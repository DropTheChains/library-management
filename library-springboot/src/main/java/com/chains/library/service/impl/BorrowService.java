package com.chains.library.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.chains.library.controller.request.BaseRequest;
import com.chains.library.controller.request.BorrowRequest;
import com.chains.library.entity.Book;
import com.chains.library.entity.Borrow;
import com.chains.library.entity.Retur;
import com.chains.library.entity.User;
import com.chains.library.exception.ServiceException;
import com.chains.library.mapper.BookMapper;
import com.chains.library.mapper.BorrowMapper;
import com.chains.library.mapper.UserMapper;
import com.chains.library.mapper.po.BorrowReturCountPO;
import com.chains.library.service.IBorrowService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;


@Service
public class BorrowService implements IBorrowService {
    @Autowired
    BorrowMapper borrowMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    BookMapper bookMapper;


    @Override
    public List<Borrow> list() {
        List<Borrow> list = borrowMapper.list();
        return list;
    }

    @Override
    public PageInfo<Borrow> page(BorrowRequest borrowRequest) {
        PageHelper.startPage(borrowRequest.getPageNum(), borrowRequest.getPageSize());
        //PageHelper.startPage相当于开启分页,通过拦截MySQL的方式,把你的查询语句拦截下来加limit.
        //你需要将你的查询语句放到这个PageHelper.startPage的后面进行执行。这样才能用到拦截功能。
        List<Borrow> Borrows = borrowMapper.listByCondition(borrowRequest);
        PageInfo<Borrow> pageInfo = new PageInfo<>(Borrows);
        for (Borrow borrow : Borrows){
            LocalDate returnDate = borrow.getReturnDate();
            LocalDate now = LocalDate.now();
            if (now.plusDays(1).isEqual(returnDate)){
                borrow.setNotice("即将到期");
            }else if (now.isEqual(returnDate)){
                borrow.setNotice("已到期");
            }else if (now.isAfter(returnDate)){
                borrow.setNotice("已过期");
            }else {
                borrow.setNotice("正常");
            }
        }
        return pageInfo;
    }

    @Override
    public PageInfo<Retur> pageRetur(BorrowRequest request) {
        PageHelper.startPage(request.getPageNum(),request.getPageSize());
        List<Retur> returs = borrowMapper.listReturByCondition(request);
        PageInfo<Retur> returPageInfo = new PageInfo<>(returs);
        return returPageInfo;
    }


    @Override
    public Borrow getById(Integer id) {
        Borrow byId = borrowMapper.getById(id);
        return byId;
    }

    @Override
    public void delById(Integer id) {
        borrowMapper.delById(id);
    }

    @Override
    public Integer save(Borrow borrow) {
        String userNo = borrow.getUserNo();
        User user = userMapper.getByNo(userNo);
        if (Objects.isNull(user)){
            throw new ServiceException("用户不存在！");
        }
        String bookNo = borrow.getBookNo();
        Book book = bookMapper.getByNo(bookNo);
        if (Objects.isNull(book)){
            throw new ServiceException("图书不存在！");
        }
        Integer account = user.getAccount();
        Integer score = book.getScore();
        if (score > account){
            throw new ServiceException("用户积分不足！");
        }
        if (book.getNums() < 1){
            throw new ServiceException("图书数量不足！");
        }
        user.setAccount(user.getAccount() - score);
        userMapper.update(user);
        book.setNums(book.getNums() - 1);
        bookMapper.update(book);

        borrow.setReturnDate(LocalDate.now().plus(borrow.getDays(),ChronoUnit.DAYS));
        return borrowMapper.save(borrow);
    }

    @Override
    @Transactional
    public Integer saveRetur(Retur retur) {
        retur.setRealDate(LocalDate.now());
        Borrow borrow = borrowMapper.getById(retur.getId());
        borrowMapper.delById(borrow.getId());
        Book book = bookMapper.getByNo(borrow.getBookNo());
        book.setNums(book.getNums()+1);
        bookMapper.update(book);
        return borrowMapper.saveRetur(retur);
    }

    @Override
    public Integer update(Borrow borrow) {

        return borrowMapper.update(borrow);
    }

    @Override
    public void delByIdRetur(Integer id) {
        borrowMapper.delByIdRetur(id);
    }

    @Override
    public Map<String, Object> getCountByTimeRange(String timeRange) {
        Map<String, Object> map = new HashMap<>();
        Date today = new Date();
        List<DateTime> dateRange;
        switch (timeRange){
            case "week":
                dateRange = DateUtil.rangeToList(DateUtil.offsetDay(today,-6),today, DateField.DAY_OF_WEEK);
                break;
            case "month":
                dateRange = DateUtil.rangeToList(DateUtil.offsetDay(today,-29),today, DateField.DAY_OF_MONTH);
                break;
            case "month2":
                dateRange = DateUtil.rangeToList(DateUtil.offsetDay(today,-59),today, DateField.DAY_OF_MONTH);
                break;
            case "month3":
                dateRange = DateUtil.rangeToList(DateUtil.offsetDay(today,-89),today, DateField.DAY_OF_MONTH);
                break;
            default:
                dateRange = new ArrayList<>();
        }
        List<String> dateStrRange = datetimeToDateStr(dateRange);
        map.put("date",dateStrRange);
        List<BorrowReturCountPO> borrowCount = borrowMapper.getCountByTimeRange(timeRange, 1);
        List<BorrowReturCountPO> returCount = borrowMapper.getCountByTimeRange(timeRange, 2);
        map.put("borrow",countList(borrowCount,dateStrRange));
        map.put("retur",countList(returCount,dateStrRange));

        return map;
    }

    private List<String> datetimeToDateStr(List<DateTime> dateTimesList){
        List<String>  list = CollUtil.newArrayList();
        if (CollUtil.isEmpty(dateTimesList)){
            return list;
        }
        for (DateTime dateTime : dateTimesList){
            String date = DateUtil.formatDate(dateTime);
            list.add(date);
        }
        return list;
    }

    private List<Integer> countList(List<BorrowReturCountPO> countPOList,List<String> dateRange){
        List<Integer> list = CollUtil.newArrayList();
        if (CollUtil.isEmpty(countPOList)){
            return list;
        }
        for (String date: dateRange){
            Integer count = countPOList.stream().filter(countPO -> date.equals(countPO.getDate()))
                    .map(BorrowReturCountPO::getCount).findFirst().orElse(0);
            list.add(count);
        }
        return list;
    }

}
