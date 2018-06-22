package com.ibm.mail;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import org.springframework.web.client.RestTemplate;

public class SanityMail {
	
	public static void sendNewRequestEmail(String[] fileDetails) {
		String WEBSERVICE_URL = "http://KICDT13.kcdc.att.com:8025/IBTOOL_Services" + "/sendEmail";

		//String ccList = null;
		//String toList = null;

		/*
		 * List<String> getAllLeadEmailIds = getAllLeadEmailIds(); int i = 0; for
		 * (String leadID : getAllLeadEmailIds) { if (i < 1) { toList = leadID +
		 * "@att.com"; } else if (i >= 1) { toList = toList + "," + leadID + "@att.com";
		 * } i++; }
		 * 
		 * ArrayList<EmailDataBean> dataList = new ArrayList<EmailDataBean>();
		 * EmailDataBean data1 = new EmailDataBean(); data1.setKey("RequestUniqueID");
		 * data1.setValue(requestUniqueId); dataList.add(data1);
		 */

		EmailServiceBean emailServiceBean = new EmailServiceBean();
		// emailServiceBean.setTemplateId(1);
		emailServiceBean.setCcList("soumkris@in.ibm.com");
		emailServiceBean.setToList("cwalikar@in.ibm.com");
		emailServiceBean.setFromId("ashmoh27@in.ibm.com");
		emailServiceBean.setMailBody("Report Mail from Sanity Application Test Team<br><br>");
		emailServiceBean.setMailSubject("API Sanity Application Report Mail");

		EmailAttachmentServiceBean attachBean = new EmailAttachmentServiceBean();
		ArrayList<EmailAttachmentServiceBean> attachBeanList = new ArrayList<EmailAttachmentServiceBean>();
		File file = new File(fileDetails[1]);
		byte[] bytesArray = new byte[(int) file.length()];
		FileInputStream fis;
		try {
			fis = new FileInputStream(file);
			fis.read(bytesArray); // read file into bytes[]
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		attachBean.setData(bytesArray);
		attachBean.setMimeType("application/vnd.ms-excel");
		attachBean.setFilename(fileDetails[0]);
		attachBeanList.add(attachBean);
		emailServiceBean.setAttachmentFiles(attachBeanList);

		RestTemplate restTemplate = new RestTemplate();

		boolean isMailSent = restTemplate.postForObject(WEBSERVICE_URL, emailServiceBean, Boolean.class);

		System.out.println(isMailSent);

	}

}
