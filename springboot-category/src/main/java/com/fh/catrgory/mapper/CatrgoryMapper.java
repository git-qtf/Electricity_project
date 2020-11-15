package com.fh.catrgory.mapper;

import com.fh.catrgory.model.Catrgory;

import java.util.List;
import java.util.Map;

public interface CatrgoryMapper {
    List<Map<String,Object>> queryList();

    void add(Catrgory catrgory);

    void update(Catrgory catrgory);

    void delete(List<Integer> list);
}
