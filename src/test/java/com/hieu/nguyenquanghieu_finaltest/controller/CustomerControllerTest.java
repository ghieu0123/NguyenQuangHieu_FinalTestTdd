package com.hieu.nguyenquanghieu_finaltest.controller;

import com.hieu.nguyenquanghieu_finaltest.entity.Customer;
import com.hieu.nguyenquanghieu_finaltest.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest {

    @MockBean
    CustomerService customerService;

    @Autowired
    private MockMvc mockMvc;

    private Customer customer;

    @BeforeEach
    public void setup() {
        customer = Customer.builder()
                .id(1)
                .name("Hieu")
                .email("hieu@gmail.com")
                .build();
    }

    @Test
    public void should_return_index_view() throws Exception {
        this.mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("Index"))
                .andExpect(model().attributeExists("Customers"))
                .andDo(print());
    }

    @Test
    public void should_return_create_form_view() throws Exception {
        this.mockMvc.perform(get("/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("CreateForm"))
                .andExpect(model().attributeExists("newCustomer"))
                .andDo(print());
    }

    @Test
    public void should_create_new_customer_and_redirect_to_index() throws Exception {
        Mockito.when(customerService.findByEmail(anyString())).thenReturn(null);
        Mockito.when(customerService.save(any(Customer.class))).thenReturn(true);

        this.mockMvc.perform(post("/create")
                        .param("name", customer.getName())
                        .param("email", customer.getEmail()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"))
                .andDo(print());
    }

    @Test
    public void should_return_create_form_if_customer_exists() throws Exception {
        Mockito.when(customerService.findByEmail(anyString())).thenReturn(customer);

        this.mockMvc.perform(post("/create")
                        .param("name", customer.getName())
                        .param("email", customer.getEmail()))
                .andExpect(status().isOk())
                .andExpect(view().name("CreateForm"))
                .andDo(print());
    }

    @Test
    public void should_return_create_form_if_save_fails() throws Exception {
        Mockito.when(customerService.findByEmail(anyString())).thenReturn(null);
        Mockito.when(customerService.save(any(Customer.class))).thenReturn(false);

        this.mockMvc.perform(post("/create")
                        .param("name", customer.getName())
                        .param("email", customer.getEmail()))
                .andExpect(status().isOk())
                .andExpect(view().name("CreateForm"))
                .andDo(print());
    }
}
