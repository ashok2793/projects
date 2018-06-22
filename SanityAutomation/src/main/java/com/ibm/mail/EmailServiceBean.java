package com.ibm.mail;

import java.util.ArrayList;

public class EmailServiceBean {

	private String fromId;//Email Properties File
	private String toList;
	private String ccList;
	private String mailSubject;
	private String mailBody;
	private int templateId;
	private String templatePath; //Email Properties File
	private String mailServerName; //Email Properties File
	private ArrayList<EmailDataBean> dataList;
	private ArrayList<EmailAttachmentServiceBean> attachmentFiles;
	public String getFromId() {
		return fromId;
	}
	public void setFromId(String fromId) {
		this.fromId = fromId;
	}
	public String getToList() {
		return toList;
	}
	public void setToList(String toList) {
		this.toList = toList;
	}
	public String getCcList() {
		return ccList;
	}
	public void setCcList(String ccList) {
		this.ccList = ccList;
	}
	public String getMailSubject() {
		return mailSubject;
	}
	public void setMailSubject(String mailSubject) {
		this.mailSubject = mailSubject;
	}
	public String getMailBody() {
		return mailBody;
	}
	public void setMailBody(String mailBody) {
		this.mailBody = mailBody;
	}
	public int getTemplateId() {
		return templateId;
	}
	public void setTemplateId(int templateId) {
		this.templateId = templateId;
	}
	public String getTemplatePath() {
		return templatePath;
	}
	public void setTemplatePath(String templatePath) {
		this.templatePath = templatePath;
	}
	public String getMailServerName() {
		return mailServerName;
	}
	public void setMailServerName(String mailServerName) {
		this.mailServerName = mailServerName;
	}
	public ArrayList<EmailDataBean> getDataList() {
		return dataList;
	}
	public void setDataList(ArrayList<EmailDataBean> dataList) {
		this.dataList = dataList;
	}
	public ArrayList<EmailAttachmentServiceBean> getAttachmentFiles() {
		return attachmentFiles;
	}
	public void setAttachmentFiles(
			ArrayList<EmailAttachmentServiceBean> attachmentFiles) {
		this.attachmentFiles = attachmentFiles;
	}
	
	
}
