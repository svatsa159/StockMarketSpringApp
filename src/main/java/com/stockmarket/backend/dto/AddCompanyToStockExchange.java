package com.stockmarket.backend.dto;

import java.util.List;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AddCompanyToStockExchange {
	public long company_id;
	public List<String> stock_exchange_list;
}
