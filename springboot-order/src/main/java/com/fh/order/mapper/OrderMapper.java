package com.fh.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fh.order.model.Order;

import java.util.List;

/**
 * @author huangp
 * @create 2020-11-05 19:11
 */
public interface OrderMapper extends BaseMapper<Order> {

    /**
     * 查询所有订单
     */
    List<Order> queryOrder();
}
