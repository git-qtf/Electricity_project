package com.fh.catrgory.controller;

import com.fh.catrgory.biz.CatrgoryService;
import com.fh.common.Ignore;
import com.fh.common.ServerResponse;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("catrgory")
public class CatrgoryController {

    @Resource
    CatrgoryService catrgoryService;

    @Ignore
    @RequestMapping("queryList")
    public ServerResponse queryList(){
        return catrgoryService.queryList();
    }

}
