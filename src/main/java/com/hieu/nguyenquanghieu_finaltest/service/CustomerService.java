package com.hieu.nguyenquanghieu_finaltest.service;

import com.hieu.nguyenquanghieu_finaltest.entity.Customer;
import com.hieu.nguyenquanghieu_finaltest.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> findAll() {
        return customerRepository.getAllCustomer();
    }

    public boolean save(Customer customer) {
        if(customer.getNumber().charAt(0) != 'C')
            return false;
        else {
            customerRepository.createCustomer(customer);
            return true;
        }
    }

    public Customer findByEmail(String email) {
        return customerRepository.getCustomerByEmail(email);
    }
}
