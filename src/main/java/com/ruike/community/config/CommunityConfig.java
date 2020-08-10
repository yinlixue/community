package com.ruike.community.config;

import com.ruike.community.dao.DiscussPostMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommunityConfig {

    @Bean
    public Logger logger(){
        return LoggerFactory.getLogger("Unit Test");
    }
}
