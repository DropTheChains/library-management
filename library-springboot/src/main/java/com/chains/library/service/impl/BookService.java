package com.chains.library.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.chains.library.controller.request.BookRequest;
import com.chains.library.entity.Book;
import com.chains.library.entity.Category;
import com.chains.library.mapper.BookMapper;
import com.chains.library.service.IBookService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService implements IBookService {
    @Autowired
    BookMapper bookMapper;

    @Override
    public List<Book> list() {
        List<Book> list = bookMapper.list();
        return list;
    }

    @Override
    public Object page(BookRequest bookRequest) {
        PageHelper.startPage(bookRequest.getPageNum(), bookRequest.getPageSize());
        //PageHelper.startPage相当于开启分页,通过拦截MySQL的方式,把你的查询语句拦截下来加limit.
        //你需要将你的查询语句放到这个PageHelper.startPage的后面进行执行。这样才能用到拦截功能。
        List<Book> books = bookMapper.listByCondition(bookRequest);
        PageInfo<Book> pageInfo = new PageInfo<>(books);
        return pageInfo;
    }

    @Override
    public Book getById(Integer id) {
        Book byId = bookMapper.getById(id);
        return byId;
    }

    @Override
    public void delById(Integer id) {
        bookMapper.delById(id);
    }

    @Override
    public Integer save(Book book) {
        Book setCategory = setCategory(book);
        return bookMapper.save(setCategory);
    }

    @Override
    public Integer update(Book book) {
        Book setCategory = setCategory(book);
        return bookMapper.update(setCategory);
    }
    public Book setCategory(Book book){
        List<String> categories = book.getCategories();
        StringBuilder sb = new StringBuilder();
        if (CollectionUtil.isNotEmpty(categories)){
            categories.forEach(v -> sb.append(v).append(" > "));
            book.setCategory(sb.toString().substring(0, sb.lastIndexOf(" > ")));
        }
        return book;
    }
}
