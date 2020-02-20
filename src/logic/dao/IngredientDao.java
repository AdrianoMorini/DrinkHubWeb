package logic.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import logic.model.Ingredient;

public class IngredientDao {
	
	    
	private IngredientDao() {
		throw new IllegalStateException("Utility class");
	}
	
	
	public static boolean setNewCocktailIngredients(List<Ingredient> ingrList, int id) {
		
		Statement stateSet = null;
		Connection connectionSet = null;
		DatabaseManager dbMan = MariaDBDatabaseManager.getInstance();
		
		try {
			
			connectionSet = dbMan.openConnection();
            stateSet = dbMan.openStatement(connectionSet);
			
            int lenIngr = ingrList.size();
            for (int i = 0; i < lenIngr; i++) {
            	String mysql = "INSERT INTO test.ingredientCocktail values('" + ingrList.get(i).getName() +
            					"', '" + id + "', '" + ingrList.get(i).getQuantity() + "', '" + 
            					ingrList.get(i).getType() + "');";
            	ResultSet res = stateSet.executeQuery(mysql);
            	res.close();
            }
            
		} catch (SQLException e9) {
			// Opening connection, or opening statement, or executing query failed
            e9.printStackTrace();
    		dbMan.closeStatement(stateSet);
    		dbMan.closeConnection(connectionSet);
            return false;
        }
    
		dbMan.closeStatement(stateSet);
		dbMan.closeConnection(connectionSet);
	
		return true;
	
	}
	
	
	public static boolean removeIngredientByID(int id) {
		
		Connection connSet = null;
		Statement stSet = null;
		DatabaseManager dbMan = MariaDBDatabaseManager.getInstance();
		
		try {
			
			connSet = dbMan.openConnection();
            stSet = dbMan.openStatement(connSet);
			
            String mysql = "DELETE FROM test.ingredientCocktail where cocktailID = " + id + ";";
            ResultSet res = stSet.executeQuery(mysql);
            res.close();

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
	
	
	public static List<Integer> findIDByIngredientName(String ingredientName) {
		
		Statement stateSet = null;
		Connection connectionSet = null;
		DatabaseManager dbMan = MariaDBDatabaseManager.getInstance();
		
		ArrayList<Integer> idList = new ArrayList<>();
		
		try {
			
			connectionSet = dbMan.openConnection();
            stateSet = dbMan.openStatement(connectionSet);
            
            String mysql = "SELECT cocktailID FROM test.ingredientCocktail where ingredientName = '" + ingredientName + "';";
            ResultSet res = stateSet.executeQuery(mysql);
            
            while(res.next()){
            	
            	int id = res.getInt("cocktailID");
            	idList.add(id);
            	
            }
                                    
            res.close();
			
		} catch (SQLException e2) {
			// Opening connection, or opening statement, or executing query failed
            e2.printStackTrace();
        }
    
		dbMan.closeStatement(stateSet);
		dbMan.closeConnection(connectionSet);
		
		return idList;
	
	}
	
	
	public static List<Ingredient> findIngredientByID(int id) {
		
		Statement stateSet = null;
		Connection connectionSet = null;
		DatabaseManager dbMan = MariaDBDatabaseManager.getInstance();
		
		ArrayList<Ingredient> ingrList = new ArrayList<>();
		
		try {
			
			connectionSet = dbMan.openConnection();
            stateSet = dbMan.openStatement(connectionSet);
            
            String mysql = "SELECT * FROM test.ingredientCocktail where cocktailID = " + id + ";";
            ResultSet res = stateSet.executeQuery(mysql);
            
            while(res.next()) {
        		
        		String ingrName = res.getString("ingredientName");
        		float ingrQuantity = res.getFloat("quantity");
        		int ingrType = res.getInt("typeIng");
        		Ingredient ing = new Ingredient(ingrName, ingrQuantity, ingrType);
        		ingrList.add(ing);
        	
        	}

            res.close();
			
		} catch (SQLException e3) {
			// Opening connection, or opening statement, or executing query failed
            e3.printStackTrace();
        }
    
		dbMan.closeStatement(stateSet);
		dbMan.closeConnection(connectionSet);
	
		return ingrList;
	
	}


}
