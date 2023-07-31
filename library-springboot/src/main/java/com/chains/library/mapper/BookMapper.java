package com.chains.library.mapper;

import com.chains.library.controller.request.BaseRequest;
import com.chains.library.controller.request.BookRequest;
import com.chains.library.entity.Book;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookMapper {
    List<Book> list();

    List<Book> listByCondition(BookRequest request);

    Integer save(Book Book);

    Book getById(Integer id);

    Integer update(Book Book);

    void delById(Integer id);

    Book getByNo(String bookNo);
}
