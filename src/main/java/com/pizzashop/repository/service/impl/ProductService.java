package com.pizzashop.repository.service.impl;

import com.pizzashop.repository.enity.Pizza;
import com.pizzashop.repository.enity.Topping;
import com.pizzashop.repository.repository.PizzaRepo;
import com.pizzashop.repository.repository.ToppingRepo;
import com.pizzashop.repository.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {
    @Autowired
    ToppingRepo toppingRepo;

    @Autowired
    PizzaRepo pizzaRepo;

    @Override
    public void saveNewTopping(Topping topping) {
        toppingRepo.save(topping);
    }

    @Override
    public List<Topping> allToppings() {
        return toppingRepo.findAll();
    }

    @Override
    public Pizza savePizza(Pizza pizza) {
        return pizzaRepo.save(pizza);
    }
}
