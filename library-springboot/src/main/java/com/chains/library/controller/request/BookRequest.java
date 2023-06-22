package com.chains.library.controller.request;

import lombok.Data;

@Data
public class BookRequest extends BaseRequest{
    private String name;
    private String bookNo;
}
