package com.adamkilbo.firstblood;

import java.sql.*;

/*
 * 
 * This class interfaces with the SQL tables that exist. It can:
 * 1) insert information into any of our various SQL tables
 * 2) retrieve information from our SQL tables
 * 3) Create and delete tables as needed when the program starts.
 */

public class Tables {
	
	Connection SQLConn = null;
	
	public Tables() {
		
		connectToSQL();
		
		// delete tables, new data etc.
		// deleteTables();
		
		// create tables
		//createTables();
		
	}
	
	public void createTables(){
		// check if the various sql tables exist. if not, create them.
		DatabaseMetaData dbm = null;
		ResultSet tables;
		
		try {
			
			dbm = SQLConn.getMetaData();
			
			// check if table matchIDQueue exists
			tables = dbm.getTables(null, null, "matchIDQueue", null);
			if (tables.next()) {
				System.out.println("table matchIDQueue already exists");
			} else {
				
			    String sql = "CREATE TABLE matchIDQueue "
			                   + "(matchID INT NOT NULL)"; 
			    SQLConn.createStatement().executeUpdate(sql);
			}
			tables.close();
	
			// check if table matchIDQAnalyzed exists
			tables = dbm.getTables(null, null, "matchIDAnalyzed", null);
			if (tables.next()) {
				System.out.println("table matchIDAnalyzed already exists");
			} else {
				
			    String sql = "CREATE TABLE matchIDAnalyzed "
			                   + "(matchID INT NOT NULL)"; 
			    SQLConn.createStatement().executeUpdate(sql);
			}
			tables.close();
			
			// check if table summonerIDQueue exists
			tables = dbm.getTables(null, null, "summonerIDQueue", null);
			if (tables.next()) {
				System.out.println("table summonerIDQueue already exists");
			} else {
				
			    String sql = "CREATE TABLE summonerIDQueue "
			                   + "(summonerID INT NOT NULL)"; 
			    SQLConn.createStatement().executeUpdate(sql);
			}
			tables.close();

			// check if table summonerIDAnalyzed exists
			tables = dbm.getTables(null, null, "summonerIDAnalyzed", null);
			if (tables.next()) {
				System.out.println("table summonerIDAnalyzed already exists");
			} else {
				
			    String sql = "CREATE TABLE summonerIDAnalyzed "
			                   + "(summonerID INT NOT NULL, "
			                   + " updateTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL)"; 
			    SQLConn.createStatement().executeUpdate(sql);
			}
			tables.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("Verified/created SQL tables");
	}
	
	public void connectToSQL() {
		// create connection to tables
		try {
			Class.forName("org.sqlite.JDBC");
			SQLConn = DriverManager.getConnection("jdbc:sqlite:test.db");
		} catch (Exception e) {
			System.out.println("Error connecting to database");
			System.err.println(e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);		
		}
		System.out.println("Opened database successfully");
	}
	
	//////////////////////
	// delete tables
	//////////////////////
	
	public void deleteTables() {
		try {
			
			// if needed, drop tables
			String dropTables = "DROP TABLE matchIDAnalyzed;";
			SQLConn.createStatement().executeUpdate(dropTables);
			
			dropTables = "DROP TABLE matchIDQueue;";
			SQLConn.createStatement().executeUpdate(dropTables);
			
			dropTables = "DROP TABLE summonerIDQueue;";
			SQLConn.createStatement().executeUpdate(dropTables);
			
			dropTables = "DROP TABLE summonerIDAnalyzed;";
			SQLConn.createStatement().executeUpdate(dropTables);
			
			System.out.println("table dropped");
		} catch (SQLException e1) {
			
			e1.printStackTrace();
		}
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////
	// setters
	//////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void insertMatchIDQueue(int id) {
		ResultSet rs = null;
		ResultSet rs1 = null;
		try {
			// Avoid duplicates
			rs = SQLConn.createStatement().executeQuery("select * from matchIDQueue where matchID = " + id + ";");
		    if (!rs.next()) {
		    	// Ensure not in analyzed
		    	rs1 = SQLConn.createStatement().executeQuery("select * from matchIDAnalyzed where matchID = " + id + ";");
				
			    if (!rs1.next()) {
			    	SQLConn.createStatement().executeUpdate("INSERT INTO matchIDQueue " + "VALUES ('" + id + "');");
			    }
		    }
		} catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		} finally {
	    	try {
				rs.close();
				rs1.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    }
	}
	
	public void insertSummonerIDQueue(int id) {
		ResultSet rs = null;
		ResultSet rs1 = null;
		
		try {
			// Avoid duplicates
			rs = SQLConn.createStatement().executeQuery("select * from summonerIDQueue where summonerID = '" + id + "';");
		    if (!rs.next()) {	
		    	// Ensure not in analyzed
		    	rs1 = SQLConn.createStatement().executeQuery("select * from summonerIDAnalyzed where summonerID = '" + id + "';"); 
			    if (!rs1.next()) {
			    	SQLConn.createStatement().executeUpdate("INSERT INTO summonerIDQueue " + "VALUES ('" + id + "');");
			    }
		    }   
		} catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		} finally {
	    	try {
				rs.close();
				rs1.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    }
	}
	
	public void insertMatchIDAnalyzed(int id) {
		try {
			
			SQLConn.createStatement().executeUpdate("INSERT INTO matchIDAnalyzed (matchID) " + "VALUES ('" + id + "');");
		
		} catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		}
	}
	
