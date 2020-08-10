package com.ruike.community.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Date;

/*此类是一个配置类*/
@Configuration
public class AlphaConfig {

    /*1.这个方法返回的对象将被作为一个Bean装配到Spring容器中
    * 2.方法的名字就是Bean的名字*/
    @Bean
    public SimpleDateFormat simpleDateFormat(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }
}
