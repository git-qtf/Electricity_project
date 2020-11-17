package com.fh.cart.controller;

import com.fh.cart.biz.CartService;
import com.fh.cart.model.Cart;
import com.fh.common.Ignore;
import com.fh.common.ServerResponse;
import com.fh.common.UserCustomAnnotations;
import com.fh.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author huangp
 * @create 2020-11-03 22:21
 */
@RestController
@RequestMapping("cart")
public class CartController {

   @Autowired
    private CartService cartService;

    /**
     * 新增数据到redis数据库
     * @param cart
     * @return
     */
    @RequestMapping("addCart")
    public ServerResponse addCart(Cart cart, @UserCustomAnnotations User user){
       try {
           cartService.addCart(cart,user);
           return ServerResponse.success();
       } catch (Exception e) {
           e.printStackTrace();
           return ServerResponse.error();
       }
   }

    /**
     * 查询商品总数
     * @return
     */
    @RequestMapping("queryCartCount")
    public ServerResponse queryCartCount(@UserCustomAnnotations User user){
       try {
           return ServerResponse.success(cartService.queryCartCount(user));
       } catch (Exception e) {
           e.printStackTrace();
           return ServerResponse.error();
       }
   }

    /**
     * 查询所有商品
     * @return
     */
    @RequestMapping("queryCart")
    public ServerResponse queryCart(@UserCustomAnnotations User user){
       try {
           return ServerResponse.success(cartService.queryCart(user));
       } catch (Exception e) {
           e.printStackTrace();
           return ServerResponse.error();
       }
   }

    /**
     * 删除商品
     * @return
     */
    @RequestMapping("delCart")
    public ServerResponse delCart(Cart Cart){
       try {
           cartService.delCart(Cart);
           return ServerResponse.success();
       } catch (Exception e) {
           e.printStackTrace();
           return ServerResponse.error();
       }
   }

    /**
     * 批量删除商品
     * @return
     */
    @RequestMapping("deleteCartBach")
    public ServerResponse deleteCartBach(List<Cart> cartList){
        try {

            cartService.deleteCartBach(cartList);
            return ServerResponse.success();
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.error();
        }
    }




}
