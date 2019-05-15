package com.savk.omicuris.ecommerce;

import com.savk.omicuris.ecommerce.dto.CustomerDto;
import com.savk.omicuris.ecommerce.dto.ItemDto;
import com.savk.omicuris.ecommerce.dto.OrderDto;
import com.savk.omicuris.ecommerce.entity.*;
import com.savk.omicuris.ecommerce.repository.*;
import com.savk.omicuris.ecommerce.service.CustomerService;
import com.savk.omicuris.ecommerce.service.ItemService;
import com.savk.omicuris.ecommerce.service.OrderService;
import com.savk.omicuris.ecommerce.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
public class ECommerceApplication implements CommandLineRunner {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private StockService stockService;

    public static void main(String[] args) {
        SpringApplication.run(ECommerceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception   {
        /*OrderDto orderDto = orderService.findById(3L);
        System.out.println(orderDto);*/

        /*CustomerDto customerDto = new CustomerDto();
        customerDto.setId(5L);

        Set<ItemDto> itemDtos = new HashSet<>();
        itemDtos.add(itemService.findById(10));
        itemDtos.add(itemService.findById(11));
        itemDtos.add(itemService.findById(12));
        itemDtos.add(itemService.findById(13));

        OrderDto orderDto = new OrderDto();
        orderDto.setCustomer(customerDto);
        orderDto.setDatetime(new Date());
        orderDto.setIncart(true);
        orderDto.setItems(itemDtos);
        orderDto.setPaymentmode("CARD-SAVk");
        orderDto.setTotal(234);

        orderService.saveOrder(orderDto);*/

//        OrderDto order = orderService.getOrderById(2);
//        Order order = orderRepository.findById(2L).orElse(null);
//        System.out.println(order);
    }


}
