package com.chains.library.mapper;

import com.chains.library.controller.request.BaseRequest;
import com.chains.library.controller.request.BookRequest;
import com.chains.library.controller.request.BorrowRequest;
import com.chains.library.entity.Book;
import com.chains.library.entity.Borrow;
import com.chains.library.entity.Retur;
import com.chains.library.mapper.po.BorrowReturCountPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BorrowMapper {
    List<Borrow> list();

    List<Borrow> listByCondition(BorrowRequest request);
    List<Retur> listReturByCondition(BaseRequest request);

    Integer save(Borrow borrow);

    Integer saveRetur(Retur retur);

    Borrow getById(Integer id);

    Integer update(Borrow borrow);

    void delById(Integer id);

    void delByIdRetur(Integer id);

    List<BorrowReturCountPO> getCountByTimeRange(@Param("timeRange") String timeRange, @Param("type") int type);

}
