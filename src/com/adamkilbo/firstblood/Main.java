package com.adamkilbo.firstblood;

public class Main {

	public static void main(String[] args) {
		
		callTests();
		SQLTest();
		APITest();
		APICalls api = new APICalls();
		
		
		
		
		while (true) {
			// check if we have summoner IDs to grab matches from.
				// if zero, seed w/ challenger/master players
				// else continue
			
			// check if we have match IDs to grab data from.
				// if zero, grab data match IDs from master/challenger players.
				// if > 0, take top match and analyze it for data and summoner IDs
					// do analysis on data
			
			
			
			
			System.out.println("while loop");
			System.exit(0);
		}

	}
	
	public static void callTests(){
		APITest();
		SQLTest();
	}
	
	public static void SQLTest() {
		//Tables SQLTables = new Tables();
		
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
// https://na.api.pvp.net/api/lol/na/v2.2/matchlist/by-summoner/{summonerId}?rankedQueues=RANKED_FLEX_SR,RANKED_SOLO_5x5&seasons=SEASON2017&api_key= (key)

// call for detail on specific match:
// https://na.api.pvp.net/api/lol/na/v2.2/match/{matchId}?api_key= (key)

// call for master/challenger players:
// https://na.api.pvp.net/api/lol/na/v2.5/league/challenger?type=RANKED_FLEX_SR&api_key=(key)
// https://na.api.pvp.net/api/lol/na/v2.5/league/master?type=RANKED_FLEX_SR&api_key= (key)