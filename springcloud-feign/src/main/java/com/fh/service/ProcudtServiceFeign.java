package com.fh.service;

import com.fh.common.ServerResponse;
import com.fh.service.impl.ProcudtServiceFeignImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @author huangp
 * @create 2020-11-17 14:14
 */
@FeignClient(value = "springboot-product",fallback = ProcudtServiceFeignImpl.class)
public interface ProcudtServiceFeign {

    /**
     * 根据id查询商品数据
     * @param id
     * @return
     */
    @RequestMapping("product/selectById")
    ServerResponse selectById(@RequestParam("id") Integer id);

    /**
     * 根据id修改商品数据
     * @param id
     * @return
     */
    @RequestMapping("product/updateStock")
    ServerResponse updateStock(@RequestParam("id") Integer id,@RequestParam("count") Integer count);

}
