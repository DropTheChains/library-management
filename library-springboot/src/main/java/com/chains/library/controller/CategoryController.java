package com.chains.library.controller;

import com.chains.library.common.Result;
import com.chains.library.controller.request.BaseRequest;
import com.chains.library.controller.request.CategoryRequest;
import com.chains.library.entity.Category;
import com.chains.library.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    ICategoryService categoryService;

    @GetMapping("/list")
    public Result list(){
        List<Category> list = categoryService.list();
        return Result.success(list);
    }
    @GetMapping("/tree")
    public Result tree(){
        List<Category> list = categoryService.tree();
        return Result.success(list);
    }
    @GetMapping("/page")
    public Result page(CategoryRequest categoryRequest){
        return Result.success(categoryService.page(categoryRequest));
    }
    @PostMapping("/save")
    public Result save(@RequestBody Category Category){
        int returnid = categoryService.save(Category);
        return Result.success(returnid);
    }
    @GetMapping("/get/{id}")
    public Result getById(@PathVariable Integer id){
        return Result.success(categoryService.getById(id));
    }

    @PostMapping("/update")
    public Result update(@RequestBody Category Category){
        return Result.success(categoryService.update(Category));
    }
    @GetMapping("/delete/{id}")
    public Result delById(@PathVariable Integer id){
        categoryService.delById(id);
        return Result.success();
    }

}
