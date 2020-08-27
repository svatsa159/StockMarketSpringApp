package com.stockmarket.backend.dto;

import java.util.Date;

import com.stockmarket.backend.entity.Company;
import com.stockmarket.backend.entity.StockExchange;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IPO_DTO {
	private Company company;
	private StockExchange stockExchange;
	private float price_per_share;
	private long number_of_shares;
	// dd-MM-yyyy HH:mm:ss
	private Date open_date_time;
	private String remarks;
}
