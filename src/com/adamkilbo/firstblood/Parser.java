package com.adamkilbo.firstblood;

import java.util.Iterator;

import org.json.simple.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/*
 * 
 * This class takes info passed from APICalls, parses it, and takes action on it. 
 * A few things this class does: 
 * 1) gets info from matches for our statistics
 * 2) fills in our matchID and summonerID queues
 * 
 */

public class Parser {
	
	public Parser() {}
	
	void parseMatch(String in) {
		JSONParser parser = new JSONParser();
		JSONObject json = null;
		try {
			json = (JSONObject) parser.parse(in);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		System.out.println("1: " + json);
		
		String queueType = (String) json.get("queueType");
		if (queueType.equals("RANKED_SOLO_5x5") == true ) {
			long matchId = (long) json.get("matchId");
			System.out.println("mID: " + matchId);
			
			JSONArray participants = (JSONArray) json.get("participants");
			Iterator<?> i = participants.iterator();
			
			while (i.hasNext()) {
				JSONObject participant = (JSONObject) i.next();
				JSONObject stats = (JSONObject) participant.get("stats");
				long championId = (long) participant.get("championId");
				System.out.println("ChampID: " + championId);
				String role = (String) participant.get("role");
				System.out.println("role: " + role);
				String lane = (String) participant.get("lane");
				System.out.println("lane: " + lane);
				boolean winner = (boolean) stats.get("winner");
				System.out.println("winner: " + winner);
				boolean firstBloodKill = (boolean) stats.get("firstBloodKill");
				System.out.println("firstBloodKill: " + firstBloodKill);
				
				if (firstBloodKill == true) {
					System.out.println("Winner!\n.\n.\n.\n.");
					// insert stats into table
				} else {
					// insert into summoner search queue?
				}
			}
		}
	}

}
