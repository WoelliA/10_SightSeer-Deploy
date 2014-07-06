package de.ur.mi.android.examples.sightseer.data;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class JsonRequest {
	
	private String requestUrl;
	private String requestIdentifier;
	public JsonRequest(String requestUri, String requestIdentifier){
		this.requestUrl = requestUri;
		this.requestIdentifier = requestIdentifier;
	}
	
	public JsonResult execute(){
		HttpClient httpClient = new DefaultHttpClient();
		HttpResponse response;
		String responseString = null;
		try {
			response = httpClient.execute(new HttpGet(requestUrl));
			StatusLine statusLine = response.getStatusLine();
			if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				response.getEntity().writeTo(out);
				out.close();
				responseString = out.toString();

			} else {
				response.getEntity().getContent().close();
				throw new IOException(statusLine.getReasonPhrase());
			}
		} catch (Exception e) {
			// this.listener.onUpdateCanceled(0);
		}
		return new JsonResult(responseString,requestIdentifier);
	}
	
}
