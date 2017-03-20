package com.adamkilbo.firstblood;

import java.util.Iterator;

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
	
	void parseMatch(String match) {
		Tables SQLTables = new Tables();
		
		JSONParser parser = new JSONParser();
		JSONObject json = null;
		try {
			json = (JSONObject) parser.parse(match);
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
				JSONObject timeline = (JSONObject) participant.get("timeline");
				long championId = (long) participant.get("championId");
				System.out.println("ChampID: " + championId);
				String role = (String) timeline.get("role");
				//System.out.println("role: " + role);
				String lane = (String) timeline.get("lane");
				//System.out.println("lane: " + lane);
				boolean winner = (boolean) stats.get("winner");
				System.out.println("winner: " + winner);
				boolean firstBloodKill = (boolean) stats.get("firstBloodKill");
				//System.out.println("firstBloodKill: " + firstBloodKill);
				
				if (firstBloodKill == true) {
					//System.out.println("First Blood!\n.\n.\n.\n.");
					// insert stats into table
					// this order:
					// championId, win/loss, role, lane, season highest 
					
					SQLTables.insertMatchStatistics(matchId, role, lane, championId, winner);
				} 
			}
			
			// matches -> participantIdentities. get IDs and insert into summonerIdQueue
			JSONArray participantIdentities = (JSONArray) json.get("participantIdentities");
			Iterator<?> j = participantIdentities.iterator();
			
			while (j.hasNext()) {
				JSONObject participantIdentity = (JSONObject) j.next();
				JSONObject player = (JSONObject) participantIdentity.get("player");
				long summonerId = (long) player.get("summonerId");
				System.out.println("SummonerId: " + summonerId);
				
				SQLTables.insertSummonerIDQueue(summonerId);
			}
		}
	}
	
	// sends match list into matchIDQueue
	public void parseMatchList(String matchList) {
		Tables SQLTables = new Tables();
		
		JSONParser parser = new JSONParser();
		JSONObject json = null;
		try {
			json = (JSONObject) parser.parse(matchList);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		JSONArray matches = (JSONArray) json.get("matches");
		Iterator<?> i = matches.iterator();
		
		while (i.hasNext()) {
			JSONObject match = (JSONObject) i.next();
			long matchId = (long) match.get("matchId");
			System.out.println("matchId: " + matchId);
			
			// insert matchID into matchIdQueue
			SQLTables.insertMatchIDQueue(matchId);
		}
		
	}

}
