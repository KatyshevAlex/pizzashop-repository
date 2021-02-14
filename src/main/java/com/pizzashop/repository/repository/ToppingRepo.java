package com.pizzashop.repository.repository;

import com.pizzashop.repository.enity.Topping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToppingRepo  extends JpaRepository<Topping, Long> {
}
