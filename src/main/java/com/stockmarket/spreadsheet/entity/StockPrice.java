package com.stockmarket.spreadsheet.entity;

import java.util.Date;

import javax.persistence.Entity;
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
	private long id;
	private long companyCode;
	private String stockExchangeName;
	private Double price;
	private Date date;
	private Date time;
}
