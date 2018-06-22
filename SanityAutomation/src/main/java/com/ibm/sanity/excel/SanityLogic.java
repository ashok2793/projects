package com.ibm.sanity.excel;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import cim.ibm.sanity.bean.SanityBean;

public class SanityLogic {

	public static Set<SanityBean> logic(FileInputStream file) {

		Set<SanityBean> sanities = new LinkedHashSet<SanityBean>();
		Map<String, String> headers = new HashMap<String, String>();
		String serviceURI = null;

		try {
			Map<String, String> dme_params = null;

			XSSFWorkbook as = new XSSFWorkbook(file);

			//initializing sheets
			XSSFSheet uri_sheet = as.getSheetAt(0);
			XSSFSheet header_sheet = as.getSheetAt(1);
			XSSFSheet dme_sheet = as.getSheetAt(2);

			//getting respective data for each URI from URI Sheet
			for(int i=1; i<=uri_sheet.getLastRowNum(); i++){
				SanityBean sanity = new SanityBean();

				XSSFRow row = uri_sheet.getRow(i);
				headers = new HashMap<String, String>();
				dme_params = new HashMap<String, String>();

				//get mS details from a i'th row
				sanity.setApiNo((int) row.getCell(0).getNumericCellValue());
				sanity.setMs_Name(row.getCell(1).getStringCellValue());
				sanity.setMsNo((int) row.getCell(2).getNumericCellValue());
				sanity.setUri(row.getCell(3).getStringCellValue());
				sanity.setMethod_type(row.getCell(4).getStringCellValue());
				sanity.setJson_request(row.getCell(5).getStringCellValue());

				//loop to find all headers from Headers Sheet
				for(int k=1;k<=header_sheet.getLastRowNum(); k++) {

					//checking headers with matching msNo
					if(header_sheet.getRow(k).getCell(1).getNumericCellValue() == sanity.getMsNo()) {
						headers.put(header_sheet.getRow(k).getCell(2).getStringCellValue(), header_sheet.getRow(k).getCell(3).getStringCellValue());
					} else {
						continue;
					}
				}
				sanity.setHeader_params(headers);

				//loop to find all dme_params from DME Sheet
				for(int l=1;l<=dme_sheet.getLastRowNum(); l++) {

					if(dme_sheet.getRow(l).getCell(1).getNumericCellValue() == sanity.getMsNo() ) {
						String value = null;
						if(dme_sheet.getRow(l).getCell(2).getStringCellValue().equalsIgnoreCase("ServiceURI")) {
							serviceURI = dme_sheet.getRow(l).getCell(3).getStringCellValue();
							sanity.setServiceURI(serviceURI);
						} else {
							switch (dme_sheet.getRow(l).getCell(3).getCellType()) {
							case Cell.CELL_TYPE_STRING:
								value = dme_sheet.getRow(l).getCell(3).getStringCellValue();
								break;
							case Cell.CELL_TYPE_BOOLEAN:
								value = "" + dme_sheet.getRow(l).getCell(3).getBooleanCellValue();
								value = value.toUpperCase();
								break;
							case Cell.CELL_TYPE_NUMERIC:
								value = "" + dme_sheet.getRow(l).getCell(3).getNumericCellValue();
								break;
							}
							serviceURI = null;
							dme_params.put(dme_sheet.getRow(l).getCell(2).getStringCellValue(), value);
						}
					}
				}
				sanity.setDme_params(dme_params);
				sanities.add(sanity);
			}
			as.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		return sanities;
	}

}
