package com.stockmarket.backend.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
@Table(name = "company")
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String company_name;

	private float turnover;
	private String ceo;

	public Company(String company_name, float turnover, String ceo, String board_of_directors, String write_up) {
		super();
		this.company_name = company_name;
		this.turnover = turnover;
		this.ceo = ceo;
		this.board_of_directors = board_of_directors;

		this.write_up = write_up;

	}

	private String board_of_directors;

	@ElementCollection
	private List<String> stock_exchanges = new ArrayList<String>();

	@ManyToOne()
	@JoinColumn(name = "company_id")
	private Sector sector;
	private String write_up;

}
