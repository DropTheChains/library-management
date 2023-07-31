package com.chains.library.controller.request;

import lombok.Data;

@Data
public class BorrowRequest extends BaseRequest{
    private String BookName;
    private String BookNo;
    private String UserName;
}
