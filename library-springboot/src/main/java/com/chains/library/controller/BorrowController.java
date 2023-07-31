package com.chains.library.controller;

import com.chains.library.common.Result;
import com.chains.library.controller.request.BaseRequest;
import com.chains.library.controller.request.BorrowRequest;
import com.chains.library.entity.Borrow;
import com.chains.library.entity.Retur;
import com.chains.library.service.IBorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/borrow")
public class BorrowController {
    @Autowired
    IBorrowService iborrowService;
    @GetMapping("/list")
    public Result list(){
       return Result.success(iborrowService.list());
    }

    @GetMapping("/page")
    public Result page(BorrowRequest borrowRequest){
        return Result.success(iborrowService.page(borrowRequest));
    }

    @GetMapping("/pageRetur")
    public Result pageRetur(BorrowRequest request){
        return Result.success(iborrowService.pageRetur(request));
    }

    @GetMapping("/get/{id}")
    public Result getById(@PathVariable Integer id){
        Borrow byId = iborrowService.getById(id);
        return Result.success(byId);
    }

    @GetMapping("/del/{id}")
    public Result delById(@PathVariable Integer id){
        iborrowService.delById(id);
        return Result.success("删除成功！");
    }

    @GetMapping("/delRetur/{id}")
    public Result delByIdRetur(@PathVariable Integer id){
        iborrowService.delByIdRetur(id);
        return Result.success("删除成功！");
    }
    @PostMapping("/save")
    public Result save(@RequestBody Borrow borrow){
        int returnid = iborrowService.save(borrow);
        return Result.success(returnid);
    }

    @PostMapping("/saveRetur")
    public Result saveRetur(@RequestBody Retur retur){
        int returnid = iborrowService.saveRetur(retur);
        return Result.success(returnid);
    }

    @PostMapping("/update")
    public Result update(@RequestBody Borrow borrow){
        return Result.success(iborrowService.update(borrow));
    }

    @GetMapping("/lineCharts/{timeRange}")
    public Result lineCharts(@PathVariable String timeRange){
        return Result.success(iborrowService.getCountByTimeRange(timeRange));
    }
}
