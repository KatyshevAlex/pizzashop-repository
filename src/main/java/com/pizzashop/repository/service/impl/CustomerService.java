package com.pizzashop.repository.service.impl;


import com.pizzashop.repository.enity.Customer;
import com.pizzashop.repository.repository.CustomerRepo;
import com.pizzashop.repository.service.ICustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomerService implements ICustomerService {
    @Autowired
    CustomerRepo customerRepo;

    @Override
    public Customer findCustomer(Customer customer) {
        Customer response = null;
        if(customer.getEmail() != null){
            log.debug("searching in the DB by email");
            response = customerRepo.findByEmail(customer.getEmail());
        } else if (customer.getLogin() != null) {
            log.debug("searching in the DB by login");
            response = customerRepo.findByLogin(customer.getLogin());
        }

        return response;
    }

    public Customer updateCustomer(Customer customer){
        if (customer != null){
            log.debug("save customer {}", customer.toString());
            return customerRepo.save(customer);
        }
        return null;
    }
}
