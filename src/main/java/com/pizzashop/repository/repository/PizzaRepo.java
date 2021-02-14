package com.pizzashop.repository.repository;

import com.pizzashop.repository.enity.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PizzaRepo extends JpaRepository<Pizza, Long> {
}
