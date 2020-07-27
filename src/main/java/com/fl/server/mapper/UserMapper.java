package com.fl.server.mapper;

import com.fl.server.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

public interface UserMapper {
    ArrayList<User> getAllUser();
    ArrayList<User> selectByType(@Param("userType") String userType);
    ArrayList<User> selectByAccount(@Param("userAccount") String userAccount);
    boolean insert(@Param("user") User user);
    boolean update(@Param("user") User user);
    boolean delete(@Param("userAccount") String userAccount);
}
