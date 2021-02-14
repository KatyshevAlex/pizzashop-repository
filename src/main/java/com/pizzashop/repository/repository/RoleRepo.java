package com.pizzashop.repository.repository;



import com.pizzashop.repository.enity.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo  extends JpaRepository<Role,Long> {

    @Query(value = "SELECT * FROM pizzashop.roles a WHERE a.role_type = CONCAT('ROLE_', ?1) ORDER BY id ASC LIMIT 1",
            nativeQuery = true)
    Role findByRoleType(String roleTypeString);
}
