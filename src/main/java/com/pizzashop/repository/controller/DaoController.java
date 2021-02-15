package com.pizzashop.repository.controller;

import com.pizzashop.repository.annotations.LogExecutionTime;
import com.pizzashop.repository.enity.Customer;
import com.pizzashop.repository.enity.Pizza;
import com.pizzashop.repository.enity.Topping;
import com.pizzashop.repository.service.ICustomerService;
import com.pizzashop.repository.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.Cacheable;

import java.util.List;

@RestController
@RequestMapping("/dao")
@CacheConfig(cacheNames = {"toppings", "pizzas", "customers"})
public class DaoController {
    @Autowired
    ICustomerService customerService;
    @Autowired
    IProductService productService;

    @PostMapping("/add-new-topping")
    @LogExecutionTime
    @CacheEvict("toppings")
    public void addTopping(@RequestBody Topping topping){
        productService.saveNewTopping(topping);
    }

    @GetMapping("/toppings")
    @LogExecutionTime
    @Cacheable("toppings")
    public List<Topping> getAllToppings(){
        return productService.allToppings();
    }

    @PostMapping("/save-pizza")
    @LogExecutionTime
    @CacheEvict("pizzas")
    public Pizza savePizza(@RequestBody Pizza pizza){
        return productService.savePizza(pizza);
    }

    @PostMapping("/find-customer")
    @LogExecutionTime
    @Cacheable("customers")
    public Customer getCustomer(@RequestBody Customer customer){
        return customerService.findCustomer(customer);
    }

    @PutMapping("/update-customer")
    @LogExecutionTime
    @CacheEvict("customers")
    public Customer updateCustomer(@RequestBody Customer customer){
        return customerService.updateCustomer(customer);
    }

    @PostMapping("/create-customer")
    @LogExecutionTime
    @CacheEvict("customers")
    public Customer createCustomer(@RequestBody Customer customer){
        return customerService.updateCustomer(customer);
    }
}
