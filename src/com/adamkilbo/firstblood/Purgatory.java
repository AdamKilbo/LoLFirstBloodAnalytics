package com.adamkilbo.firstblood;

/*
 * This file exists to hold code that isn't currently being used but may be used in the future. 
 */

public class Purgatory {}

/*			dbm = SQLConn.getMetaData();

tables = dbm.getTables(null, null, "FBStats", null);
if (tables.next()) {
	;
} else {
	stmt = SQLConn.createStatement();
	// on first iter only do following: matchID, killerRole, killerLane, killerChamp, FBTeamWin
      String sql = "CREATE TABLE FBStats "
                   + "(matchID INT NOT NULL, "
                   + " firstBloodTime INT NOT NULL, "
                   + " killerRole TEXT NOT NULL, "
                   + " killerLane TEXT NOT NULL, "
                   + " killerTier TEXT NOT NULL, "
                   + " killerChamp INT NOT NULL, "
                   + " assistRoles TEXT NOT NULL, "
                   + " assistLanes TEXT NOT NULL, "
                   + " FBTeamWin TEXT NOT NULL)"; 
      stmt.executeUpdate(sql);

// check if table accumulatedFBStats exists
tables = dbm.getTables(null, null, "accumulatedFBStats", null);
}
if (tables.next()) {
	;
} else {
	stmt = SQLConn.createStatement();
    String sql = "CREATE TABLE accumulatedFBStats "
                   + "(FBTimeStampTotal INT NOT NULL, "
                   + " matchesAnalyzed INT NOT NULL, "
                   + " FBTimeStampAverage INT NOT NULL, "
                   + " bronzeFBTimeStampAverage INT NOT NULL, "
                   + " bronzeMatchesAnalyzed INT NOT NULL, "
                   + " silverFBTimeStampAverage INT NOT NULL, "
                   + " silverMatchesAnalyzed INT NOT NULL, "
                   + " goldFBTimeStampAverage INT NOT NULL, "
                   + " goldMatchesAnalyzed INT NOT NULL, "
                   + " platinumFBTimeStampAverage INT NOT NULL, "
                   + " platinumeMatchesAnalyzed INT NOT NULL, "
                   + " diamondFBTimeStampAverage INT NOT NULL, "
                   + " diamondMatchesAnalyzed INT NOT NULL, "
                   + " masterFBTimeStampAverage INT NOT NULL, "
                   + " masterMatchesAnalyzed INT NOT NULL, "
                   + " challengerFBTimeStampAverage INT NOT NULL, "
                   + " challengerMatchesAnalyzed INT NOT NULL,"
                   + " totalTeamWin INT NOT NULL)"; 
      stmt.executeUpdate(sql);
}

// check if table summonerIDQueue exists
tables = dbm.getTables(null, null, "summonerIDQueue", null);
if (tables.next()) {
	;
} else {
	stmt = SQLConn.createStatement();
    String sql = "CREATE TABLE summonerIDQueue "
                   + "(summonerID INT NOT NULL)"; 
      stmt.executeUpdate(sql);
}

// check if table summonerIDAnalyzed exists
tables = dbm.getTables(null, null, "summonerIDAnalyzed", null);
if (tables.next()) {
	;
} else {
	stmt = SQLConn.createStatement();
    String sql = "CREATE TABLE summonerIDAnalyzed "
                   + "(summonerID INT NOT NULL, "
                   + " updateTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL)"; 
      stmt.executeUpdate(sql);
}
*/