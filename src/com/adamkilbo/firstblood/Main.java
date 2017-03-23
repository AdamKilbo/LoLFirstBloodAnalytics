package com.adamkilbo.firstblood;

public class Main {

	public static void main(String[] args) {
		
		Tables SQLTables = new Tables();
		SQLTables.deleteTables();
		SQLTables.createTables();
		APICalls api = new APICalls();
		FirstBloodAnalysis analysis = new FirstBloodAnalysis();
		
		//callTests();
		boolean skipAnalysis = true;
		while (true) {
			// check if we have summoner IDs to grab matches from, or matches in our queue.
				// if low (~5), seed w/ challenger/master players
				// else continue
			if (SQLTables.getSummonerIDQueueSize() < 5) {
				System.out.println("seeding with master/challenger players");
				api.getMasterChallengerPlayers();
			} 
			if (SQLTables.getMatchIDQueueSize() < 5) {
				System.out.println("Getting matches from a player");
				String summonerID = Integer.toString(SQLTables.getSummonerIDQueue());
				System.out.println("printing summonerID: " + summonerID);
				api.getMatches(summonerID);
			}
			System.out.println(SQLTables.getMatchIDQueueSize());
			System.out.println("getting data from a match");
			String matchID = Long.toString(SQLTables.getMatchIDQueue());
			System.out.println("printing matchID: " + matchID);
			api.parseMatch(matchID);
			
			if (skipAnalysis == false) {
				analysis.doAnalysis();
			} else {
				skipAnalysis = false;
			}
		}

	}
	
	public static void callTests(){
		APITest();
		//SQLTest();
	}
	
	public static void APITest() {
		
		APICalls api = new APICalls();
		Tables SQLTables = new Tables();
		
		//FirstBloodAnalysis asdf = new FirstBloodAnalysis();
		
		//asdf.doAnalysis();
		
		//System.out.println(api.getChampName(85)); // working
		
		//api.getMasterChallengerPlayers(); // working
		
		//System.out.println(api.parseMatch("2054994244")); // working
		
		//System.out.println(api.getMatches("44989337")); // working
		
		
	}
	
	public static void SQLTest() {
		//Tables SQLTables = new Tables();
		
		//System.out.println(SQLTables.getSummonerIDQueueSize()); // working
		//System.out.println(SQLTables.getMatchIDQueueSize()); // working
		
		//System.out.println(SQLTables.getSummonerIDQueue()); // working
		
//		System.out.println(SQLTables.getMostFrequentLane());
//		System.out.println(SQLTables.getMostFrequentLaneNumber());
//		
//		System.out.println(SQLTables.getMostFrequentRole());
//		System.out.println(SQLTables.getMostFrequentRoleNumber());
//		
//		System.out.println(api.getChampName(SQLTables.getMostFrequentChamp()));
//		System.out.println(SQLTables.getMostFrequentChampNumber());
//		
//		System.out.println(SQLTables.getNumberWins());
//		System.out.println(SQLTables.getNumberLosses());
		
		/*SQLTables.insertMatchIDQueue(6);
		int game = SQLTables.getMatchIDQueue();
		System.out.println("game: " + game);
		SQLTables.insertMatchIDAnalyzed(game);
		boolean gameAnalyzed = SQLTables.getMatchIDAnalyzed(game);
		System.out.println(gameAnalyzed);
		
		SQLTables.insertMatchIDQueue(7);
		game = SQLTables.getMatchIDQueue();
		System.out.println("game: " + game);
		SQLTables.insertMatchIDAnalyzed(game);
		gameAnalyzed = SQLTables.getMatchIDAnalyzed(game);
		System.out.println(gameAnalyzed);
		
		SQLTables.insertSummonerIDQueue(9);
		int summoner = SQLTables.getSummonerIDQueue();
		System.out.println("summoner: " + summoner);
		SQLTables.insertSummonerIDAnalyzed(summoner);
		boolean summonerAnalyzed = SQLTables.getSummonerIDAnalyzed(summoner);
		System.out.println(summonerAnalyzed);
		
		SQLTables.insertSummonerIDQueue(10);
		summoner = SQLTables.getSummonerIDQueue();
		System.out.println("summoner: " + summoner);
		SQLTables.insertSummonerIDAnalyzed(summoner);
		summonerAnalyzed = SQLTables.getSummonerIDAnalyzed(summoner);
		System.out.println(summonerAnalyzed);*/
	}

}


// call for match history of summoner.Ranked solo/flex of season 7. Double check when I get chance. Currently this is bugged and doesn't return any games for 2017 season.
// https://na.api.pvp.net/api/lol/NA/v2.2/matchlist/by-summoner/{summonerId}?rankedQueues=TEAM_BUILDER_RANKED_SOLO&beginTime=1481108400000&api_key={key}
// Epoch to use: 1481108400000 (start of season 2017)

// call for detail on specific match:
// https://na.api.pvp.net/api/lol/na/v2.2/match/{matchId}?api_key= (key)

// call for master/challenger players:
// https://na.api.pvp.net/api/lol/na/v2.5/league/challenger?type=RANKED_FLEX_SR&api_key=(key)
// https://na.api.pvp.net/api/lol/na/v2.5/league/master?type=RANKED_FLEX_SR&api_key= (key)
