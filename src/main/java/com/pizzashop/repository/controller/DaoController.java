package com.pizzashop.repository.controller;

import com.pizzashop.repository.enity.Customer;
import com.pizzashop.repository.enity.Pizza;
import com.pizzashop.repository.enity.Topping;
import com.pizzashop.repository.service.ICustomerService;
import com.pizzashop.repository.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dao")
public class DaoController {
    @Autowired
    ICustomerService customerService;
    @Autowired
    IProductService productService;

    @PostMapping("/add-new-topping")
    public void addTopping(@RequestBody Topping topping){
        productService.saveNewTopping(topping);
    }

    @GetMapping("/toppings")
    public List<Topping> getAllToppings(){
        return productService.allToppings();
    }

    @PostMapping("/save-pizza")
    public Pizza savePizza(@RequestBody Pizza pizza){
        return productService.savePizza(pizza);
    }

    @PostMapping("/find-customer")
    public Customer getCustomer(@RequestBody Customer customer){
        return customerService.findCustomer(customer);
    }

    @PutMapping("/update-customer")
    public Customer updateCustomer(@RequestBody Customer customer){
        return customerService.updateCustomer(customer);
    }

    @PostMapping("/create-customer")
    public Customer createCustomer(@RequestBody Customer customer){
        return customerService.updateCustomer(customer);
    }
}
