package com.fh.cart.biz;

import com.fh.cart.model.Cart;
import com.fh.common.ServerResponse;
import com.fh.user.model.User;

import java.util.List;

/**
 * @author huangp
 * @create 2020-11-03 22:21
 */
public interface CartService {

    /**
     * 新增数据到redis数据库
     * @param cart
     * @return
     */
    ServerResponse addCart(Cart cart, User user);

    /**
     * 查询商品总数
     * @return
     */
    ServerResponse queryCartCount(User user);

    /**
     * 查询所有商品
     * @return
     */
    List<Cart> queryCart(User user);

    /**
     * 删除商品
     * @return
     */
    void delCart(Cart cart);

    void deleteCartBach(List<Cart> cartList);

    /**
     * 批量删除商品
     * @return
     */
   /* void deleteCartBach(List cart, HttpServletRequest request);*/
}
