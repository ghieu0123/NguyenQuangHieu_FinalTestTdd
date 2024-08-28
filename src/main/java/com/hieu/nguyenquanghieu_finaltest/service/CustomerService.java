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

    public void save(Customer customer) {
        customerRepository.createCustomer(customer);
    }

    public Customer findByEmail(String email) {
        return customerRepository.getCustomerByEmail(email);
    }
}
