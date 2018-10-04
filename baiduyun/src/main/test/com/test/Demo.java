package com.test;


import com.ma.util.BaiduyunUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

public class Demo {

    @Test
    public void test() throws IOException {
      //  String getlink = BaiduyunUtil.getlink("", "btn-download", "http://www.pansoso.com/?a=go&url=68ad0de407e70a7919b4d95e8896975ewY4e3c2ead7eac2be070affd6cd8056925MvMWhzbXNHZkU&t=5a!M6LS155S36LSr56m35aWz44CQ5Lq65Lq644CR&dx=MEI=&m=");
        List<String> strings = BaiduyunUtil.searchList("富贵男贫穷", "http://www.pansoso.com/zh/", "pss");
        String linkToYun=BaiduyunUtil.getLinkToYun(strings);
        String re=BaiduyunUtil.getlink("","btn-download",linkToYun);
        //System.out.println(re);
    }
    public void test01(){
        String s="网盘列表：<a class=\"btn-download\" rel=\"noreferrer external nofollow\" href=\"http://to.pansoso.com/?a=to&amp;url=68ad0de407e70a7919b4d95e8896975ewY4e3c2ead7eac2be070affd6cd8056925MvMWh1ZW9GaE0\" target=\"_self\">百度网盘</a>&nbsp;&nbsp;&nbsp;提取码：990c";
        //s.split("提取码：")
    }
}

