package com.pizzashop.repository.service;


import com.pizzashop.repository.enity.Customer;

public interface ICustomerService {
    Customer findCustomer(Customer customer);
    Customer updateCustomer(Customer customer);
}
