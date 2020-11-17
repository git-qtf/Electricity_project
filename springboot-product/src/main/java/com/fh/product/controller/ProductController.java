package com.fh.product.controller;


import com.fh.cart.model.Cart;
import com.fh.common.Ignore;
import com.fh.common.ServerResponse;
import com.fh.product.biz.ProductService;
import com.fh.product.model.Product;
import com.fh.vo.ProductPage;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * 查询热销商品图片
     * @return
     */
    @Ignore
    @RequestMapping("queryHotList")
    public ServerResponse queryHotList(){
        return productService.queryHotList();
    }

    /**
     * 查询上架商品图片
     * @return
     */
    @Ignore
    @RequestMapping("queryList")
    public ServerResponse queryList(){
       return productService.queryList();
    }

    /**
     * 条件查询上架商品信息+懒加载（分页）
     * @return
     */
    @Ignore
    @RequestMapping("queryListPage")
    public PageInfo queryListPage(ProductPage page){
        PageHelper.startPage(page.getPageSize(), page.getPageLimit());
        List<Product> productList = productService.queryListPage(page);
        return new PageInfo(productList);
    }

    /**
     * 查询商品是否有货
     * @param cartList
     * @return
     */
    @RequestMapping("queryStock")
    public ServerResponse queryStock(@RequestBody List<Cart> cartList){
        try {
            List list = productService.queryStock(cartList);
            return ServerResponse.success(list);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.error();
        }
    }

    /**
     * 根据id查询商品数据
     * @param
     * @return
     */
    @Ignore
    @RequestMapping("selectById")
    public ServerResponse selectById(@RequestParam("id") Integer id){
        try {
            return productService.selectById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.error();
        }
    }

    /**
     * 根据id修改商品数据
     * @param id
     * @return
     */
    @Ignore
    @RequestMapping("updateStock")
    public ServerResponse updateStock(Integer id,Integer count){
        try {
            return productService.updateStock(id,count);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.error();
        }
    }

}
