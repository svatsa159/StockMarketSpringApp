package com.stockmarket.backend.service;

import java.util.List;

import com.stockmarket.backend.dto.CompanyDTO;
import com.stockmarket.backend.entity.StockExchange;
import com.stockmarket.backend.exception.EntityExists;
import com.stockmarket.backend.exception.EntityNotFound;

public interface StockExchangeService {
	public List<StockExchange> getAllStockExchanges();

	public boolean addStockExchange(StockExchange exchange) throws EntityExists;

	public boolean removeStockExchange(String name) throws EntityNotFound;

	List<CompanyDTO> getCompaniesByStockExchange(String name);
}
