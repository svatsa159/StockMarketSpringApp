package com.stockmarket.backend.dao;

import java.util.List;

import com.stockmarket.backend.entity.StockExchange;
import com.stockmarket.backend.exception.EntityExists;
import com.stockmarket.backend.exception.EntityNotFound;

public interface StockExchangeDao {
	List<StockExchange> listAllStockExchanges();

	boolean addStockExchange(StockExchange exchange) throws EntityExists;

	boolean deleteStockExchange(StockExchange exchange);

	StockExchange getStockExchangeByName(String name) throws EntityNotFound;
}
