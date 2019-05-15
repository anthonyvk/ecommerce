package com.savk.omicuris.ecommerce.rest;

import com.savk.omicuris.ecommerce.dto.OrderDto;
import com.savk.omicuris.ecommerce.entity.Order;
import com.savk.omicuris.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping
    public List<OrderDto> getAllOrders() throws Exception    {
        return orderService.findAll();
    }

    @PostMapping("/createOrderForCustomer/{custId}")
    public OrderDto createOrder(@PathVariable("custId") Long custId) throws Exception   {
        return orderService.create(custId);
    }

    @GetMapping("/{id}")
    public OrderDto getOrderDetails(@PathVariable("id") Long id) throws Exception   {
        return orderService.findById(id);
    }

    @PutMapping("/{id}/addItemsToTheOrder")
    public OrderDto addItemsToOrder(
            @PathVariable("id") Long orderId,
            @RequestParam("itemId") Long itemId,
            @RequestParam("quantity") Long quantity) throws Exception    {

        return orderService.addItemsToOrder(orderId, itemId, quantity);
    }

    @PutMapping("/{id}/checkout")
    public OrderDto checkout(
            @PathVariable("id") Long orderId,
            @RequestParam("paymentMode") String paymentMode) throws Exception    {

        if(paymentMode == null) {
            paymentMode = "ONLINE";
        }

        return orderService.checkout(orderId, paymentMode);
    }
}
