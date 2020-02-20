package logic.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import logic.model.Tag;

public class TagDao {
		
		    
	private TagDao() {
		throw new IllegalStateException("Utility class");
	}
	
	
	public static boolean setNewCocktailTags(List<Tag> tagList, int id) {
		
		Statement stateSet = null;
		Connection connectionSet = null;
		DatabaseManager dbMan = MariaDBDatabaseManager.getInstance();
		
		try {
			
			connectionSet = dbMan.openConnection();
            stateSet = dbMan.openStatement(connectionSet);
			
            int lenTags = tagList.size();
            for (int j = 0; j < lenTags; j++) {
            	String mysql = "INSERT INTO test.tagCocktail values('" + tagList.get(j).getTag() +
    							"', '" + id + "');";
            	ResultSet res = stateSet.executeQuery(mysql);
            	res.close();
            }
            
		} catch (SQLException e) {
			// Opening connection, or opening statement, or executing query failed
            e.printStackTrace();
    		dbMan.closeStatement(stateSet);
    		dbMan.closeConnection(connectionSet);
            return false;
        }
    
		dbMan.closeStatement(stateSet);
		dbMan.closeConnection(connectionSet);
	
		return true;		
	
	}
	
	
	public static boolean removeTagByID(int id) {
		
		Connection cSet = null;
		Statement sSet = null;
		DatabaseManager dbMan = MariaDBDatabaseManager.getInstance();
		
		try {
			
			cSet = dbMan.openConnection();
            sSet = dbMan.openStatement(cSet);
			
            String mysql = "DELETE FROM test.tagCocktail where cocktailID = " + id + ";";
            ResultSet res = sSet.executeQuery(mysql);
            res.close();

		} catch (SQLException e7) {
			// Opening connection, or opening statement, or executing query failed
            e7.printStackTrace();
    		dbMan.closeStatement(sSet);
    		dbMan.closeConnection(cSet);
            return false;
        }
    
		dbMan.closeStatement(sSet);
		dbMan.closeConnection(cSet);
	
		return true;
	
	}
	

	public static List<Tag> findTagByID(int id) {
		
		Statement stateSet = null;
		Connection connectionSet = null;
		DatabaseManager dbMan = MariaDBDatabaseManager.getInstance();
		
		ArrayList<Tag> tagList = new ArrayList<>();
		
		try {
			
			connectionSet = dbMan.openConnection();
            stateSet = dbMan.openStatement(connectionSet);
            
            String mysql = "SELECT * FROM test.tagCocktail where cocktailID = " + id + ";";
            ResultSet res = stateSet.executeQuery(mysql);
            
            while(res.next()) {
        		
        		String tagName = res.getString("tagName");
        		Tag t = new Tag(tagName);
        		tagList.add(t);
        		
        	}
        
            res.close();
			
		} catch (SQLException e2) {
			// Opening connection, or opening statement, or executing query failed
            e2.printStackTrace();
        }
    
		dbMan.closeStatement(stateSet);
		dbMan.closeConnection(connectionSet);
	
		return tagList;
		
	}
	
	
	public static List<Integer> findIDByTagName(String tagName) {

		Connection connectionSet = null;
		Statement stateSet = null;
		DatabaseManager dbMan = MariaDBDatabaseManager.getInstance();
		
		ArrayList<Integer> idList = new ArrayList<>();
		
		try {
			
			connectionSet = dbMan.openConnection();
            stateSet = dbMan.openStatement(connectionSet);
            
            String mysql = "SELECT cocktailID FROM test.tagCocktail where tagName = '" + tagName + "';";
            ResultSet res = stateSet.executeQuery(mysql);
            
            while(res.next()){
            	
            	int id = res.getInt("cocktailID");
            	idList.add(id);
            	
            }
                                    
            res.close();
            stateSet.close();
            connectionSet.close();
			
		} catch (SQLException e3) {
			// Opening connection, or opening statement, or executing query failed
            e3.printStackTrace();
        }
    
		dbMan.closeStatement(stateSet);
		dbMan.closeConnection(connectionSet);
		
		return idList;
	}

}
