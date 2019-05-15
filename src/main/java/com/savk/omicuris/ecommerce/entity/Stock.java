package com.savk.omicuris.ecommerce.entity;

import com.savk.omicuris.ecommerce.dto.StockDto;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "stock")
@NamedQueries(
        @NamedQuery(name = "Stock.findStockByItemId", query = "select s from Stock s where s.item.id = :id")
)
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    @OneToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    private Item item;

    private long available;

    public Stock() {}

    public Stock(StockDto stockDto) {
        this.id = stockDto.getId();
        this.item = stockDto.getItem();
        this.available = stockDto.getAvailable();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public long getAvailable() {
        return available;
    }

    public void setAvailable(long available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", item=" + item +
                ", available=" + available +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stock stock = (Stock) o;
        return available == stock.available &&
                Objects.equals(id, stock.id) &&
                Objects.equals(item, stock.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, item, available);
    }
}
