package com.ma.mapperservice;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.ma.dao.BaiduyunDao;
import com.ma.domain.Baiduyun;
import com.ma.mapper.BaiduMapper;
import com.ma.util.BaiduyunUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Service

public class BaiduMapperImpl implements BaiduMapper {
    @Autowired
    private Environment environment;
    @Autowired
    private BaiduyunDao baiduyunDao;

    @Override
    public String getLinkBaiduyun(String searchName) {
        String className="btn-download";
        try {
            List<String> strings = BaiduyunUtil.searchList(searchName, environment.getProperty("linkurl"), "pss");


           // System.out.println(strings);
            String name=strings.get(0).split("@@")[1];
            if(strings==null)return null;
            String linkToYun = BaiduyunUtil.getLinkToYun(strings);
            String getlink = BaiduyunUtil.getlink("", "btn-download", linkToYun);
            Baiduyun baiduyun = new Baiduyun();
            baiduyun.setName(name);
            baiduyun.setUrl(getlink);
            Baiduyun baiduyun1 = baiduyun.selectOne(new EntityWrapper(baiduyun));

            if(!getlink.contains("@@")) {
                if (baiduyun1 == null) baiduyun.insert();
            }else{
                String pass=getlink.split("@@")[1];
                baiduyun.setKeyword(pass);
                if (baiduyun1 == null) baiduyun.insert();
            }
            return getlink;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
        //String link=baiduyunUtil.getlink(searchName,className);
    }
}
