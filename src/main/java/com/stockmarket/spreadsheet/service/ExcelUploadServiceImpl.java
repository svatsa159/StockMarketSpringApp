package com.stockmarket.spreadsheet.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.stockmarket.spreadsheet.entity.StockPrice;
import com.stockmarket.spreadsheet.exception.ExcelFormatErrorException;
import com.stockmarket.spreadsheet.helper.ExcelUploadHelper;

@Service
public class ExcelUploadServiceImpl implements ExcelUploadService {

	@Override
	public void uploadExcelStockPrices(MultipartFile file) throws ExcelFormatErrorException {
		try {
//			throw new IOException();
			@SuppressWarnings("unused")
			List<StockPrice> uploadPrices = ExcelUploadHelper.getStockPricesFromExcel(file.getInputStream());
		} catch (IOException e) {
			throw new ExcelFormatErrorException("Excel Sheet Could not be stored ");
		}

	}

}
