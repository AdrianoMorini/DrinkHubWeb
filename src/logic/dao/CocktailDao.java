package logic.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import logic.model.Cocktail;
import logic.model.Ingredient;
import logic.model.Recipe;
import logic.model.Tag;

public class CocktailDao {
		

	private CocktailDao() {
		throw new IllegalStateException("Utility class init");
	}
	
	
	public static boolean setNewCocktail(Cocktail c) {

		Statement stateSet = null;
		Connection connectionSet = null;
		DatabaseManager dbMan = MariaDBDatabaseManager.getInstance();
		
		try {
			
			connectionSet = dbMan.openConnection();
            stateSet = dbMan.openStatement(connectionSet);
            
            int len = c.getImage().length();
            String im = c.getImage().substring(6, len);
			
            String mysql1 = "INSERT INTO test.cocktail(cocktailName, cocktailUser, cocktailProcedure, cocktailDate, image) "
            				+ "values('" + c.getName() + "', '" + c.getCocktailUser() + "', '" + c.getRecipeProcedure()
            				+ "', '" + c.getDate() + "', '" + im + "');";
            ResultSet res1 = stateSet.executeQuery(mysql1);
            
            String mysql2 = "SELECT last_insert_id();";
            ResultSet res2 = stateSet.executeQuery(mysql2);
            res2.next();
            int currentID = res2.getInt("last_insert_id()");
            res2.close();
            
            res1.close();
            
            c.setId(currentID);
            
            List <Ingredient> ingrList = c.getRecipeIngredients();
            IngredientDao.setNewCocktailIngredients(ingrList, currentID);

            List<Tag> tagList = c.getTags();
            TagDao.setNewCocktailTags(tagList, currentID);
            
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
	
	
	public static boolean removeCocktailByID(int id) {
		Connection connSet = null;
		Statement stSet = null;
		DatabaseManager dbMan = MariaDBDatabaseManager.getInstance();
		
		try {
			connSet = dbMan.openConnection();
            stSet = dbMan.openStatement(connSet);
			
            String mysql = "DELETE FROM test.cocktail where cocktailID = " + id + ";";
            ResultSet res = stSet.executeQuery(mysql);
            res.close();
            
            IngredientDao.removeIngredientByID(id);
            TagDao.removeTagByID(id);
			
		} catch (SQLException e1) {
			// Opening connection, or opening statement, or executing query failed
            e1.printStackTrace();
            dbMan.closeStatement(stSet);
            dbMan.closeConnection(connSet);
            return false;
        }
		
        dbMan.closeStatement(stSet);
        dbMan.closeConnection(connSet);
		
		return true;
	}
	
	
	public static List<Cocktail> findCocktailByName(String name) {
		
		Statement stateSet = null;
		Connection connectionSet = null;
		DatabaseManager dbMan = MariaDBDatabaseManager.getInstance();
		
		ArrayList<Cocktail> cList = new ArrayList<>();
		
		try {
			
			connectionSet = dbMan.openConnection();
            stateSet = dbMan.openStatement(connectionSet);
            
            String mysql = "SELECT * FROM test.cocktail where cocktailName = '" + name + "';";
            ResultSet res = stateSet.executeQuery(mysql);
            
            while(res.next()){
            	
            	int id = res.getInt("cocktailID");
            	Cocktail c = cocktailFromRS(res, id);
            	cList.add(c);
            	
            }
                                    
            res.close();
			
		} catch (SQLException e2) {
			// Opening connection, or opening statement, or executing query failed
            e2.printStackTrace();
        }
		
        dbMan.closeStatement(stateSet);
        dbMan.closeConnection(connectionSet);
		
		return cList;
		
	}
	
	
	public static Cocktail findCocktailByID(int id) {
		ArrayList<Integer> l = new ArrayList<>();
		l.add(id);
		return findCocktailByIDList(l).get(0);
	}
	
	
	public static List<Cocktail> findCocktailByTag(String tagName) {
		List<Integer> idList = TagDao.findIDByTagName(tagName);
		return findCocktailByIDList(idList);
	}
	
	
	public static List<Cocktail> findCocktailByIngredient(String ingredientName) {
        List<Integer> idList = IngredientDao.findIDByIngredientName(ingredientName);
		return findCocktailByIDList(idList);
	}
	
	
	public static List<Cocktail> findCocktailByUsername(String cUsername) {
		List<Integer> idList = findIDByUsername(cUsername);
		return findCocktailByIDList(idList);
	}
	
	
	public static List<Integer> findIDByUsername(String cUsername) {

		Statement stateSet = null;
		Connection connectionSet = null;
		
		ArrayList<Integer> idList = new ArrayList<>();
		
		DatabaseManager dbMan = MariaDBDatabaseManager.getInstance();
		
		try {
			
			connectionSet = dbMan.openConnection();
            stateSet = dbMan.openStatement(connectionSet);
            
            String mysql = "SELECT cocktailID FROM test.cocktail where cocktailUser = '" + cUsername + "';";
            ResultSet res = stateSet.executeQuery(mysql);
            
            while(res.next()){
            	
            	int id = res.getInt("cocktailID");
            	idList.add(id);
            	
            }
                                    
            res.close();
			
		} catch (SQLException e3) {
			// Opening connection, or opening statement, or executing query failed
            e3.printStackTrace();
        }
		
        dbMan.closeConnection(connectionSet);
		dbMan.closeStatement(stateSet);

		return idList;
	} 
	
	
	public static Cocktail cocktailFromRS(ResultSet rs, int id) {
		
		try {
			String cName = rs.getString("cocktailName");
			String cUser = rs.getString("cocktailUser");
			String cProcedure = rs.getString("cocktailProcedure");
			Date cDate = rs.getDate("cocktailDate");
			String cImage = rs.getString("image");
    	
			List<Ingredient> ingrList = IngredientDao.findIngredientByID(id);
			List<Tag> tList = TagDao.findTagByID(id);
			Recipe r = new Recipe(cProcedure, ingrList);
			
			return new Cocktail(cUser, cName, tList, r, (java.sql.Date) cDate, id, cImage);

		}catch (SQLException e4) {
            e4.printStackTrace();
            return null;
		}

	}
	
	
	public static List<Cocktail> findCocktailByIDList(List<Integer> idList) {
		
		Statement stateSet = null;
		Connection connectionSet = null;
		DatabaseManager dbMan = MariaDBDatabaseManager.getInstance();
		
		ArrayList<Cocktail> cList = new ArrayList<>();
		
		try {
			
           
			connectionSet = dbMan.openConnection();
			stateSet = dbMan.openStatement(connectionSet);  
            for(int i = 0; i < idList.size(); i++) {
				
            	int id = idList.get(i);
            	
            	String mysql = "SELECT * FROM test.cocktail where cocktailID = '" + id + "';";
            	ResultSet res = stateSet.executeQuery(mysql);
            	res.next();
            	Cocktail c = cocktailFromRS(res, id);
            	cList.add(c);
            	res.close();
            
            }
            
		
		} catch (SQLException e5) {
			// Opening connection, or opening statement, or executing query failed
            e5.printStackTrace();
        }
		
        dbMan.closeConnection(connectionSet);
		dbMan.closeStatement(stateSet);
		
		return cList;
	}
	
	
}
