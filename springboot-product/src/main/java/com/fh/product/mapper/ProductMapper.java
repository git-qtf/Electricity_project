package com.fh.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fh.product.model.Product;

public interface ProductMapper extends BaseMapper<Product> {

    /**
     * 减少商品的库存数量
     * @param id
     * @param count
     */
    Long updateStock(Integer id, int count);
}
