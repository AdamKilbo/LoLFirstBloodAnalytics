package com.adamkilbo.firstblood;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FirstBloodAnalysis {
	public FirstBloodAnalysis() {}
	
	public void doAnalysis() {
		Tables SQLTables = new Tables();
		APICalls api = new APICalls();
		String buffer = null;
		
		String lane = SQLTables.getMostFrequentLane();
		String laneNum = Integer.toString(SQLTables.getMostFrequentLaneNumber());
		
		String role = SQLTables.getMostFrequentRole();
		String roleNum = Integer.toString(SQLTables.getMostFrequentRoleNumber());
		
		String champ = api.getChampName(SQLTables.getMostFrequentChamp());
		String champNum = Integer.toString(SQLTables.getMostFrequentChampNumber());
		
		String totalWins = Integer.toString(SQLTables.getNumberWins());
		String totalLosses = Integer.toString(SQLTables.getNumberLosses());
		
		String totalGames = Integer.toString(SQLTables.getNumberWins() + SQLTables.getNumberLosses());
		
		
		buffer = "Most Frequent Champ: " +  champ +
				" Number: " +  champNum +
				" Most Frequent Role: " + role + 
				" Number: " + roleNum +
				" Most Frequent Lane: " + lane +
				" Number: " + laneNum +
				" Number Wins: " + totalWins + 
				" Number Losses: " + totalLosses + 
				" Total Games: " + totalGames;
		
		System.out.println(buffer);
		
		FileWriter fwriter = null;
		BufferedWriter bwriter = null;
		try {
			fwriter = new FileWriter("stats.txt");
			bwriter = new BufferedWriter(fwriter);
			bwriter.write(buffer);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bwriter != null)
					bwriter.close();
				if (fwriter != null)
					fwriter.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
}
