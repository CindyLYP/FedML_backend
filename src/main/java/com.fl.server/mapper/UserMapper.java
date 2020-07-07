package com.fl.server.mapper;


import com.fl.server.object.User;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    User selectByEmail(@Param("email") String email);
    Boolean insert(@Param("user") User user);
    Boolean update(@Param("user") User user);
    Boolean deleteByEmail(@Param("email") String email);
}
