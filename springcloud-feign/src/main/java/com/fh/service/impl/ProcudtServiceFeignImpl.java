package com.fh.service.impl;

import com.fh.common.ServerResponse;
import com.fh.service.ProcudtServiceFeign;
import org.springframework.stereotype.Component;

/**
 * @author huangp
 * @create 2020-11-17 14:37
 */
@Component
public class ProcudtServiceFeignImpl implements ProcudtServiceFeign {

    @Override
    public ServerResponse selectById(Integer id) {
        return null;
    }

    @Override
    public ServerResponse updateStock(Integer id, Integer count) {
        return null;
    }
}
