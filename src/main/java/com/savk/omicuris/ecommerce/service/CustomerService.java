package com.savk.omicuris.ecommerce.service;

import com.savk.omicuris.ecommerce.dto.CustomerDto;
import com.savk.omicuris.ecommerce.entity.Customer;
import com.savk.omicuris.ecommerce.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public CustomerDto findById(long id) throws Exception   {
        Customer customer = customerRepository.findById(id).orElse(null);
        if(customer == null)    {
            throw new Exception("Customer not Found");
        }
        return new CustomerDto(customer);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<CustomerDto> findAll()   {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerDto> customerDtos = new ArrayList<>();
        for(Customer customer : customers)  {
            customerDtos.add(new CustomerDto(customer));
        }
        return customerDtos;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public CustomerDto findBySsn(String ssn)   {
        Customer customer = null;// = customerRepository.findBySsn().orElse(null);
        return new CustomerDto(customer);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public CustomerDto save(CustomerDto customerDto)   {
        Customer customer = new Customer(customerDto);
        customer = customerRepository.save(customer);
        customerDto.setId(customer.getId());
        return customerDto;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void delete(Long id) throws Exception   {
        Customer customer = customerRepository.findById(id).orElse(null);
        if(customer == null)    {
            throw new Exception("Customer not Found");
        }
        customerRepository.delete(customer);
    }
}
