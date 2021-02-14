package com.pizzashop.repository.repository;



import com.pizzashop.repository.enity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface CustomerRepo extends JpaRepository<Customer, Long> {

    @Query(value = "SELECT * FROM pizzashop.customers c WHERE c.email = ?1 ORDER BY id ASC LIMIT 1",
            nativeQuery = true)
    Customer findByEmail(String email);

    @Query(value = "SELECT * FROM pizzashop.customers c WHERE c.login = ?1 ORDER BY id ASC LIMIT 1",
            nativeQuery = true)
    Customer findByLogin(String login);
}
