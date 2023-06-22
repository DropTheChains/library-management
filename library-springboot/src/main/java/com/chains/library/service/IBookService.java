package com.chains.library.service;

import com.chains.library.controller.request.BookRequest;
import com.chains.library.entity.Book;

import java.util.List;

public interface IBookService {

    List<Book> list();

    Object page(BookRequest bookRequest);

    Book getById(Integer id);

    void delById(Integer id);

    Integer save(Book book);

    Integer update(Book book);
}
