package com.stockmarket.backend.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "stockexchange")
public class StockExchange {
	public StockExchange() {
	}

	public StockExchange(String name, String brief, String address, String remarks) {
		super();
		this.name = name;
		this.brief = brief;
		this.address = address;
		this.remarks = remarks;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "company_stock_exchange", joinColumns = @JoinColumn(name = "company_id"), inverseJoinColumns = @JoinColumn(name = "stockexchange_id"))
	private List<Company> company;

	@Override
	public String toString() {
		return "StockExchange [id=" + id + ", company=" + company + ", name=" + name + ", brief=" + brief + ", address="
				+ address + ", remarks=" + remarks + "]";
	}

	public List<Company> getCompany() {
		return company;
	}

	public void setCompany(List<Company> company) {
		this.company = company;
	}

	@Column(unique = true)
	private String name;
	private String brief;
	private String address;
	private String remarks;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getaddress() {
		return address;
	}

	public void setaddress(String address) {
		this.address = address;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public long getId() {
		return id;
	}
}
