package com.savk.omicuris.ecommerce.rest;

import com.savk.omicuris.ecommerce.dto.CustomerDto;
import com.savk.omicuris.ecommerce.entity.Customer;
import com.savk.omicuris.ecommerce.repository.CustomerRepository;
import com.savk.omicuris.ecommerce.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping("")
    public List<CustomerDto> getAllCustomers() {
        return customerService.findAll();
    }

    @GetMapping("/{id}")
    public CustomerDto findById(@PathVariable("id") Long id) throws Exception    {
        return customerService.findById(id);
    }

    @PostMapping
    public CustomerDto persistCustomer(@RequestBody CustomerDto customerDto)    {
        return customerService.save(customerDto);
    }

    @PutMapping
    public CustomerDto updateCustomer(@RequestBody CustomerDto customerDto)    {
        return customerService.save(customerDto);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable("id") Long id) throws Exception {
        customerService.delete(id);
    }
}
