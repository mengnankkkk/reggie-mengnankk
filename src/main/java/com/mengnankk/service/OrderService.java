package com.mengnankk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mengnankk.entity.Orders;
import org.springframework.stereotype.Service;

@Service
public interface OrderService extends IService<Orders> {

    public void sumbit(Orders orders);


}
