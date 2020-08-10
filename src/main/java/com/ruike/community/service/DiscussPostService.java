package com.ruike.community.service;

import com.ruike.community.dao.DiscussPostMapper;
import com.ruike.community.entity.DiscussPost;
import org.springframework.stereotype.Service;

import java.util.List;

public interface DiscussPostService {

    //根据用户id查个人的帖子（结果包含被拉黑的）
    List<DiscussPost> findDiscussPostByUserId(int userId, int offset, int limit);

//获取所有帖子（不包含被拉黑的）
    List<DiscussPost> getAllDiscussPostExceptBlackList(int offset, int limit);

    //获取个人帖子的数量（包含被拉黑的）
    int getNumberOfDiscussPostFrom(int userId);

    //获取帖子的数量（不包含被拉黑的帖子）
    int getNumberOfAllDiscussPostExceptBlackList();
}
