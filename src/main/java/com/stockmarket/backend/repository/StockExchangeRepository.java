package com.stockmarket.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stockmarket.backend.entity.StockExchange;

@Repository
public interface StockExchangeRepository extends JpaRepository<StockExchange, Long> {
	public StockExchange getStockExchangeByName(String name);
}
