package com.ibm.sanity.excel;

import java.awt.Color;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Set;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import cim.ibm.sanity.bean.SanityBean;

public class SanityExcelWriterExample {

	static String[] file = new String[2];

	public static String[] writeExcelSheet(Set<SanityBean> final_sanities)  {
		try {
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("Report Details");
			String[] headings = { "API#", "msName", "msNo", "URI", "Method", "JSON Request", "JSON Response", "Status Code", "Status Report"};
			Row rowOne = sheet.createRow(0);

			Font fontItalic = workbook.createFont();
			fontItalic.setColor(IndexedColors.BLACK.getIndex());
			fontItalic.setItalic(true);
			fontItalic.setBold(true);

			Font fontBold = workbook.createFont();
			fontBold.setColor(IndexedColors.BLACK.getIndex());
			fontBold.setBold(true);

			XSSFColor colGreen = new XSSFColor(Color.GREEN);
			XSSFColor colHeading = new XSSFColor(Color.LIGHT_GRAY);
			XSSFColor colRed = new XSSFColor(Color.RED);

			XSSFCellStyle styleHeading = (XSSFCellStyle) workbook.createCellStyle();
			styleHeading.setFillBackgroundColor(colHeading);
			styleHeading.setFont(fontItalic);
			styleHeading.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			styleHeading.setFillForegroundColor(XSSFColor.toXSSFColor(colHeading));
			styleHeading.setBorderTop(BorderStyle.THICK);
			styleHeading.setBorderBottom(BorderStyle.THICK);
			styleHeading.setBorderLeft(BorderStyle.THICK);
			styleHeading.setBorderRight(BorderStyle.THICK);

			XSSFCellStyle styleGreen = (XSSFCellStyle) workbook.createCellStyle();
			styleGreen.setFillBackgroundColor(colGreen);
			styleGreen.setFont(fontBold);
			styleGreen.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			styleGreen.setFillForegroundColor(XSSFColor.toXSSFColor(colGreen));

			XSSFCellStyle styleRed = (XSSFCellStyle) workbook.createCellStyle();
			styleRed.setFillBackgroundColor(colRed);
			styleRed.setFont(fontBold);
			styleRed.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			styleRed.setFillForegroundColor(XSSFColor.toXSSFColor(colRed));

			for(int i=0;i<9;i++) {
				Cell cell = rowOne.createCell(i);
				cell.setCellValue(headings[i]);
				cell.setCellStyle(styleHeading);
			}
			int i = 1;
			Iterator<SanityBean> itr = final_sanities.iterator();
			while(itr.hasNext()) {
				SanityBean sanity = (SanityBean) itr.next();
				Row row = sheet.createRow(i);
				Cell cell0 = row.createCell(0);
				cell0.setCellValue(sanity.getApiNo());
				Cell cell1 = row.createCell(1);
				cell1.setCellValue(sanity.getMs_Name());
				Cell cell2 = row.createCell(2);
				cell2.setCellValue(sanity.getMsNo());
				Cell cell3 = row.createCell(3);
				cell3.setCellValue(sanity.getUri());
				Cell cell4 = row.createCell(4);
				cell4.setCellValue(sanity.getMethod_type());
				Cell cell5 = row.createCell(5);
				cell5.setCellValue(sanity.getJson_request());
				Cell cell6 = row.createCell(6);
				cell6.setCellValue(sanity.getJson_response());
				Cell cell7 = row.createCell(7);
				cell7.setCellValue(sanity.getStatus_code());
				Cell cell8 = row.createCell(8);
				if(sanity.getStatus_report().equals("SUCCESS")) {
					cell8.setCellValue(sanity.getStatus_report());
					cell8.setCellStyle(styleGreen);
				} else {
					cell8.setCellValue(sanity.getStatus_report());
					cell8.setCellStyle(styleRed);
				}
				i++;
			}
			
			//code to set column width
			sheet.setColumnWidth(0, 8 * 256);
			sheet.setColumnWidth(1, 26 * 256);
			sheet.setColumnWidth(2, 8 * 256);
			sheet.setColumnWidth(3, 65 * 256);
			sheet.setColumnWidth(4, 12 * 256);
			sheet.setColumnWidth(5, 25 * 256);
			sheet.setColumnWidth(6, 25 * 256);
			sheet.setColumnWidth(7, 15 * 256);
			sheet.setColumnWidth(8, 17 * 256);

			//date to be appended with the file name
			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			String formatDateTime = now.format(formatter);
			
			file[0] = "SanityReport-"+formatDateTime+".xlsx";
			file[1] = new String("C:\\SanityReports\\"+file[0]);

			FileOutputStream outputStream = new FileOutputStream(file[1]); 
			workbook.write(outputStream);
			workbook.close();

		} catch (Exception e) {
			System.out.println("Could'nt write");
			System.out.println(e);
		}
		
		System.out.println("Excel Generation Completed");
		return file;

	}

}