package com.ruike.community.service;

import com.ruike.community.CommunityApplication;
import com.ruike.community.entity.DiscussPost;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
class DiscussPostServiceTest {

    @Autowired
    private Logger logger;

    @Autowired
    DiscussPostService discussPostService;

    @Test
    void findDiscussPostByUserId() {
        int userId = 103;
        logger.info("用户{}的帖子：", userId);
        List<DiscussPost> posts = discussPostService.findDiscussPostByUserId(userId, 0, 100);
        for (DiscussPost post : posts) {
            System.out.println(post);
        }

    }

    @Test
    void getAllDiscussPostExceptBlackList() {
        List<DiscussPost> posts = discussPostService.getAllDiscussPostExceptBlackList(0, 100);
        logger.info("所有日志条目（不包含拉黑帖子）：{}", posts.size());
    }

    @Test
    void getNumberOfDiscussPostFrom() {
        int userId = 101;
        int rows = discussPostService.getNumberOfDiscussPostFrom(userId);
        logger.info("用户{}的帖子总共{}条", userId, rows);
    }

    @Test
    void getNumberOfAllDiscussPost() {
        int rows = discussPostService.getNumberOfAllDiscussPostExceptBlackList();
        logger.info("论坛中帖子总数{}", rows);
    }
}