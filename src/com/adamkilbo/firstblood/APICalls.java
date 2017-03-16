package com.adamkilbo.firstblood;

import org.json.simple.*;
import java.io.InputStream;

public class APICalls {
	String baseURL = "https://na.api.pvp.net/";
	static String apiKey = null;
	
	public APICalls() {
		// set API key
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("APIKey.txt");
		String apiKey = in.toString();
		System.out.println("apikey: " + apiKey);
		if (apiKey == null) {
			System.out.println("Uh Oh, APIKey is not set. Make a text file called APIKey.txt and insert your API key in the file");
			System.exit(0);
		}
	}
	
	public static String match(int matchID) {
		String match1 = "api/lol/NA/v2.2/match/"; // matchID here
		String match2 = "/includeTimeline=true&api_key="; // api key here
		String request = match1 + matchID + match2 + apiKey;
		
		return "hello";
	}
	
}
