package com.pizzashop.repository.prototype;

import com.pizzashop.repository.enity.Customer;

public class Prototypes {

    public static Customer getCustomer(String login){
        Customer customer = new Customer();
        customer.setLogin(login);
        return customer;
    }
}
