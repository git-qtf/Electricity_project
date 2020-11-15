package com.fh.cart.biz;

import com.alibaba.fastjson.JSON;
import com.fh.cart.model.Cart;
import com.fh.common.Ignore;
import com.fh.common.ServerResponse;

import com.fh.product.model.Product;
import com.fh.user.model.User;
import com.fh.utils.RedisUtil;
import com.fh.utils.SystemConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author huangp
 * @create 2020-11-03 22:21
 */
@Service
@Transactional
public class CartServiceImpl implements CartService{

    @Resource
    private RedisUtil redisUtil;

    //@Resource
    //private ProductMapper productMapper;
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 新增数据到redis数据库
     * @param cart
     * @return
     */
    @Override
    public ServerResponse addCart(Cart cart,User user) {


        //Product product = productMapper.selectById(cart.getId());
        ServerResponse serverResponse = restTemplate.postForObject("http://localhost:8083/product/selectById?id="+cart.getId(), null, ServerResponse.class);

        if(serverResponse.getCode()!=200){
            return serverResponse.error(serverResponse.getMessage());
        }
        Map map = (Map) serverResponse.getdata();
        //判断商品是否存在
        if(map == null ){
            return ServerResponse.error(SystemConstant.PRODUCT_IS_NULL);
        }
        //判断商品是否上架
        if(map.get("status").equals(0)){
            return ServerResponse.error(SystemConstant.PRODUCT_IS_DOWN);
        }

        //判断用户的购物车是否有商品
        //将用户id放入Cart表中
        cart.setUserId(user.getUserId());

        boolean exists = redisUtil.exist(SystemConstant.CART_KEY + user.getUserId());
        if(exists){
            //用户已有购物车
            //判断该商品是否已存在
            String productExist = (String) redisUtil.hget(SystemConstant.CART_KEY + user.getUserId(), cart.getId() + "");
            if(productExist!=null){
                //商品已存在更新商品的数量
                Cart cartRedis = JSON.parseObject(productExist, Cart.class);
                cartRedis.setCount(cartRedis.getCount()+cart.getCount());//更新商品数量
                String cartStr2 = JSON.toJSONString(cartRedis);
                redisUtil.hset(SystemConstant.CART_KEY+user.getUserId(),cart.getId()+"",cartStr2);
            }else{
                //商品不存在
                String cartStr = JSON.toJSONString(cart);
                redisUtil.hset(SystemConstant.CART_KEY+user.getUserId(),cart.getId()+"",cartStr);
            }
        }else { //用户没有购物车
            //为用户新建一辆购物车并将数据放进去
            String cartStr = JSON.toJSONString(cart);
            redisUtil.hset(SystemConstant.CART_KEY+user.getUserId(),cart.getId()+"",cartStr);
        }

        return ServerResponse.success();
    }

    /**
     * 查询商品总数
     * @return
     */
    @Ignore
    @Override
    public ServerResponse queryCartCount(User user) {
        //判断用户的购物车是否有商品
        List list = (List) redisUtil.hget(SystemConstant.CART_KEY + user.getUserId());

        if(list.size() <= 0 && list == null){
            return ServerResponse.error();
        }

        return ServerResponse.success(list.size());
    }

    /**
     * 查询所有商品
     * @return
     */
    @Override
    public List<Cart> queryCart(User user) {
        //根据用户id取出购物车中的商品
        List<Cart> cartList = new ArrayList<>();
        List<String> hget = redisUtil.hget(SystemConstant.CART_KEY + user.getUserId());

        //循环取出的String类型的数据转化为object格式放入list集合中
        for (String str: hget) {
            cartList.add(JSON.parseObject(str,Cart.class));
        }

        return cartList;
    }

    /**
     * 删除商品
     * @return
     */
    @Override
    public void delCart(Cart cart) {
        if(cart != null){
            redisUtil.hdel(SystemConstant.CART_KEY + cart.getUserId(),cart.getId()+"");
        }
    }

    @Override
    public void deleteCartBach(List<Cart> cartList) {
        for (Cart cart:cartList) {
            redisUtil.hdel(SystemConstant.CART_KEY + cart.getUserId(),cart.getId()+"");
        }
    }

    /**
     * 批量删除商品
     * @return
     */
   /* @Override
    public void deleteCartBach(List cart, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        for (Object id:cart) {
            redisUtil.hremove(SystemConstant.CART_KEY + user.getUserId(),id+"");
        }
    }*/


}
