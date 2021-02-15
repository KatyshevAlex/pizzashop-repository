package com.pizzashop.repository.service.impl;

import com.pizzashop.repository.enity.Pizza;
import com.pizzashop.repository.enity.Topping;
import com.pizzashop.repository.repository.PizzaRepo;
import com.pizzashop.repository.repository.ToppingRepo;
import com.pizzashop.repository.service.IProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductService implements IProductService {
    @Autowired
    ToppingRepo toppingRepo;

    @Autowired
    PizzaRepo pizzaRepo;

    @Override
    public void saveNewTopping(Topping topping) {
        log.debug("save topping {}", topping);
        toppingRepo.save(topping);
    }

    @Override
    public List<Topping> allToppings() {
        log.debug("searching in the DB all toppings");
        return toppingRepo.findAll();
    }

    @Override
    public Pizza savePizza(Pizza pizza) {
        log.debug("save pizza {}", pizza);
        return pizzaRepo.save(pizza);
    }
}
