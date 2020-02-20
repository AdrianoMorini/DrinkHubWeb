package logic.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import logic.model.Ingredient;

public class IngredientDao {
	
	private static String pass = "password";
	private static String user = "root";
	private static String dburl = "jdbc:mysql://localhost:3306/test";
		
		    
	private IngredientDao() {
		throw new IllegalStateException("Utility class");
	}
	
	
	public static boolean setNewCocktailIngredients(List<Ingredient> ingrList, int id) {
		Statement stateSet = null;
		Connection connectionSet = null;
		try {
			
			connectionSet = DriverManager.getConnection(dburl, user, pass);
            stateSet = connectionSet.createStatement();
			int lenIngr = ingrList.size();
            for (int i = 0; i < lenIngr; i++) {
            	String mysql = "INSERT INTO test.ingredientCocktail values('" + ingrList.get(i).getName() +
            					"', '" + id + "', '" + ingrList.get(i).getQuantity() + "', '" + 
            					ingrList.get(i).getType() + "');";
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
	
	
	public static boolean removeIngredientByID(int id) {
		Statement stateSet = null;
		Connection connectionSet = null;
		
		try {
			
			connectionSet = DriverManager.getConnection(dburl, user, pass);
            stateSet = connectionSet.createStatement();
			
            String mysql = "DELETE FROM test.ingredientCocktail where cocktailID = " + id + ";";
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
	
	
	public static List<Integer> findIDByIngredientName(String ingredientName) {
		ArrayList<Integer> idList = new ArrayList<>();
		Statement stateSet = null;
		Connection connectionSet = null;
		try {
			
			connectionSet = DriverManager.getConnection(dburl, user, pass);
            stateSet = connectionSet.createStatement();
            
            String mysql = "SELECT cocktailID FROM test.ingredientCocktail where ingredientName = '" + ingredientName + "';";
            ResultSet res = stateSet.executeQuery(mysql);
            
            while(res.next()){
            	
            	int id = res.getInt("cocktailID");
            	idList.add(id);
            	
            }
                                    
            res.close();
            stateSet.close();
            connectionSet.close();
			
		} catch (SQLException e1) {
            e1.printStackTrace();
            return idList;
        } catch (Exception e2) {
            e2.printStackTrace();
            return idList;
        } finally {
            try {
                if (stateSet != null)
                    stateSet.close();
            } catch (SQLException e3) {
            	e3.printStackTrace();
            	return idList;
            }
            try {
                if (connectionSet != null)
                    connectionSet.close();
            } catch (SQLException e4) {
                e4.printStackTrace();
                return idList;
            }
		}
		return idList;
	}
	
	
	public static ArrayList<Ingredient> findIngredientByID(int id) {
		
		ArrayList<Ingredient> ingrList = new ArrayList<>();
		Statement stateSet = null;
		Connection connectionSet = null;
		
		try {
			
			connectionSet = DriverManager.getConnection(dburl, user, pass);
            stateSet = connectionSet.createStatement();
            
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
            stateSet.close();
            connectionSet.close();
			
		} catch (SQLException se) {
            se.printStackTrace();
            return ingrList;
        } catch (Exception e) {
            e.printStackTrace();
            return ingrList;
        } finally {
            try {
                if (stateSet != null)
                    stateSet.close();
            } catch (SQLException se2) {
            	se2.printStackTrace();
            	return ingrList;
            }
            try {
                if (connectionSet != null)
                    connectionSet.close();
            } catch (SQLException se) {
                se.printStackTrace();
                return ingrList;
            }
		}
	return ingrList;
	}


}
