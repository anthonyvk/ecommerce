package com.savk.omicuris.ecommerce.entity;

import com.savk.omicuris.ecommerce.dto.OrderEntryDto;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "orderentry")
public class OrderEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

//    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
//    private Order order;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    private Item item;

    private Long quantity;

    public OrderEntry() {}

    public OrderEntry(OrderEntryDto orderEntry) {
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

//    public Order getOrder() {
//        return order;
//    }

//    public void setOrder(Order order) {
//        this.order = order;
//    }

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

    @Override
    public String toString() {
        return "OrderEntry{" +
                "id=" + id +
                ", item=" + item +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderEntry that = (OrderEntry) o;
        return Objects.equals(id, that.id) &&
//                Objects.equals(order, that.order) &&
                Objects.equals(item, that.item) &&
                Objects.equals(quantity, that.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, item, quantity);
    }
}
