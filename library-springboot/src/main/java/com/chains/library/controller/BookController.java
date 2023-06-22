package com.chains.library.controller;

import com.chains.library.common.Result;
import com.chains.library.controller.request.BookRequest;
import com.chains.library.entity.Book;
import com.chains.library.entity.Category;
import com.chains.library.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    IBookService iBookService;
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
