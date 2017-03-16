package com.adamkilbo.firstblood;

import org.json.simple.*;
import java.net.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class APICalls {
	static String apiKey = null;
	
	public APICalls() {
		// set API key
		getAPIKey();
	}
	
	private void getAPIKey(){
		
		InputStream in;
		BufferedReader buf = null;
		
		try {
			in = new FileInputStream("src/com/adamkilbo/firstblood/APIKey.txt");
			buf = new BufferedReader(new InputStreamReader(in));
			
			String line = buf.readLine();
			StringBuilder sb = new StringBuilder();
			
			sb.append(line).append("\n");
		
			apiKey = sb.toString();
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				buf.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if (apiKey == null) {
			System.out.println("Uh Oh, APIKey is not set. Make a text file called APIKey.txt and insert your API key in the file");
			System.exit(0);
		} else {
			System.out.println("apikey: " + apiKey);
		}
	}
	
	public String match(String matchID) {
		String request = "https://na.api.pvp.net/api/lol/na/v2.2/match/{matchId}?api_key={key}";
		request = request.replace("{matchId}", matchID);
		request = request.replace("{key}", apiKey);
		
		System.out.println("request: " + request);
		
		makeRequest(request);
		
		return "hello";
	}
	
	public void makeRequest(String request) {
		
		try {
			URL url = new URL(request);
			
			HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
			urlc.setRequestMethod("GET");
	        urlc.setDoOutput(true);
	        
	        int responseCode = urlc.getResponseCode();
	        System.out.println("sending 'GET' request to URL : " + url);
	        System.out.println("Response Code : " + responseCode);
	        
	        //get result
	        BufferedReader br = new BufferedReader(new InputStreamReader(urlc.getInputStream()));
	        String line = null;
	        StringBuilder buffer = new StringBuilder();
	        while ((line=br.readLine())!=null) {
	            System.out.println(line);
	            buffer.append(line);
	        }
	        
	        line = buffer.toString();
	        
	        //JSONObject obj = new JSONObject(line);
	        
	        br.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
