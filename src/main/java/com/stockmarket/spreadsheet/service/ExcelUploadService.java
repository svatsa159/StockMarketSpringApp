package com.stockmarket.spreadsheet.service;

import org.springframework.web.multipart.MultipartFile;

import com.stockmarket.spreadsheet.exception.ExcelFormatErrorException;

public interface ExcelUploadService {

	void uploadExcelStockPrices(MultipartFile file) throws ExcelFormatErrorException;

}
