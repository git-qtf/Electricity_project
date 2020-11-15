package com.fh.order.biz;

import com.fh.cart.model.Cart;
import com.fh.common.ServerResponse;
import com.fh.user.model.User;

import java.util.List;

/**
 * @author huangp
 * @create 2020-11-05 19:11
 */
public interface OrderService {

    /**
     * 生成订单
     * @param
     * @param payType
     * @param addressId
     * @param totalPrice
     * @param user
     * @return
     */
    ServerResponse buildOrder(List<Cart> cartList1, Integer payType, Integer addressId, Integer totalPrice, User user);

    /**
     * 修改订单状态
     * @param out_trade_no
     */
    void updateStatus(String out_trade_no);

    /**
     * 查询所有订单
     * @return
     */
    ServerResponse queryOrder(User user);
}
