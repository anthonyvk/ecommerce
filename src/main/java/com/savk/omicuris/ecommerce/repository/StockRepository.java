package com.savk.omicuris.ecommerce.repository;

import com.savk.omicuris.ecommerce.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
    public Stock findStockByItemId(@Param("id") Long id);
}
