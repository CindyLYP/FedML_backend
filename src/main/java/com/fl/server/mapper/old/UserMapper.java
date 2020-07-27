package com.fl.server.mapper.old;


import com.fl.server.object.old.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    User selectByEmail(@Param("email") String email);
    Boolean insert(@Param("user") User user);
    Boolean update(@Param("user") User user);
    Boolean deleteByEmail(@Param("email") String email);
}
