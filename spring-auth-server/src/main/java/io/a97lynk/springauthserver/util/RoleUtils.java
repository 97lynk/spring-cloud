package io.a97lynk.springauthserver.util;

import org.apache.ibatis.jdbc.SQL;

public class RoleUtils {

    public String findRoleByAccountId() {
        return new SQL()
                .SELECT("R.*")
                .FROM("Role R", "role_account AR")
                .WHERE("R.id = AR.role_id", "AR.account_id = #{accountId}")
                .toString();
    }
}
