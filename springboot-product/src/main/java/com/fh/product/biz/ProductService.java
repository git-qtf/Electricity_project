package com.fh.product.biz;

import com.fh.cart.model.Cart;
import com.fh.common.ServerResponse;
import com.fh.product.model.Product;
import com.fh.vo.ProductPage;

import java.util.List;

public interface ProductService {

    /**
     * 查询热销商品图片
     * @return
     */
    ServerResponse queryHotList();

    /**
     * 查询上架商品图片
     * @return
     */
    ServerResponse queryList();

    /**
     * 条件查询上架商品信息+懒加载（分页）
     * @return
     */
    List<Product> queryListPage(ProductPage page);

    /**
     * 查询商品是否有货
     * @param cartList
     * @return
     */
    List queryStock(List<Cart> cartList);

    /**
     * 根据id查询商品数据
     * @param id
     * @return
     */
    ServerResponse selectById(Integer id);

    /**
     * 根据id修改商品数据
     * @param id
     * @return
     */
    ServerResponse updateStock(Integer id, Integer count);
}