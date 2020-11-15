package com.fh.order.biz;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fh.cart.model.Cart;
import com.fh.common.Ignore;
import com.fh.common.ServerResponse;
import com.fh.order.mapper.OrderDetailMapper;
import com.fh.order.mapper.OrderMapper;
import com.fh.order.model.Order;
import com.fh.order.model.OrderDetail;
import com.fh.user.model.User;
import com.fh.utils.IdUtil;
import com.fh.utils.RedisUtil;
import com.fh.utils.SystemConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author huangp
 * @create 2020-11-05 19:11
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService{

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private OrderDetailMapper orderDetailMapper;

   /* @Resource
    private ProductMapper productMapper;*/

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 生成订单
     * @param cartList1
     * @param payType
     * @param addressId
     * @param totalPrice
     * @param user
     * @return
     */
    @Override
    public ServerResponse buildOrder(List<Cart> cartList1, Integer payType, Integer addressId, Integer totalPrice, User user) { //获取到session中存放的以登录用户信息


        //库存不足时的一个list集合
        List<String> stockNotFull = new ArrayList<>();

        //订单详情集合
        List<OrderDetail> orderDetailList = new ArrayList<>();

        //使用工具类中的雪花算法生成不会重复的一个id
        String orderId = IdUtil.createId();

        //总数量
        int totaCount = 0;

        //for判断商品是否充足
        for (int i = 0; i < cartList1.size(); i++) {
            //通过商品id查询商品信息

            ServerResponse serverResponse = restTemplate.postForObject("http://localhost:8083/product/selectById?id="+cartList1.get(i).getId(), null, ServerResponse.class);
            if(serverResponse.getCode() != 200){
                return ServerResponse.error(serverResponse.getMessage());
            }
            Map map = (Map) serverResponse.getdata();
            //根据查询出来的商品信息来判断商品数量是否足够
            if(Integer.valueOf(String.valueOf(map.get("stock"))) >= cartList1.get(i).getCount()){
                //如果充足减少数据库商品库存数量
                ServerResponse res = restTemplate.postForObject("http://localhost:8083/product/updateStock?Id=" + cartList1.get(i).getId() + "&count=" + cartList1.get(i).getCount(), null, ServerResponse.class);
                if(res.getCode() != 200){
                    return ServerResponse.error(res.getMessage());
                }
                Map mm = (Map) res.getdata();
                if(res.getdata().equals(1)){
                    //生成订单信息
                    OrderDetail orderDetail = getOrderDetail(orderId, cartList1.get(i));
                    //将订单信息放入【订单详情集合】
                    orderDetailList.add(orderDetail);
                    totaCount += cartList1.get(i).getCount();

                }else{
                    //如果库存不充足的话把商品名称返回前端
                    stockNotFull.add(String.valueOf(map.get("name")));
                }

            }else{
                //库存不充足，商品名称返回前端
                stockNotFull.add(String.valueOf(map.get("name")));
            }
        }

        //判断有没有库存不足的商品
        if(stockNotFull.size() == 0){
            //没有商品不足的情况下保存订单信息
            for (OrderDetail orderDetail:orderDetailList) {
                orderDetailMapper.insert(orderDetail);

                //修改购物车中对应的商品
                String productExist = (String) RedisUtil.hget(SystemConstant.CART_KEY + user.getUserId(), orderDetail.getProductId() + "");
                Cart cartRedis = JSON.parseObject(productExist, Cart.class);
                //订单页的商品数量<=购物车中的商品数量
                if(cartRedis.getCount() <= orderDetail.getCount()){
                    RedisUtil.hdel(SystemConstant.CART_KEY+user.getUserId(),orderDetail.getProductId()+"");
                }else{
                    //更新商品的数量
                    cartRedis.setCount(cartRedis.getCount()-orderDetail.getCount());
                    String jsonString = JSON.toJSONString(cartRedis);
                    RedisUtil.hset(SystemConstant.CART_KEY+user.getUserId(),orderDetail.getProductId()+"",jsonString);
                }

            }
            Order order = new Order();
            addOrder(payType, addressId, totalPrice, user, orderId, totaCount, order);
            orderMapper.insert(order);

            //雪花算法生成的id
            return ServerResponse.success(orderId);
        }else{
            return ServerResponse.error(stockNotFull.toString());
        }


    }

    /**
     * 修改订单信息
     * @param out_trade_no
     */
    @Override
    public void updateStatus(String out_trade_no) {
        Order order = orderMapper.selectById(out_trade_no);
        order.setStatus(2);
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("id",out_trade_no);
        orderMapper.update(order,wrapper);
    }

    /**
     * 查询所有订单
     * @return
     */
    @Override
    public ServerResponse queryOrder(User user) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("userId",user.getUserId());
        List<Order> list = orderMapper.selectList(wrapper);
        for (Order order :list) {
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("orderId",order.getId());
            List<OrderDetail> orderDetailList = orderDetailMapper.selectList(queryWrapper);
            order.setOrderDetailLsit(orderDetailList);
        }
        return ServerResponse.success(list);
    }


    private void addOrder(Integer payType, Integer addressId, Integer totalPrice, User user, String orderId, int totaCount, @org.jetbrains.annotations.NotNull Order order) {
        order.setAddressId(addressId);
        order.setCreateDate(new Date());
        order.setTotalCount(totaCount);
        order.setUserId(user.getUserId());
        order.setTotalPrice(BigDecimal.valueOf(totalPrice));
        order.setId(orderId);
        order.setPayType(payType);
    }

    private OrderDetail getOrderDetail(String orderId, Cart cart) {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProductId(cart.getId());
        orderDetail.setOrderId(orderId);
        orderDetail.setPrice(cart.getPrice());
        orderDetail.setName(cart.getName());
        orderDetail.setCount(cart.getCount());
        orderDetail.setFilePath(cart.getFilePath());
        return orderDetail;
    }
}
