package com.ibm.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.entity.Sanity;
import com.ibm.repository.SanityRepository;

@Service
public class SanityService {


	@Autowired
	private SanityRepository sanityRepository;

	public SanityService(SanityRepository sanityRepository) {
		this.sanityRepository = sanityRepository;
	}

	public Sanity addSanity(Sanity sanity) {
		Sanity s = sanityRepository.save(sanity);
		return s;
	}

	public Optional<Sanity> getSanityById(Long id) {
		Optional<Sanity> s = sanityRepository.findById(id);
		return s;
	}

	public Sanity update(Sanity Sanity) {
		Sanity s= sanityRepository.saveAndFlush(Sanity);
		return s;
	}
	
	public void delete(Long id) {
		sanityRepository.deleteById(id);
	}
	
	public List<Sanity> getAllSanities() {
		List<Sanity> list = sanityRepository.findAll();
		return list;
	}

	public void sendData() throws IOException {
		FileInputStream input = new FileInputStream  
				("C:\\Workspace's\\SanityAppStatus.xls");
		POIFSFileSystem fs = new POIFSFileSystem (input);
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		HSSFSheet sheet = wb.getSheetAt(0);
		List<Sanity> sanities = new ArrayList<>();
		for(int i=1; i<=sheet.getLastRowNum(); i++){
			HSSFRow row = sheet.getRow(i);
			String appType = row.getCell(0).getStringCellValue();
			String category = row.getCell(1).getStringCellValue();
			String status = row.getCell(2).getStringCellValue();
			String date = row.getCell(3).getStringCellValue();
			String time = row.getCell(4).getStringCellValue();
			
			Sanity s = new Sanity(row.getCell(0).getStringCellValue(),
					row.getCell(1).getStringCellValue(),
					row.getCell(2).getStringCellValue(),
					row.getCell(3).getStringCellValue(),
					row.getCell(4).getStringCellValue());
			
			sanities.add(s);
			}
		sanityRepository.saveAll(sanities);
		
	}
	
}
