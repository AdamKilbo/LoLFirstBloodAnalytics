package com.adamkilbo.firstblood;

import java.net.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/*
 * 
 * This class makes calls to the riot games API and sends the response to the
 * Parser.java class to be parsed and processed.
 */

// to do: be able to retreive size of SQL queue tables

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
			System.out.println("Uh Oh, APIKey is not set. Make a text file called APIKey.txt and insert your API key in the file in the 'src/com/adamkilbo/firstblood/' directory");
			System.exit(0);
		} else {
			System.out.println("apikey: " + apiKey);
		}
	}
	
	// get specific match information
	public String parseMatch(String matchID) {
		String request = "https://na.api.pvp.net/api/lol/na/v2.2/match/{matchId}?api_key={key}";
		request = request.replace("{matchId}", matchID);
		request = request.replace("{key}", apiKey);
		System.out.println("request: " + request);
		
		String match = makeRequest(request);
		
		Parser parser = new Parser();
		parser.parseMatch(match);
		
		return match;
	}
	
	// get a list of matches from summoner's match history
	public String getMatches(String summonerId) {
		String request = "https://na.api.pvp.net/api/lol/NA/v2.2/matchlist/by-summoner/{summonerId}?rankedQueues=TEAM_BUILDER_RANKED_SOLO&beginTime=1481108400000&api_key={key}";
		request = request.replace("{key}", apiKey);
		request = request.replace("{summonerId}", summonerId);
		
		String matchList = makeRequest(request);
		
		Parser parser = new Parser();
		parser.parseMatchList(matchList);
				
		return matchList;
	}
	
	public String makeRequest(String request) {
		
		try {
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				System.out.println("waiting failed");
				e.printStackTrace();
				System.exit(1);
			}
			
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
	        
	        br.close();
	        
	        line = buffer.toString();
	        return line;
			
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		return "oops";
		
	}
	
}
