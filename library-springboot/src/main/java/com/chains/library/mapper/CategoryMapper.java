package com.chains.library.mapper;

import com.chains.library.controller.request.BaseRequest;
import com.chains.library.controller.request.CategoryRequest;
import com.chains.library.entity.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {
    List<Category> list();

    List<Category> listByCondition(CategoryRequest categoryRequest);

    Integer save(Category category);

    Category getById(Integer id);

    Integer update(Category category);

    void delById(Integer id);
}
