package com.savk.omicuris.ecommerce.dto;

import com.savk.omicuris.ecommerce.entity.Order;
import com.savk.omicuris.ecommerce.entity.OrderEntry;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class OrderDto {
    private Long id;
    private Date datetime;
    private String paymentmode;
    private double total;
    private Set<OrderEntryDto> orderEntries;
    private boolean incart;
    private CustomerDto customer;  //Customer who has ordered

    public OrderDto() {}

    public OrderDto(Order order)    {
        this.id = order.getId();
        this.datetime = order.getDatetime();
        this.paymentmode = order.getPaymentmode();
        this.total = order.getTotal();

        Set<OrderEntry> orderEntries = order.getOrderEntries();
        this.orderEntries = new HashSet<>();
        for(OrderEntry orderEntry : orderEntries)  {
            this.orderEntries.add(new OrderEntryDto(orderEntry));
        }

        this.incart = order.isIncart();
        this.customer = new CustomerDto(order.getCustomer());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public String getPaymentmode() {
        return paymentmode;
    }

    public void setPaymentmode(String paymentmode) {
        this.paymentmode = paymentmode;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Set<OrderEntryDto> getOrderEntries() {
        return orderEntries;
    }

    public void setOrderEntries(Set<OrderEntryDto> orderEntries) {
        this.orderEntries = orderEntries;
    }

    public boolean isIncart() {
        return incart;
    }

    public void setIncart(boolean incart) {
        this.incart = incart;
    }

    public CustomerDto getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDto customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "id=" + id +
                ", datetime=" + datetime +
                ", paymentmode='" + paymentmode + '\'' +
                ", total=" + total +
                ", orderEntries=" + orderEntries +
                ", incart=" + incart +
                ", customer=" + customer +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDto orderDto = (OrderDto) o;
        return Double.compare(orderDto.total, total) == 0 &&
                incart == orderDto.incart &&
                Objects.equals(id, orderDto.id) &&
                Objects.equals(datetime, orderDto.datetime) &&
                Objects.equals(paymentmode, orderDto.paymentmode) &&
                Objects.equals(orderEntries, orderDto.orderEntries) &&
                Objects.equals(customer, orderDto.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, datetime, paymentmode, total, orderEntries, incart, customer);
    }
}
