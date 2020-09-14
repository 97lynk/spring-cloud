/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kt3.accountservice.reponsitory;

import com.kt3.accountservice.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author 97lynk
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{

     Role findByName(String name);
}
