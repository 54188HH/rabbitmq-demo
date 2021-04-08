package com.wangshili.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * @author lzq
 * @version 1.0
 * @date 2021/4/7 17:05
 */
@TableName("qiangok")
public class Qiangok implements Serializable{
    private static final long serialVersionUID = -8273559749383800093L;
    @TableId(type = IdType.AUTO)
    private Long id;
    private int userId;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Qiangok(Long id, int userId) {
        this.id = id;
        this.userId = userId;
    }

    public Qiangok() {
    }
}
