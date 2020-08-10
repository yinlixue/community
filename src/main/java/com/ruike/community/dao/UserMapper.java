package com.ruike.community.dao;

import com.ruike.community.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {

    User selectUserById(int id);

    User selectUserByName(String username);

    User selectUserByEmail(String email);

    int insertUser(User user);

    int updateStatus(int id, int status);

    int updateHeader(int id, String header);

    int updatePassword(int id, String password);
}
