package com.hieu.nguyenquanghieu_finaltest.controller;

import com.hieu.nguyenquanghieu_finaltest.entity.Customer;
import com.hieu.nguyenquanghieu_finaltest.service.CustomerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CustomerController {
    List<Customer> customerList = new ArrayList<Customer>();

    CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("Customers", customerList);
        return "Index";
    }

    @GetMapping("/create")
    public String create(final Model model) {
        model.addAttribute("newCustomer", Customer.builder());
        return "CreateForm";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Customer customer, final Model model) {
        boolean check = false;
        Customer tmpUser = customerService.findByEmail(customer.getEmail());
        if (tmpUser == null) {
            check = customerService.save(customer);
            if (check == true)
                return "redirect:/";
        }
        return "CreateForm";
    }
}
