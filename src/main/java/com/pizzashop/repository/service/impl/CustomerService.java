package com.pizzashop.repository.service.impl;


import com.pizzashop.repository.enity.Customer;
import com.pizzashop.repository.enity.Topping;
import com.pizzashop.repository.repository.CustomerRepo;
import com.pizzashop.repository.repository.ToppingRepo;
import com.pizzashop.repository.service.ICustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService implements ICustomerService {
    @Autowired
    CustomerRepo customerRepo;

    @Override
    public Customer findCustomer(Customer customer) {
        Customer response = null;
        if(customer.getEmail() != null){
            response = customerRepo.findByEmail(customer.getEmail());
        } else if (customer.getLogin() != null) {
            response = customerRepo.findByLogin(customer.getLogin());
        }

        return response;
    }

    public Customer updateCustomer(Customer customer){
        if (customer != null){
            return customerRepo.save(customer);
        }
        return null;
    }
}
