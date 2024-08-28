package com.hieu.nguyenquanghieu_finaltest.service;

import com.hieu.nguyenquanghieu_finaltest.entity.Customer;
import com.hieu.nguyenquanghieu_finaltest.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @Test
    public void should_return_customer_exist_with_this_email() throws ParseException {
        String email = "ghieu@gmail.com";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date createdAtDate = sdf.parse("2020-05-05");
        Customer fakeCus = Customer.builder().id(1).name("Hieu").number("C101").createdAt(createdAtDate).email(email).build();
        Customer expected = fakeCus;

        when(customerRepository.getCustomerByEmail(email)).thenReturn(fakeCus);

        Customer result = customerService.findByEmail(email);

        assertThat(result).isEqualTo(fakeCus);
    }

    @Test
    public void should_return_create_customer_fail_with_this_number() throws ParseException {
        String number = "A123";
        boolean expected = false;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date createdAtDate = sdf.parse("2020-05-05");
        Customer fakeCus = Customer.builder().id(1).name("Hieu").number(number).createdAt(createdAtDate).email("ghieu@gmail.com").build();

        boolean result = customerService.save(fakeCus);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void should_return_create_customer_success_with_this_number() throws ParseException {
        String number = "C123";
        boolean expected = true;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date createdAtDate = sdf.parse("2020-05-05");
        Customer fakeCus = Customer.builder().id(1).name("Hieu").number(number).createdAt(createdAtDate).email("ghieu@gmail.com").build();

        boolean result = customerService.save(fakeCus);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void should_return_all_customer_success() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date createdAtDate = sdf.parse("2020-05-05");
        Customer customer1 = Customer.builder()
                .id(1)
                .name("Hieu")
                .number("C101")
                .createdAt(createdAtDate)
                .email("ghieu@gmail.com")
                .build();

        Customer customer2 = Customer.builder()
                .id(2)
                .name("Duc Anh")
                .number("C102")
                .createdAt(createdAtDate)
                .email("ducanh@gmail.com")
                .build();
        List<Customer> lstFakeCus = new ArrayList<Customer>();
        lstFakeCus.add(customer1);
        lstFakeCus.add(customer2);
        List<Customer> expected = lstFakeCus;

        when(customerRepository.getAllCustomer()).thenReturn(lstFakeCus);

        List<Customer> result = customerService.findAll();

        assertThat(result).isEqualTo(lstFakeCus);
    }
}
