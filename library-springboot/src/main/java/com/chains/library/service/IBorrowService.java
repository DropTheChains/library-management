package com.chains.library.service;


import com.chains.library.controller.request.BaseRequest;
import com.chains.library.controller.request.BorrowRequest;
import com.chains.library.entity.Borrow;
import com.chains.library.entity.Retur;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface IBorrowService {

    List<Borrow> list();

    PageInfo<Borrow> page(BorrowRequest BorrowRequest);

    PageInfo<Retur> pageRetur(BorrowRequest request);


    Borrow getById(Integer id);

    void delById(Integer id);

    Integer save(Borrow Borrow);

    Integer saveRetur(Retur retur);

    Integer update(Borrow Borrow);

    void delByIdRetur(Integer id);

    Map<String,Object> getCountByTimeRange(String timeRange);
}
