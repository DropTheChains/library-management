package com.chains.library.controller.request;

import lombok.Data;

@Data
public class PasswordRequest {
    private Integer id;
    private String username;
    private String newPass;
}
