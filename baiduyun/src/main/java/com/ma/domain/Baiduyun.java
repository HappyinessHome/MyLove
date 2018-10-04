package com.ma.domain;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@ToString
@TableName("wechat")
public class Baiduyun extends Model<Baiduyun> {
    @Setter
    @Getter
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    @Setter
    @Getter
    private String name;
    @Setter
    @Getter
    private String url;
    @Setter
    @Getter
    private String keyword;
    @Override
    protected Serializable pkVal() {
        return id;
    }
}

