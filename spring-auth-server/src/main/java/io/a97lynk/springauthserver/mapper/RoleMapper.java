package io.a97lynk.springauthserver.mapper;

import io.a97lynk.springauthserver.entity.Role;
import io.a97lynk.springauthserver.util.RoleUtils;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
@Service
public interface RoleMapper {

    @SelectProvider(value = RoleUtils.class, method = "findRoleByAccountId")
    List<Role> findAllByAccountId(@Param("accountId") Integer id);
}
