package com.ruike.community.service.impl;

import com.ruike.community.service.AlphaService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
@Primary
public class AlphaServiceSingleThreadImpl implements AlphaService {

    public AlphaServiceSingleThreadImpl(){
        System.out.println("构造" + this.getClass() + "实例");
    }

    @Override
    @PostConstruct
    public void init() {
        System.out.println(this.getClass() + "实例执行init方法！");
    }

    @Override
    @PreDestroy
    public void destory() {
        System.out.println("销毁" + this.getClass() + "实例");
    }
}
