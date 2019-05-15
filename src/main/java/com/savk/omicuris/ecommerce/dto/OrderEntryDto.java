package com.savk.omicuris.ecommerce.dto;

import com.savk.omicuris.ecommerce.entity.Item;
import com.savk.omicuris.ecommerce.entity.Order;
import com.savk.omicuris.ecommerce.entity.OrderEntry;

public class OrderEntryDto {
    private Long id;
    private Order order;
    private Item item;
    private Long quantity;

    public OrderEntryDto() {}

    public OrderEntryDto(OrderEntry orderEntry) {
        this.id = orderEntry.getId();
//        this.order = orderEntry.getOrder();
        this.item = orderEntry.getItem();
        this.quantity = orderEntry.getQuantity();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
