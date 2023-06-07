package com.chains.library.controller.request;

import lombok.Data;

@Data
public class AdminRequest extends BaseRequest{
    private String username;
    private String phone;
}
