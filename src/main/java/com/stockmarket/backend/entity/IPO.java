package com.stockmarket.backend.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ipo")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IPO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne(fetch = FetchType.EAGER)
	private Company company;
	@OneToOne
	private StockExchange stockExchange;
	private float price_per_share;
	private long number_of_shares;
	// dd-MM-yyyy HH:mm:ss
	private Date open_date_time;
	private String remarks;

}
