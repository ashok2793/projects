package com.ibm.dme;

import java.net.URI;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import com.att.aft.dme2.api.DME2Client;
import com.att.aft.dme2.api.DME2Manager;
import com.att.aft.dme2.api.DME2RestfulHandler;

import cim.ibm.sanity.bean.SanityBean;

public class SanityDmeOperation {

	public static Set<SanityBean> dmeOperations(Set<SanityBean> sanities) throws Exception {

		Set<SanityBean> final_sanities = new LinkedHashSet<SanityBean>();

		Iterator<SanityBean> itr = sanities.iterator();

		while(itr.hasNext()){

			SanityBean sanity = (SanityBean) itr.next();

			Properties props = new Properties(); 
			for (Map.Entry<String, String> entry : sanity.getDme_params().entrySet()) {
				System.setProperty(entry.getKey() , entry.getValue());
			}

			DME2RestfulHandler.ResponseInfo response = null; 

			DME2Manager manager = new DME2Manager( "MyManager", props );

			String serviceURI =sanity.getServiceURI(); 

			int timeout = 1000000; 

			Map<String,String> headers = sanity.getHeader_params();

			DME2Client client = new DME2Client(new URI(serviceURI),1000000); 

			String testpayload =sanity.getJson_request(); 

			client.setPayload(testpayload); 
			client.setMethod(sanity.getMethod_type()); 
			client.setSubContext(sanity.getUri()); 
			client.setHeaders(headers); 

			DME2RestfulHandler handler = new DME2RestfulHandler( serviceURI ); 
			client.setReplyHandler( handler ); 
			try { 
				client.send( ); 
				response = handler.getResponseInfo( timeout ); 
				//System.out.println("Host name :");

				sanity.setJson_response(response.getBody());
				sanity.setStatus_code(response.getCode());
				if(response.getCode() == 200) {
					sanity.setStatus_report("SUCCESS");
				} else {
					sanity.setStatus_report("FAILED");
				}
				
				final_sanities.add(sanity);
			} 
			
			
			finally { 
				try { 
					manager.shutdown( ); 
				} catch ( Exception e ) { 
					e.printStackTrace(); 
				} 
			} 
		} 
		return final_sanities;
	}
}