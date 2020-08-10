package com.ruike.community.entity;


import lombok.Data;

@Data
public class Page {
    //当前页面
    private int current = 1;

    //每页显示数量上限
    private int capacity = 15;

    //数据总行数
    private int rows;

    //访问路径
    private String path = "/index";


    //获取总页数
    public int getTotal(){
        if (rows % capacity == 0){
            return rows / capacity;
        } else {
            return rows / capacity + 1;
        }
    }


    //获取页码起始页码
    public int getFrom(){
        int from = current - 2;
        return from > 1 ? from : 1;
    }

    //获取末尾页码
    public int getTo(){
        int to = current + 2;
        int total = getTotal();
        return to > total ? total : to;
    }

    //获取起始行
    public int getOffSet(){
        return (this.current - 1) * capacity;
    }

}
