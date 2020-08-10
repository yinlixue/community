package com.ruike.community.service.impl;

import com.ruike.community.dao.DiscussPostMapper;
import com.ruike.community.entity.DiscussPost;
import com.ruike.community.service.DiscussPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscussPostServiceImpl implements DiscussPostService {

    @Autowired
    private DiscussPostMapper discussPostMapper;

    @Override
    public List<DiscussPost> findDiscussPostByUserId(int userId, int offset, int limit) {
        assert (userId != 0);
        return discussPostMapper.selectDiscussPostsList(userId, offset, limit);
    }

    @Override
    public List<DiscussPost> getAllDiscussPostExceptBlackList(int offset, int limit) {
        return discussPostMapper.selectDiscussPostsList(0, offset, limit);
    }

    @Override
    public int getNumberOfDiscussPostFrom(int userId) {
        assert (userId != 0);
        return discussPostMapper.selectDiscussPostRows(userId);
    }

    @Override
    public int getNumberOfAllDiscussPostExceptBlackList() {
        return discussPostMapper.selectDiscussPostRows(0);
    }
}
