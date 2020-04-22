package io.a97lynk.springauthserver.mapper;

import io.a97lynk.springauthserver.entity.Account;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
@Service
public interface AccountMapper {

    @Select("SELECT * FROM Account WHERE username = #{username}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "roles", column = "id", javaType = List.class,
                    many = @Many(select = "io.a97lynk.springauthserver.mapper.RoleMapper.findAllByAccountId"))
    })
    Account findAccountByUsername(String username);
}
