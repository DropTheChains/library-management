package com.chains.library.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.chains.library.controller.request.BaseRequest;
import com.chains.library.controller.request.CategoryRequest;
import com.chains.library.entity.Category;
import com.chains.library.mapper.CategoryMapper;
import com.chains.library.service.ICategoryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class CategoryService implements ICategoryService {
    @Autowired
    CategoryMapper categoryMapper;

    public List<Category> list() {
        List<Category> list = categoryMapper.list();
        return list;
    }
    public List<Category> tree(){
        List<Category> list = categoryMapper.list();
        List<Category> tree = createTree(null, list);
        return tree;
    }
    public List<Category> createTree(Integer pid,List<Category> categories){
        ArrayList<Category> treeList = new ArrayList<>();
        for (Category category : categories){
            if (pid == null){
                if (category.getPid() == null){
                    treeList.add(category);
                    category.setChildren(createTree(category.getId(),categories));
                }
            }else{
                if (pid.equals(category.getPid())){
                    treeList.add(category);
                    category.setChildren(createTree(category.getId(),categories));
                }
            }
            if (CollectionUtil.isEmpty(category.getChildren())){
                category.setChildren(null);
            }
        }
        return treeList;
    }

    public Object page(CategoryRequest categoryRequest) {
        PageHelper.startPage(categoryRequest.getPageNum(), categoryRequest.getPageSize());
        //PageHelper.startPage相当于开启分页,通过拦截MySQL的方式,把你的查询语句拦截下来加limit.
        //你需要将你的查询语句放到这个PageHelper.startPage的后面进行执行。这样才能用到拦截功能。
        List<Category> CategoryList = categoryMapper.listByCondition(categoryRequest);
        PageInfo<Category> pageInfo = new PageInfo<>(CategoryList);
        return pageInfo;
    }

    public Integer save(Category category) {
        category.setCreateTime(new Date());
        return categoryMapper.save(category);
    }


    public Category getById(Integer id) {
        Category byId = categoryMapper.getById(id);
        return byId;
    }

    public Integer update(Category category) {
        category.setUpdateTime(new Date());
        return categoryMapper.update(category);
    }

    public void delById(Integer id) {
        categoryMapper.delById(id);
    }
}
