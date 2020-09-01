package com.stockmarket.spreadsheet.helper;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.stockmarket.spreadsheet.entity.StockPrice;
import com.stockmarket.spreadsheet.exception.ExcelFormatErrorException;

public class ExcelUploadHelper {
	public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

	public static boolean hasExcelFormat(MultipartFile file) {

		if (!TYPE.equals(file.getContentType())) {
			return false;
		}

		return true;
	}

	public static List<StockPrice> getStockPricesFromExcel(InputStream is) throws ExcelFormatErrorException {
		SimpleDateFormat time_format = new SimpleDateFormat("HH:mm:ss");
		List<StockPrice> stockPriceList = new ArrayList<>();
		try {
			Workbook workbook = new XSSFWorkbook(is);
			if (workbook.getNumberOfSheets() < 1) {
				workbook.close();
				throw new ExcelFormatErrorException("No Sheets Found");
			}
			Iterator<Sheet> sheets = workbook.sheetIterator();
			while (sheets.hasNext()) {
				Sheet sheet = sheets.next();
				Iterator<Row> rows = sheet.rowIterator();
				int rowno = 0;

				while (rows.hasNext()) {
					Row row = rows.next();
					if (rowno == 0) {
						rowno++;
						continue;
					}

					try {
						System.out.println(rowno);
						if (row.getCell(0) == null && row.getCell(1) == null && row.getCell(2) == null
								&& row.getCell(3) == null && row.getCell(4) == null) {

							break;
						}
						if (row.getCell(0) == null || row.getCell(1) == null || row.getCell(2) == null
								|| row.getCell(3) == null || row.getCell(4) == null) {
							throw new ExcelFormatErrorException("Format Error on Row number " + rowno);
						}
						String companyCode = row.getCell(0).getStringCellValue().replace('\u00A0', ' ').trim();
						if (companyCode == "") {
							break;
						}
						long company_id = Long.parseLong(companyCode);
						String exchange_name = row.getCell(1).getStringCellValue();
						Double price = row.getCell(2).getNumericCellValue();
						Date date = row.getCell(3).getDateCellValue();
						Date time = time_format.parse(row.getCell(4).getStringCellValue());
						StockPrice stock = new StockPrice(company_id, exchange_name, price, date, time);
						stockPriceList.add(stock);

					} catch (IllegalStateException | ParseException | NumberFormatException e) {
						e.printStackTrace();
						workbook.close();
						throw new ExcelFormatErrorException("Format Error on Row number " + rowno);
					}

					rowno++;
				}

			}
			workbook.close();
			return stockPriceList;

		} catch (IOException e) {

			e.printStackTrace();
			throw new ExcelFormatErrorException("Error reading Excel File");
		}

	}

}