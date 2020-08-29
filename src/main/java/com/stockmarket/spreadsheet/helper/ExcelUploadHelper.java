package com.stockmarket.spreadsheet.helper;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

	@SuppressWarnings("unused")
	public static List<StockPrice> getStockPricesFromExcel(InputStream is) throws ExcelFormatErrorException {
		SimpleDateFormat time_format = new SimpleDateFormat("HH:mm:ss");
		SimpleDateFormat date_format = new SimpleDateFormat("dd-MM-yyyy");
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
//						String company_code = row.getCell(0).getStringCellValue();
//						if (company_code == "") {
//							break;
//						}
//						company_code = company_code.substring(0, company_code.length() - 1);
//						long company_id = Long.parseLong(company_code);
						if (row.getCell(0) == null && row.getCell(1) == null && row.getCell(2) == null
								&& row.getCell(3) == null && row.getCell(4) == null) {
							break;
						}
						if (row.getCell(0) == null || row.getCell(1) == null || row.getCell(2) == null
								|| row.getCell(3) == null || row.getCell(4) == null) {
							throw new ExcelFormatErrorException("Format Error");
						}
						long company_id = (long) row.getCell(0).getNumericCellValue();
						String exchange_name = row.getCell(1).getStringCellValue();
						Double price = row.getCell(2).getNumericCellValue();
						System.out.println(price);
						Date date = row.getCell(3).getDateCellValue();
						Date time = time_format.parse(row.getCell(4).getStringCellValue());
						System.out.println(date);
					} catch (IllegalStateException | ParseException | NumberFormatException e) {
						e.printStackTrace();
						throw new ExcelFormatErrorException("Format Error");
					}

					rowno++;
				}
//				System.out.println(sheet.getSheetName());
				workbook.close();
			}

		} catch (IOException e) {

			e.printStackTrace();
		}

		return null;

	}

}