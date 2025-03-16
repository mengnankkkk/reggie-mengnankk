package com.mengnankk.controller;


import com.mengnankk.comon.R;
import com.mengnankk.entity.Orders;
import com.mengnankk.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;


    /**
     *提交数据
     * @param orders
     * @return
     */
    public R<Service> sumit(@RequestBody Orders orders){
        log.info("订单数据:{}",orders);
        orderService.sumbit(orders);

        return R.success("下单成功");
    }
}
