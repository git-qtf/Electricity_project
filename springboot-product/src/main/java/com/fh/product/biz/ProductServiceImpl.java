package com.fh.product.biz;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fh.cart.model.Cart;
import com.fh.common.ServerResponse;
import com.fh.product.mapper.ProductMapper;
import com.fh.product.model.Product;
import com.fh.vo.ProductPage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Resource
    private ProductMapper productMapper;

    /**
     * 查询热销商品图片
     * @return
     */
    @Override
    public ServerResponse queryHotList() {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("isHot","1");
        return ServerResponse.success(productMapper.selectList(wrapper));
    }

    /**
     * 查询上架商品图片
     * @return
     */
    @Override
    public ServerResponse queryList() {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("status","1");
        List list = productMapper.selectList(wrapper);
        return ServerResponse.success(list);
    }


    /**
     * 条件查询上架商品信息+懒加载（分页）
     * @return
     */
    @Override
    public List<Product> queryListPage(ProductPage page) {
        QueryWrapper wrapper = new QueryWrapper();
        if(StringUtils.isNotBlank(page.getName())){
            wrapper.like("name",page.getName());
            wrapper.eq("status","1");
        }
        return productMapper.selectList(wrapper);
    }

    /**
     * 查询商品是否有货
     * @param cartList
     * @return
     */
    @Override
    public List queryStock(List<Cart> cartList) {

        List<Cart> list = new ArrayList<>();
        if(cartList != null && cartList.size() > 0)

        for (Cart cart: cartList) {
            Product product = productMapper.selectById(cart.getId());
            if(product.getStock() >= cart.getCount()){
                cart.setStockStatus(1);
            }else{
                cart.setStockStatus(0);
            }
            list.add(cart);
        }
            return list;
    }

    /**
     * 根据id查询商品数据
     * @param id
     * @return
     */
    @Override
    public ServerResponse selectById(Integer id) {
        Product product = productMapper.selectById(id);
        return ServerResponse.success(product);
    }

    /**
     * 根据id修改商品数据
     * @param id
     * @return
     */
    @Override
    public ServerResponse updateStock(Integer id, Integer count) {
        Long aLong = productMapper.updateStock(id, count);
        return ServerResponse.success(aLong);
    }

}
