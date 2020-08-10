package com.ruike.community.dao;

import com.ruike.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Primary;

import java.util.List;


@Mapper
@Primary
public interface DiscussPostMapper {
    //userId用于个人主页的帖子查询
    //offset limit 用于分页
    List<DiscussPost> selectDiscussPostsList(int userId, int offset, int limit);

    //如果只有一个参数，且需要用动态sql<if>判断，那么必须要有别名
    // 像上面有三个参数的就可以不加
    int selectDiscussPostRows(int userId);
}
