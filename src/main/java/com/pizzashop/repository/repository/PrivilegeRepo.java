package com.pizzashop.repository.repository;



import com.pizzashop.repository.enity.security.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PrivilegeRepo extends JpaRepository<Privilege, Long> {

    @Query(value = "SELECT * FROM pizzashop.privileges a WHERE a.privilege_type = ?1 ORDER BY id ASC LIMIT 1",
            nativeQuery = true)
    Privilege findByType(String type);
}
