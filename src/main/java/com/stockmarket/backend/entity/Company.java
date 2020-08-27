package com.stockmarket.backend.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "company")
public class Company {
	public Company() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String company_name;

	@Override
	public String toString() {
		return "Company [id=" + id + ", company_name=" + company_name + ", turnover=" + turnover + ", ceo=" + ceo
				+ ", board_of_directors=" + board_of_directors + ", stock_exchanges=" + stock_exchanges + ", sector="
				+ sector + ", write_up=" + write_up + "]";
	}

	private float turnover;
	private String ceo;

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public float getTurnover() {
		return turnover;
	}

	public void setTurnover(float turnover) {
		this.turnover = turnover;
	}

	public String getCeo() {
		return ceo;
	}

	public void setCeo(String ceo) {
		this.ceo = ceo;
	}

	public String getBoard_of_directors() {
		return board_of_directors;
	}

	public void setBoard_of_directors(String board_of_directors) {
		this.board_of_directors = board_of_directors;
	}

	public Sector getSector() {
		return sector;
	}

	public List<StockExchange> getStock_exchanges() {
		return stock_exchanges;
	}

	public void setStock_exchanges(List<StockExchange> stock_exchanges) {
		this.stock_exchanges = stock_exchanges;
	}

	public void setSector(Sector sector) {
		this.sector = sector;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getWrite_up() {
		return write_up;
	}

	public void setWrite_up(String write_up) {
		this.write_up = write_up;
	}

	public long getId() {
		return id;
	}

	public Company(String company_name, float turnover, String ceo, String board_of_directors, String write_up) {
		super();
		this.company_name = company_name;
		this.turnover = turnover;
		this.ceo = ceo;
		this.board_of_directors = board_of_directors;

		this.write_up = write_up;

	}

	private String board_of_directors;
	@ManyToMany(mappedBy = "company", cascade = CascadeType.ALL)

	private List<StockExchange> stock_exchanges = new ArrayList<StockExchange>();

	@ManyToOne()
	@JoinColumn(name = "company_id")
	private Sector sector;
	private String write_up;

}
