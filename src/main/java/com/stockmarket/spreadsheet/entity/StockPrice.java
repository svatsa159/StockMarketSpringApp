package com.stockmarket.spreadsheet.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StockPrice {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	public StockPrice(long companyCode, String stockExchangeName, Double price, Date date, Date time) {
		super();
		this.companyCode = companyCode;
		this.stockExchangeName = stockExchangeName;
		this.price = price;
		this.date = date;
		this.time = time;
	}

	private long companyCode;
	private String stockExchangeName;
	private Double price;
	private Date date;
	private Date time;
}
