package com.chains.library.service;

import com.chains.library.controller.request.BaseRequest;
import com.chains.library.controller.request.CategoryRequest;
import com.chains.library.entity.Category;

import java.util.List;

public interface ICategoryService {

    List<Category> list();

    Object page(CategoryRequest categoryRequest);

    Integer save(Category Category);

    Category getById(Integer id);

    Integer update(Category Category);

    void delById(Integer id);

    List<Category> tree();
}
