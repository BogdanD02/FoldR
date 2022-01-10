package functionals.handlers;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;

public class WebHandler {
	private static String url = "https://webservicesp.anaf.ro/PlatitorTvaRest/api/v6/ws/tva";
	
	public WebHandler() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * This function puts a string within quotes.
	 * 
	 * @param	obj		The string to be quoted
	 * @return	A new string with quotes.
	 */
	private String appendQuotes(String obj) {
		StringBuffer buffer = new StringBuffer();
		
		buffer.append('"');
		buffer.append(obj);
		buffer.append('"');
		
		return buffer.toString();
	}
	
	public String executePost(String CUI) {
		HttpURLConnection connection = null;
		StringBuffer querry = new StringBuffer();
		
		querry.append("[\n");
		querry.append("\t{\n");
		querry.append("\t\t");
		querry.append(appendQuotes("cui"));
		querry.append(": ");
		querry.append(CUI);
		querry.append(",\n\t\t");
		querry.append(appendQuotes("data"));
		querry.append(": ");
		querry.append(appendQuotes("2022-01-09"));
		querry.append("\n");
		querry.append("\t}\n]");
		
		try {
			URL address = new URL(url);
			
			connection = (HttpURLConnection)address.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setUseCaches(false);
		    connection.setDoOutput(true);
			//connection.getOutputStream().write(querry.toString().getBytes("UTF-8"));
			
			// Send request
			DataOutputStream wr = new DataOutputStream(connection.getOutputStream());		
			wr.writeBytes(querry.toString());
			wr.close();
		
			// Get Response
			InputStream is = connection.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			StringBuffer response = new StringBuffer();
			String line;
			
			while((line = rd.readLine()) != null) {
				response.append(line);
				response.append('\r');
			}
			rd.close();
			return response.toString();
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if(connection != null)
				connection.disconnect();
		}
	}
}
