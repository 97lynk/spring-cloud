package io.a97lynk.springauthserver.service;

import io.a97lynk.springauthserver.entity.Account;
import io.a97lynk.springauthserver.entity.Role;
import io.a97lynk.springauthserver.mapper.AccountMapper;
import io.a97lynk.springauthserver.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Account account = accountMapper.findAccountByUsername(s);
        return new User(account.getUsername(), account.getPassword(), convertRoleToGrantedAuthority(account.getRoles()));
    }

    private static List<GrantedAuthority> convertRoleToGrantedAuthority(List<Role> roles) {
        return roles
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());
    }
}
