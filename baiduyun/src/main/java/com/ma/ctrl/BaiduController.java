package com.ma.ctrl;

import com.ma.mapper.BaiduMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaiduController {
    @Autowired
    private BaiduMapper baiduMapper;
    @RequestMapping(value = "/search",method ={RequestMethod.GET,RequestMethod.POST})
    public String getLinkBaiduyun(@RequestParam(value = "searchName",required = true)String searchName){
       return baiduMapper.getLinkBaiduyun(searchName);
    }
}
