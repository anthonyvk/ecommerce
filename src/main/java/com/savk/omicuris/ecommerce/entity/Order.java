package com.savk.omicuris.ecommerce.entity;

import com.savk.omicuris.ecommerce.dto.OrderDto;
import com.savk.omicuris.ecommerce.dto.OrderEntryDto;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orderdetail")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date datetime;

    private String paymentmode;
    private double total;   //TODO : May not be needed

//    @ManyToMany(targetEntity = Item.class, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
//    @JoinTable(name = "order_item", joinColumns = {@JoinColumn(name = "order_id")}, inverseJoinColumns = {@JoinColumn(name = "item_id")})
//    private Set<Item> items;

    @OneToMany(targetEntity = OrderEntry.class, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "order_id")
//    @JoinTable(name = "orderentry", joinColumns = {@JoinColumn(name = "order_id")}, inverseJoinColumns = {@JoinColumn(name = "id")})
    private Set<OrderEntry> orderEntries;

    private boolean incart;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    private Customer customer;  //Customer who has ordered

    public Order() {}

    public Order(OrderDto orderDto) {
        this.id = orderDto.getId();
        this.datetime = orderDto.getDatetime();
        this.paymentmode = orderDto.getPaymentmode();
        this.total = orderDto.getTotal();
        this.orderEntries = new HashSet<>();
        for(OrderEntryDto itemDto : orderDto.getOrderEntries()) {
            this.orderEntries.add(new OrderEntry(itemDto));
        }
        this.incart = orderDto.isIncart();
        this.customer = new Customer(orderDto.getCustomer());
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

    public Set<OrderEntry> getOrderEntries() {
        return orderEntries;
    }

    public void setOrderEntries(Set<OrderEntry> orderEntries) {
        this.orderEntries = orderEntries;
    }

    public boolean isIncart() {
        return incart;
    }

    public void setIncart(boolean incart) {
        this.incart = incart;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", datetime=" + datetime +
                ", paymentmode='" + paymentmode + '\'' +
                ", total=" + total +
                ", orderEntries=" + orderEntries +
                ", incart=" + incart +
                ", customer=" + customer +
                '}';
    }
}
