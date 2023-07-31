package com.chains.library.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

/**
 * @description borrow
 * @author DropTheChains
 * @date 2023-07-05
 */
@Data
public class Retur {

    /**
     * id
     */
    private Integer id;

    /**
     * 图书名称
     */
    private String bookName;

    /**
     * 图书标准码
     */
    private String bookNo;

    /**
     * 用户id
     */
    private String userNo;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 用户联系方式
     */
    private String userPhone;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private LocalDate createtime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private LocalDate updatetime;

    /**
     * 借书积分
     */
    private Integer score;
    private String status;
    private Integer days;
    private LocalDate returnDate;
    //提醒状态 即将到期（-1） 已到期（当天） 已过期（超过归还日期）
    private String notice;
    private LocalDate realDate;

}