package com.pizzashop.repository.service;

import com.pizzashop.repository.enity.Pizza;
import com.pizzashop.repository.enity.Topping;

import java.util.List;

public interface IProductService {
    void saveNewTopping(Topping topping);
    List<Topping> allToppings();
    Pizza savePizza(Pizza pizza);

}
