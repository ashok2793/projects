package cim.ibm.sanity.bean;

import java.util.Map;

public class SanityBean {
	
	private String ms_Name, method_type, json_request, status_report, serviceURI, json_response, uri ;
	private Integer apiNo, msNo, status_code;
	private Map<String,String> dme_params;
	private Map<String,String> header_params;
	public String getMs_Name() {
		return ms_Name;
	}
	public void setMs_Name(String ms_Name) {
		this.ms_Name = ms_Name;
	}
	public String getMethod_type() {
		return method_type;
	}
	public void setMethod_type(String method_type) {
		this.method_type = method_type;
	}
	public String getJson_request() {
		return json_request;
	}
	public void setJson_request(String json_request) {
		this.json_request = json_request;
	}
	public String getStatus_report() {
		return status_report;
	}
	public void setStatus_report(String status_report) {
		this.status_report = status_report;
	}
	public String getServiceURI() {
		return serviceURI;
	}
	public void setServiceURI(String serviceURI) {
		this.serviceURI = serviceURI;
	}
	public String getJson_response() {
		return json_response;
	}
	public void setJson_response(String json_response) {
		this.json_response = json_response;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public Integer getApiNo() {
		return apiNo;
	}
	public void setApiNo(Integer apiNo) {
		this.apiNo = apiNo;
	}
	public Integer getMsNo() {
		return msNo;
	}
	public void setMsNo(Integer msNo) {
		this.msNo = msNo;
	}
	public Integer getStatus_code() {
		return status_code;
	}
	public void setStatus_code(Integer status_code) {
		this.status_code = status_code;
	}
	public Map<String, String> getDme_params() {
		return dme_params;
	}
	public void setDme_params(Map<String, String> dme_params) {
		this.dme_params = dme_params;
	}
	public Map<String, String> getHeader_params() {
		return header_params;
	}
	public void setHeader_params(Map<String, String> header_params) {
		this.header_params = header_params;
	}
	@Override
	public String toString() {
		return "SanityBean [ms_Name=" + ms_Name + ", method_type=" + method_type + ", json_request=" + json_request
				+ ", status_report=" + status_report + ", serviceURI=" + serviceURI + ", json_response=" + json_response
				+ ", uri=" + uri + ", apiNo=" + apiNo + ", msNo=" + msNo + ", status_code=" + status_code
				+ ", dme_params=" + dme_params + ", header_params=" + header_params + "]";
	}
	
	
}
