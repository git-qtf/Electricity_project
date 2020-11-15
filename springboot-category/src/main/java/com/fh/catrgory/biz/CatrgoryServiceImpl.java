package com.fh.catrgory.biz;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.fh.catrgory.mapper.CatrgoryMapper;
import com.fh.catrgory.model.Catrgory;
import com.fh.common.ServerResponse;
import com.fh.utils.RedisUtil;
import com.fh.utils.SystemConstant;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CatrgoryServiceImpl implements CatrgoryService {

    @Resource
    CatrgoryMapper catrgoryMapper;

    @Override
    public ServerResponse queryList() {

        boolean exist = RedisUtil.exist(SystemConstant.CARTRGORY);
        if(exist){
            String cart = RedisUtil.get(SystemConstant.CARTRGORY);
            List<Map> maps = JSONArray.parseArray(cart, Map.class);
            return ServerResponse.success(maps);
        }
        //全部集合
        List<Map<String, Object>> allList = catrgoryMapper.queryList();
        //定义一个父亲的集合
        List<Map<String, Object>> fList =new ArrayList<>();
        //循环全部 获取根节点为0的数据
        for (Map<String, Object> allMap : allList) {
            //通过跟pid 为0就是根节点
            if(allMap.get("pId").equals(0)){
                //把查到的根节点放到父集合中
                fList.add(allMap);
            }
        }
        //调用递归方法
        getEZList(allList,fList);

        String jsonString = JSON.toJSONString(fList);
        RedisUtil.set(SystemConstant.CARTRGORY,jsonString);

        //返回父亲集合
        return ServerResponse.success(fList);
    }



    //通过递归获取儿子集合
    private  void getEZList(List<Map<String, Object>> allList,List<Map<String, Object>> fList){
        //判断父亲集合不为空和size小于0
        if(fList!=null && fList.size()>0){
            //循环父集合
            for (Map<String, Object> fMap : fList) {
                //定义儿子集合
                List<Map<String, Object>> eList =new ArrayList<>();
                //循环全部集合 根据根节点id 找到子节点
                for (Map<String, Object> allMap : allList) {
                    //通过父集合的id去全部集合中寻找儿子
                    if(fMap.get("id").equals(allMap.get("pId"))){
                        //放到儿子集合中
                        eList.add(allMap);
                    }
                }
                //判断儿子集合大于0
                if(eList.size()>0){
                    //把儿子集合放入父亲中
                    fMap.put("children",eList);
                    //开始递归
                    //如果儿子集合=0递归停止
                    getEZList(allList,eList);
                }
            }
        }
    }
}
