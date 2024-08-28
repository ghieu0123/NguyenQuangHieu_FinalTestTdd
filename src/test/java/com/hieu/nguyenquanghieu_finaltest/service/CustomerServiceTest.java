package com.hieu.nguyenquanghieu_finaltest.service;

import com.hieu.nguyenquanghieu_finaltest.entity.Customer;
import com.hieu.nguyenquanghieu_finaltest.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CustomerServiceTest {
    @Mock //Tạo một mock implementation for Repository
    private CustomerRepository customerRepository;

    @InjectMocks //Inject đối tượng cần test (có sử dụng mock)
    private CustomerService customerService;

    @Test
    public void should_return_customer_does_not_exist_with_this_email(){
        String email = "ghieu@gmail.com";
        String expected = null;

        when(customerRepository.getCustomerByEmail(email)).thenReturn(null);

        Customer result = customerService.findByEmail(email);

        assertThat(result).isEqualTo(null);
    }
}
