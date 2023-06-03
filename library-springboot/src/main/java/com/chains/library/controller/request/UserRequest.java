package com.chains.library.controller.request;

import lombok.Data;

@Data
public class UserRequest extends BaseRequest{
    private String name;
    private String phone;
}