	public void insertSummonerIDAnalyzed(int id) {
		
		try {
			SQLConn.createStatement().executeUpdate("INSERT INTO summonerIDAnalyzed (summonerID) " + "VALUES ('" + id + "');");
		} catch ( Exception e ) {
			
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		}
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////
	// getters
	//////////////////////////////////////////////////////////////////////////////////////////////////
	
	// queue
	
	public int getSummonerIDQueue() {
		
		// get next summoner ID from queue
		ResultSet rs = null;
	    
	    try {
	      rs = SQLConn.createStatement().executeQuery( "SELECT * FROM summonerIDQueue;" );
	      int summonerID = rs.getInt("summonerID");
	      SQLConn.createStatement().executeUpdate("DELETE FROM summonerIDQueue where summonerID = '" + summonerID + "';");
	     
	      return summonerID;
	      
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    } finally {
	    	try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    }
	    
	    System.out.println("oops, couldn't complete get 1");
	    System.exit(0);
	    return 0;
	}
	
	public int getMatchIDQueue() {
		
		// get next matchID from queue
		ResultSet rs = null;
	    
	    try {
	      rs = SQLConn.createStatement().executeQuery("SELECT * FROM matchIDQueue;");  
	      int matchID = rs.getInt("matchID");
	      SQLConn.createStatement().executeUpdate("DELETE FROM matchIDQueue where matchID = '" + matchID + "';");
	      
	      return matchID;
	      
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    } finally {
	    	try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    }
	    
	    System.out.println("oops, couldn't complete get 2");
	    System.exit(0);
		return 0;
	}
	
	// analyzed returns bools, want to know if eligible for analysis
	
	public boolean getSummonerIDAnalyzed(int ID) {
 
		// see if ID has been analyzed in past
 		// eligible for analysis if <= 3 days old
		
		// returns true or false. 
		//True = ID analyzed in past 3 days, ignore
		ResultSet rs1 = null;
		ResultSet rs2 = null;
	    
	    try {
	    	rs1 = SQLConn.createStatement().executeQuery("SELECT * FROM summonerIDAnalyzed WHERE summonerID = '" + ID + "';");
	    	if (!rs1.next()) {
	    		return false;
	    	}
	    	else{
		    	rs2 = SQLConn.createStatement().executeQuery( "SELECT * FROM summonerIDAnalyzed WHERE updateTime < (SELECT DATETIME('now', '-3 day')) AND summonerID = " + ID + ";" );
			    if (!rs2.next()) {	
			    	return true;
			    } else {
			    	return false;
			    }
	    	}
	    } catch ( Exception e ) {
	    	System.out.println("Error in getSummonerIDAnalyzed");
	    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    	System.exit(0);
	    } finally {
	    	try {
				rs1.close();
				rs2.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    }
	    
	    System.out.println("oops, couldn't complete get 3");
	    System.exit(0);
	    return true;
	}
	
	public boolean getMatchIDAnalyzed(int ID) {
		// see if match ID has been analyzed in past
		// true = games has been analyzed in past, ignore
		ResultSet rs = null;
	    
	    try {
	      rs = SQLConn.createStatement().executeQuery( "SELECT * FROM matchIDAnalyzed WHERE matchID = " + ID + ";" );  
	      if (rs.getInt("matchID") == ID)
	    	  return true;
	      else
	    	  return false; 
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    } finally {
	    	try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    }
	    
	    System.out.println("oops, couldn't complete get 4");
	    System.exit(0);
		return false;
	}
}
