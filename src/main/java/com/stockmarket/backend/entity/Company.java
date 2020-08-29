package com.stockmarket.backend.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

	private double turnover;
	private String ceo;

	private String board_of_directors;

	@ElementCollection
	private List<String> stock_exchanges = new ArrayList<String>();

	@ManyToOne(fetch = FetchType.EAGER)
//	@JoinColumn(name = "company_id")
	@JsonIgnore
	private Sector sector;
	private String write_up;

	@OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<IPO> ipos;

	@Override
	public String toString() {
		return "Company [id=" + id + ", company_name=" + company_name + ", turnover=" + turnover + ", ceo=" + ceo
				+ ", board_of_directors=" + board_of_directors + ", stock_exchanges=" + stock_exchanges + ", write_up="
				+ write_up + ", ipos=" + ipos + "]";
	}

}
