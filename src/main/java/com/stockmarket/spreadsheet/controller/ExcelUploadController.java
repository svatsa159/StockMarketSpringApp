package com.stockmarket.spreadsheet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.stockmarket.spreadsheet.exception.ExcelFormatErrorException;
import com.stockmarket.spreadsheet.helper.ExcelUploadHelper;
import com.stockmarket.spreadsheet.service.ExcelUploadService;

@RestController
@RequestMapping("/excel_upload")
public class ExcelUploadController {

	@Autowired
	ExcelUploadService excelUploadService;

	@PostMapping
	public ResponseEntity<Object> uploadExcel(@RequestParam("file") MultipartFile file) {
		if (ExcelUploadHelper.hasExcelFormat(file)) {
			try {
				excelUploadService.uploadExcelStockPrices(file);
			} catch (ExcelFormatErrorException e) {

				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
			}
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload correct file");
		}
		return null;

	}
}
