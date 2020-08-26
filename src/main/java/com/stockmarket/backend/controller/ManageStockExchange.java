package com.stockmarket.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stockmarket.backend.entity.StockExchange;
import com.stockmarket.backend.exception.EntityExists;
import com.stockmarket.backend.exception.EntityNotFound;
import com.stockmarket.backend.service.StockExchangeServiceImpl;

@RestController
@RequestMapping("/manage_stock_exchange")
public class ManageStockExchange {

	@Autowired
	StockExchangeServiceImpl stockExchangeServiceImpl;

	@GetMapping(path = "/list")
	public List<StockExchange> getAllExchanges() {
		return stockExchangeServiceImpl.getAllStockExchanges();
	}

	@PostMapping(consumes = "application/json", path = "/add")
	public ResponseEntity<Object> addExchange(@RequestBody StockExchange exchange) {
		try {
			stockExchangeServiceImpl.addStockExchange(exchange);
		} catch (EntityExists e) {

			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok(null);
	}

	@DeleteMapping(consumes = "text/plain", path = "/remove")
	public ResponseEntity<Object> removeExchange(@RequestBody String name) {
		try {
			stockExchangeServiceImpl.removeStockExchange(name);
		} catch (EntityNotFound e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok(null);
	}
}
