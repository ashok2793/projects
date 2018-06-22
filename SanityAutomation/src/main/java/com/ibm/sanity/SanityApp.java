package com.ibm.sanity;

import java.io.FileInputStream;
import java.util.Set;

import com.ibm.dme.SanityDmeOperation;
import com.ibm.mail.SanityMail;
import com.ibm.sanity.excel.SanityExcelWriterExample;
import com.ibm.sanity.excel.SanityLogic;

import cim.ibm.sanity.bean.SanityBean;


public class SanityApp {

	public static void main(String[] args) throws Exception {

		/*FileInputStream input = new FileInputStream  
				("C:\\SanityApplicationRequirement\\Sanity.xlsx");*/
		
		FileInputStream input = new FileInputStream  
				("C:\\Users\\IBM_ADMIN\\Downloads\\Sanity.xlsx");
		
		System.out.println("API Check Started");

		Set<SanityBean> sanities= SanityLogic.logic(input);
		
		Set<SanityBean> final_sanities = SanityDmeOperation.dmeOperations(sanities);
		
		String file[] = SanityExcelWriterExample.writeExcelSheet(final_sanities);
		
		SanityMail.sendNewRequestEmail(file);

		
		System.out.println("API Check Completed");
	}
}
