package com.example.demo.xls;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.demo.domain.Car;

public class ToXls {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ToXls.class);

	public static ByteArrayInputStream toXls(List<Car> data) throws IOException {
		
		String[] headers = getHeaders(data.get(0));

		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet();
		workbook.setSheetName(0, "Hoja excel");

		CellStyle headerStyle = workbook.createCellStyle();
		Font font = workbook.createFont();
		font.setBold(true);
		headerStyle.setFont(font);
		headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		HSSFRow headerRow = sheet.createRow(0);
		for (int i = 0; i < headers.length; ++i) {
			String header = headers[i];
			HSSFCell cell = headerRow.createCell(i);
			cell.setCellStyle(headerStyle);
			cell.setCellValue(header);
		}

		for (int i = 0; i < data.size(); ++i) {
			HSSFRow dataRow = sheet.createRow(i + 1);

			dataRow.createCell(0).setCellValue(data.get(i).getId());
			dataRow.createCell(1).setCellValue(data.get(i).getName());
			dataRow.createCell(2).setCellValue(data.get(i).getColour());
			dataRow.createCell(3).setCellValue(data.get(i).getIdBrand());
		}

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		workbook.write(outputStream);
		return new ByteArrayInputStream(outputStream.toByteArray());
	}

	private static String[] getHeaders(Car car) {
		List<String> output = new ArrayList<String>();
		
		Class<?> clazz = car.getClass();

		try {
			for (Field field : clazz.getDeclaredFields()) {
				output.add(field.getName());
			}

		} catch (IllegalArgumentException e) {
			LOGGER.error("Error to generate headers xls");
		}
				
		return output.toArray(new String[output.size()]);
	}
}
