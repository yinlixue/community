package com.ruike.community.dao;

import com.ruike.community.CommunityApplication;
import com.ruike.community.entity.DiscussPost;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
class DiscussPostMapperTest {

    @Autowired
    DiscussPostMapper discussPostMapper;


    Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Test
    void selectDiscussPostsList() {
        System.out.println("当userId==0时：");
        List<DiscussPost> discussPostList = discussPostMapper.selectDiscussPostsList(0, 0, 10);
        for(DiscussPost discussPost : discussPostList) {
            System.out.println(discussPost);
        }

        System.out.println("当userId!=0时：");
        discussPostList = discussPostMapper.selectDiscussPostsList(110, 0, 10);
        for(DiscussPost discussPost : discussPostList) {
            System.out.println(discussPost);
        }
    }

    @Test
    void selectDiscussPostRows() {
        LOGGER.info("userId==0时:");
        int rows = discussPostMapper.selectDiscussPostRows(0);
        System.out.println(rows);
        LOGGER.info("总行数是{}", rows);

        int userId = 101;
        LOGGER.info("userId=={}时:", userId);
        rows = discussPostMapper.selectDiscussPostRows(userId);
        System.out.println(rows);
        LOGGER.info("用户{}的帖子数量是(包含被拉黑的){}", userId, rows);
    }
}