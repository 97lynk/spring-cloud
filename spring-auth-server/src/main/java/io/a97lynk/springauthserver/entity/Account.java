package io.a97lynk.springauthserver.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter @NoArgsConstructor
public class Account {

    private int id;

    private String username;

    private String password;

    private String fullName;

    private List<Role> roles = new ArrayList<>();

    private String tenantId;

}
