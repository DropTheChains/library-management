package com.chains.library.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.chains.library.common.Result;
import com.chains.library.controller.request.BookRequest;
import com.chains.library.entity.Admin;
import com.chains.library.entity.Book;
import com.chains.library.entity.Category;
import com.chains.library.service.IBookService;
import com.chains.library.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.yaml.snakeyaml.util.UriEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    IBookService iBookService;

    private static final String BASE_FILE_PATH = System.getProperty("user.dir") + "/files/";

    @PostMapping("/file/upload")
    public Result uploadFile(MultipartFile file){
        String originalFilename = file.getOriginalFilename();
        long flag = System.currentTimeMillis();
        String filePath = BASE_FILE_PATH + flag + "_" + originalFilename;
        try {
            FileUtil.mkParentDirs(filePath);
            file.transferTo(FileUtil.file(filePath));
            Admin currentAdmin = TokenUtils.getCurrentAdmin();
            String genToken = TokenUtils.genToken(Objects.requireNonNull(currentAdmin).getId().toString(), currentAdmin.getPassword(),15);

            return Result.success("http://localhost:9090/book/file/download/" + flag + "?token=" + genToken);
        }catch (Exception e){
            log.info("文件上传失败",e);
        }
        return Result.error("文件上传失败");
    }

    @GetMapping("/file/download/{flag}")
    public void download(@PathVariable String flag, @RequestParam(required = false) String play, HttpServletResponse response){
        OutputStream os;
        List<String> fileNames = FileUtil.listFileNames(BASE_FILE_PATH);
        String fileName = fileNames.stream().filter(name -> name.contains(flag)).findAny().orElse("");
        try {
            if (StrUtil.isNotEmpty(fileName)){
                String realName = fileName.substring(fileName.indexOf("_") + 1);
                if ("1".equals(play)){
                    response.addHeader("Content-Disposition","inline;filename=" + URLEncoder.encode(realName,"UTF-8"));
                }else {
                    response.addHeader("Content-Disposition","attachment;filename=" + URLEncoder.encode(realName,"UTF-8"));
                }
                byte[] bytes = FileUtil.readBytes(BASE_FILE_PATH + fileName);
                os = response.getOutputStream();
                os.write(bytes);
                os.flush();
                os.close();
            }
        } catch (Exception e) {
            log.error("文件下载失败");
        }
    }

    @GetMapping("/list")
    public Result list(){
       return Result.success(iBookService.list());
    }

    @GetMapping("/page")
    public Result page(BookRequest bookRequest){
        return Result.success(iBookService.page(bookRequest));
    }

    @GetMapping("/get/{id}")
    public Result getById(@PathVariable Integer id){
        Book byId = iBookService.getById(id);
        return Result.success(byId);
    }

    @GetMapping("/del/{id}")
    public Result delById(@PathVariable Integer id){
        iBookService.delById(id);
        return Result.success("删除成功！");
    }
    @PostMapping("/save")
    public Result save(@RequestBody Book book){
        int returnid = iBookService.save(book);
        return Result.success(returnid);
    }

    @PostMapping("/update")
    public Result update(@RequestBody Book book){
        return Result.success(iBookService.update(book));
    }
}
