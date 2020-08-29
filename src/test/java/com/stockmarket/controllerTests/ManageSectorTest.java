package com.stockmarket.controllerTests;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import com.stockmarket.backend.dto.AddSectorWrapper;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = StockMarketApplication.class)
@SpringBootTest
@RunWith(JUnitPlatform.class)
@TestInstance(Lifecycle.PER_CLASS)
public class ManageSectorTest {

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
	public void addSector() throws Exception {
		AddSectorWrapper sector = new AddSectorWrapper("CCC", "CCC - brief");
		this.mockMvc
				.perform(MockMvcRequestBuilders.post("/manage_sector/add/")
						.content(objectMapper.writeValueAsString(sector)).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isAccepted()).andExpect(jsonPath("$.name", is("CCC"))).andReturn();
	}

	@Test
	public void getSectors() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/manage_sector/list/").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$[0].id", is(1)))
				.andExpect(jsonPath("$[0].name", is("ABC"))).andReturn();
	}

	@Test
	public void getCompanyOfSectors() throws Exception {
		String sector_id = "1";
		this.mockMvc
				.perform(MockMvcRequestBuilders.get("/manage_sector/list_companies_by_sector/" + sector_id)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$[0].company_name", is("1-company"))).andReturn();
	}

	@Test
	public void getCompanyOfSectorsError() throws Exception {
		String sector_id = "0";
		this.mockMvc
				.perform(MockMvcRequestBuilders.get("/manage_sector/list_companies_by_sector/" + sector_id)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest()).andExpect(content().string("Sector not found")).andReturn();
	}

}
