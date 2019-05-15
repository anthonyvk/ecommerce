package com.savk.omicuris.ecommerce.repository;

import com.savk.omicuris.ecommerce.entity.OrderEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderEntryRepository extends JpaRepository<OrderEntry, Long> {
}
