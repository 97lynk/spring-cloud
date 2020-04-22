package io.a97lynk.springauthserver.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Role {

    private Long id;

    private RoleName name;

    private List<Account> accounts = new ArrayList<>();

    public enum RoleName {
        USER, ADMIN
    }
}
