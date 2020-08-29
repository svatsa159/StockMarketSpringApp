package com.stockmarket.spreadsheet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stockmarket.spreadsheet.entity.StockPrice;

public interface StockPriceRepository extends JpaRepository<StockPrice, Long> {

}
