package com.savk.omicuris.ecommerce.service;

import com.savk.omicuris.ecommerce.dto.OrderDto;
import com.savk.omicuris.ecommerce.entity.*;
import com.savk.omicuris.ecommerce.repository.*;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.util.*;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderEntryRepository orderEntryRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public OrderDto findById(long id) throws Exception  {
        Order order = orderRepository.findById(id).orElse(null);
        if(order == null)    {
            throw new Exception("Order not Found");
        }
        OrderDto orderDto = new OrderDto(order);
        return orderDto;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<OrderDto> findAll() throws Exception  {
        List<Order> orders = orderRepository.findAll();
        List<OrderDto> orderDtos = new ArrayList<>();
        for(Order o : orders)   {
            orderDtos.add(new OrderDto(o));
        }
        return orderDtos;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public OrderDto create(Long custId) throws Exception {
        Customer customer = customerRepository.findById(custId).orElse(null);
        if(customer == null)    {
            throw new Exception("Customer not Found");
        }

        Order order = new Order();
        order.setPaymentmode("");
        order.setIncart(true);
        order.setOrderEntries(new HashSet<OrderEntry>());
        order.setDatetime(new Date());
        order.setCustomer(customer);
        order.setTotal(0);

        order = orderRepository.save(order);
        return new OrderDto(order);
    }


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public OrderDto checkout(long id, String paymentMode) throws Exception  {
        Order order = orderRepository.findById(id).orElse(null);
        if(order == null)    {
            throw new Exception("Order not Found");
        }

        Set<OrderEntry> orderEntries = order.getOrderEntries();
        for(OrderEntry orderEntry : orderEntries)   {
            //Check in Stock
            Item item = orderEntry.getItem();
            Stock stock = stockRepository.findStockByItemId(item.getId());
            long remaining = stock.getAvailable()-orderEntry.getQuantity();
            if((remaining) <= 0)   {
                throw new Exception("Out of stock for Item :: " + item.getName());
            } else {
                stock.setAvailable(remaining);
                stockRepository.save(stock);
            }
        }

        order.setIncart(false);
        order.setPaymentmode(paymentMode);
        order = orderRepository.save(order);
        return new OrderDto(order);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public OrderDto saveOrder(OrderDto orderDto)    {
        Order order = new Order(orderDto);
        order = orderRepository.save(order);
        orderDto.setId(order.getId());
        return orderDto;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public OrderDto addItemsToOrder(Long orderId, Long itemId, Long qty) throws Exception {
        Item item = itemRepository.findById(itemId).orElse(null);
        if(item == null)    {
            throw new Exception("Item not Found");
        }

        Stock stock = stockRepository.findStockByItemId(item.getId());
        if(stock == null)   {
            throw new Exception("Stock not Found");
        }
        if(stock.getAvailable() - qty < 0)  {
            throw new Exception("Out of Stock");
        }

        Order order = orderRepository.findById(orderId).orElse(null);
        if(order == null)    {
            throw new Exception("Order not Found");
        }

        System.out.println(item);
        System.out.println(order);
        System.out.println(stock);

        Set<OrderEntry> itemList = order.getOrderEntries();
        OrderEntry orderEntry = new OrderEntry();
        orderEntry.setItem(item);
//        orderEntry.setOrder(order);
        orderEntry.setQuantity(qty);

        System.out.println(orderEntry);

//        orderEntry = orderEntryRepository.save(orderEntry);

        itemList.add(orderEntry);
        order.setOrderEntries(itemList);
//
        order = orderRepository.save(order);
        return new OrderDto(order);
    }


}
