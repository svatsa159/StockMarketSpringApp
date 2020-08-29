package com.stockmarket.controllerTests;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stockmarket.StockMarketApplication;
import com.stockmarket.backend.dto.AddCompanyToStockExchange;
import com.stockmarket.backend.dto.AddCompanyWrapper;
import com.stockmarket.backend.entity.Company;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = StockMarketApplication.class)
@SpringBootTest
@RunWith(JUnitPlatform.class)
@TestInstance(Lifecycle.PER_CLASS)
public class ManageCompanyTest {

	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext wac;
	@Autowired
	private ObjectMapper objectMapper;

	@BeforeAll
	public void setUp() {

		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void addCompany() throws Exception {
		Company company = new Company();
		company.setCompany_name("Company 2");
		company.setCeo("2-ceo");
		company.setTurnover(300.0);
		company.setWrite_up("2-writeup");
		company.setBoard_of_directors("2-bod");
		AddCompanyWrapper wrapper = new AddCompanyWrapper(company, 1);
		this.mockMvc
				.perform(MockMvcRequestBuilders.post("/manage_company/add/")
						.content(objectMapper.writeValueAsString(wrapper)).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isAccepted()).andExpect(jsonPath("$.company_name", is("Company 2")))
				.andExpect(jsonPath("$.turnover", is(300.0))).andReturn();
	}

	@Test
	public void addCompanyError() throws Exception {
		Company company = new Company();
		company.setCompany_name("Company 2");
		company.setCeo("2-ceo");
		company.setTurnover(300.0);
		company.setWrite_up("2-writeup");
		company.setBoard_of_directors("2-bod");
		AddCompanyWrapper wrapper = new AddCompanyWrapper(company, 0);
		this.mockMvc
				.perform(MockMvcRequestBuilders.post("/manage_company/add/")
						.content(objectMapper.writeValueAsString(wrapper)).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest()).andExpect(content().string("Sector not found")).andReturn();
	}

	@Test
	public void addCompanyToStockExchange() throws Exception {
		List<String> exchange = new ArrayList<String>();
		exchange.add("ABC");
		AddCompanyToStockExchange wrapper = new AddCompanyToStockExchange(1, exchange);
		this.mockMvc.perform(MockMvcRequestBuilders.post("/manage_company/add_to_stock_exchange/")
				.content(objectMapper.writeValueAsString(wrapper)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
	}

	@Test
	public void addCompanyToStockExchangeCompanyNotFoundError() throws Exception {
		List<String> exchange = new ArrayList<String>();
		exchange.add("ABC");
		AddCompanyToStockExchange wrapper = new AddCompanyToStockExchange(0, exchange);
		this.mockMvc
				.perform(MockMvcRequestBuilders.post("/manage_company/add_to_stock_exchange/")
						.content(objectMapper.writeValueAsString(wrapper)).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest()).andExpect(content().string("Company not found")).andReturn();
	}

	@Test
	public void addCompanyToStockExchange_ExchangeNotFoundError() throws Exception {
		List<String> exchange = new ArrayList<String>();
		exchange.add("Unexisting Exchange Code");
		AddCompanyToStockExchange wrapper = new AddCompanyToStockExchange(1, exchange);
		this.mockMvc
				.perform(MockMvcRequestBuilders.post("/manage_company/add_to_stock_exchange/")
						.content(objectMapper.writeValueAsString(wrapper)).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest()).andExpect(content().string("Exchange not found")).andReturn();
	}

	@Test
	public void getCompanyById() throws Exception {
		String company_id = "1";
		this.mockMvc
				.perform(MockMvcRequestBuilders.get("/manage_company/get_by_id/" + company_id)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.company_name", is("1-company")))
				.andExpect(jsonPath("$.turnover", is(1000.0))).andReturn();

	}

	@Test
	public void getCompanyByIdError() throws Exception {
		String company_id = "0";
		this.mockMvc.perform(MockMvcRequestBuilders.get("/manage_company/get_by_id/" + company_id)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andReturn();

	}

	@Test
	public void getCompanyNameByPatter() throws Exception {
		String pattern = "mpa";
		this.mockMvc
				.perform(MockMvcRequestBuilders.post("/manage_company/get_by_pattern").param("pattern", pattern)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$[0]s", is("1-company"))).andReturn();

	}

}
