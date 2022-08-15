package io.a97lynk.accountservice.reponsitory;

import io.a97lynk.accountservice.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author 97lynk
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

     Role findByName(String name);
}
