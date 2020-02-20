package logic.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import logic.model.Tag;

public class TagDao {
	
	private static String pass = "password";
	private static String user = "root";
	private static String dburl = "jdbc:mysql://localhost:3306/test";
		
		    
	private TagDao() {
		throw new IllegalStateException("Utility class");
	}
	
	
	public static boolean setNewCocktailTags(List<Tag> tagList, int id) {
		
		Statement stateSet = null;
		Connection connectionSet = null;
		
		try {
			
			connectionSet = DriverManager.getConnection(dburl, user, pass);
            stateSet = connectionSet.createStatement();
			int lenTags = tagList.size();
            for (int j = 0; j < lenTags; j++) {
            	String mysql = "INSERT INTO test.tagCocktail values('" + tagList.get(j).getTag() +
    							"', '" + id + "');";
            	ResultSet res = stateSet.executeQuery(mysql);
            	res.close();
            }
            
            stateSet.close();
            connectionSet.close();
			
		} catch (SQLException se) {
            se.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (stateSet != null)
                    stateSet.close();
            } catch (SQLException se2) {
            	se2.printStackTrace();
            	return false;
            }
            try {
                if (connectionSet != null)
                    connectionSet.close();
            } catch (SQLException se) {
                se.printStackTrace();
                return false;
            }
		}
	return true;		
	}
	
	
	public static boolean removeTagByID(int id) {
		Statement stateSet = null;
		Connection connectionSet = null;
		
		try {
			
			connectionSet = DriverManager.getConnection(dburl, user, pass);
            stateSet = connectionSet.createStatement();
			
            String mysql = "DELETE FROM test.tagCocktail where cocktailID = " + id + ";";
            ResultSet res = stateSet.executeQuery(mysql);
            res.close();

            stateSet.close();
            connectionSet.close();
			
		} catch (SQLException e1) {
            e1.printStackTrace();
            return false;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        } finally {
            try {
                if (stateSet != null)
                    stateSet.close();
            } catch (SQLException e3) {
            	e3.printStackTrace();
            	return false;
            }
            try {
                if (connectionSet != null)
                    connectionSet.close();
            } catch (SQLException e4) {
                e4.printStackTrace();
                return false;
            }
		}
	return true;
	}
	

	public static ArrayList<Tag> findTagByID(int id) {
		
		ArrayList<Tag> tagList = new ArrayList<>();
		Statement stateSet = null;
		Connection connectionSet = null;
		try {
			
			connectionSet = DriverManager.getConnection(dburl, user, pass);
            stateSet = connectionSet.createStatement();
            String mysql = "SELECT * FROM test.tagCocktail where cocktailID = " + id + ";";
            ResultSet res = stateSet.executeQuery(mysql);
            
            while(res.next()) {
        		
        		String tagName = res.getString("tagName");
        		Tag t = new Tag(tagName);
        		tagList.add(t);
        		
        	}
        
            res.close();
            stateSet.close();
            connectionSet.close();
			
		} catch (SQLException e1) {
            e1.printStackTrace();
            return tagList;
        } catch (Exception e2) {
            e2.printStackTrace();
            return tagList;
        } finally {
            try {
                if (stateSet != null)
                    stateSet.close();
            } catch (SQLException e3) {
            	e3.printStackTrace();
            	return tagList;
            }
            try {
                if (connectionSet != null)
                    connectionSet.close();
            } catch (SQLException e4) {
                e4.printStackTrace();
                return tagList;
            }
		}
	
		return tagList;
		
	}
	
	
	public static List<Integer> findIDByTagName(String tagName) {
		ArrayList<Integer> idList = new ArrayList<>();
		Statement stateSet = null;
		Connection connectionSet = null;
		try {
			
			connectionSet = DriverManager.getConnection(dburl, user, pass);
            stateSet = connectionSet.createStatement();
            
            String mysql = "SELECT cocktailID FROM test.tagCocktail where tagName = '" + tagName + "';";
            ResultSet res = stateSet.executeQuery(mysql);
            
            while(res.next()){
            	
            	int id = res.getInt("cocktailID");
            	idList.add(id);
            	
            }
                                    
            res.close();
            stateSet.close();
            connectionSet.close();
			
		} catch (SQLException se) {
            se.printStackTrace();
            return idList;
        } catch (Exception e) {
            e.printStackTrace();
            return idList;
        } finally {
            try {
                if (stateSet != null)
                    stateSet.close();
            } catch (SQLException se2) {
            	se2.printStackTrace();
            	return idList;
            }
            try {
                if (connectionSet != null)
                    connectionSet.close();
            } catch (SQLException se) {
                se.printStackTrace();
                return idList;
            }
		}
		return idList;
	}

}
