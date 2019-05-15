package com.savk.omicuris.ecommerce.dto;

import com.savk.omicuris.ecommerce.entity.Item;
import com.savk.omicuris.ecommerce.entity.Stock;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

public class StockDto {
    private Long id;
    private Item item;
    private long available;

    public StockDto() {}

    public StockDto(Stock stock) {
        this.id = stock.getId();
        this.item = stock.getItem();
        this.available = stock.getAvailable();
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
        return "StockDto{" +
                "id=" + id +
                ", item=" + item +
                ", available=" + available +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StockDto stockDto = (StockDto) o;
        return available == stockDto.available &&
                Objects.equals(id, stockDto.id) &&
                Objects.equals(item, stockDto.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, item, available);
    }
}
