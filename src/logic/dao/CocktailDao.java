package logic.dao;

import java.sql.Connection;
import java.sql.DriverManager;
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
		
	private static String pass = "password";
	private static String user = "root";
	private static String dburl = "jdbc:mysql://localhost:3306/test";
		
		    
	private CocktailDao() {
		throw new IllegalStateException("Utility class init");
	}
	
	
	public static boolean setNewCocktail(Cocktail c) {
		// aggiungere immagine
		Statement stateSet = null;
		Connection connectionSet = null;
		try {
			
			connectionSet = DriverManager.getConnection(dburl, user, pass);
            stateSet = connectionSet.createStatement();

            String mysql1 = "INSERT INTO test.cocktail(cocktailName, cocktailUser, cocktailProcedure, cocktailDate, image) "
            				+ "values('" + c.getName() + "', '" + c.getCocktailUser() + "', '" + c.getRecipeProcedure()
            				+ "', '" + c.getDate() + "', '" + c.getImage() + "');";
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
	
	
	public static boolean removeCocktailByID(int id) {
		Statement stateSet = null;
		Connection connectionSet = null;
		
		try {
			
			connectionSet = DriverManager.getConnection(dburl, user, pass);
            stateSet = connectionSet.createStatement();
			
            String mysql = "DELETE FROM test.cocktail where cocktailID = " + id + ";";
            ResultSet res = stateSet.executeQuery(mysql);
            res.close();
            
            IngredientDao.removeIngredientByID(id);
            TagDao.removeTagByID(id);

            stateSet.close();
            connectionSet.close();
			
		} catch (SQLException ex1) {
            ex1.printStackTrace();
            return false;
        } catch (Exception ex2) {
            ex2.printStackTrace();
            return false;
        } finally {
            try {
                if (stateSet != null)
                    stateSet.close();
            } catch (SQLException ex3) {
            	ex3.printStackTrace();
            	return false;
            }
            try {
                if (connectionSet != null)
                    connectionSet.close();
            } catch (SQLException ex4) {
                ex4.printStackTrace();
                return false;
            }
		}
	return true;
	}
	
	
	public static List<Cocktail> findCocktailByName(String name) {
		
		ArrayList<Cocktail> cList = new ArrayList<>();
		Statement stateSet = null;
		Connection connectionSet = null;
		try {
			
			connectionSet = DriverManager.getConnection(dburl, user, pass);
            stateSet = connectionSet.createStatement();
            
            String mysql = "SELECT * FROM test.cocktail where cocktailName = '" + name + "';";
            ResultSet res = stateSet.executeQuery(mysql);
            
            while(res.next()){
            	
            	int id = res.getInt("cocktailID");
            	Cocktail c = cocktailFromRS(res, id);
            	cList.add(c);
            	
            }
                                    
            res.close();
            stateSet.close();
            connectionSet.close();
			
		} catch (SQLException se) {
            se.printStackTrace();
            return cList;
        } catch (Exception e) {
            e.printStackTrace();
            return cList;
        } finally {
            try {
                if (stateSet != null)
                    stateSet.close();
            } catch (SQLException se2) {
            	se2.printStackTrace();
            	return cList;
            }
            try {
                if (connectionSet != null)
                    connectionSet.close();
            } catch (SQLException se) {
                se.printStackTrace();
                return cList;
            }
		}
		
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
		ArrayList<Integer> idList = new ArrayList<>();
		Statement stateSet = null;
		Connection connectionSet = null;
		try {
			
			connectionSet = DriverManager.getConnection(dburl, user, pass);
            stateSet = connectionSet.createStatement();
            
            String mysql = "SELECT cocktailID FROM test.cocktail where cocktailUser = '" + cUsername + "';";
            ResultSet res = stateSet.executeQuery(mysql);
            
            while(res.next()){
            	
            	int id = res.getInt("cocktailID");
            	idList.add(id);
            	
            }
                                    
            res.close();
            stateSet.close();
            connectionSet.close();
			
		} catch (SQLException e5) {
            e5.printStackTrace();
            return idList;
        } catch (Exception e6) {
            e6.printStackTrace();
            return idList;
        } finally {
            try {
                if (stateSet != null)
                    stateSet.close();
            } catch (SQLException e7) {
            	e7.printStackTrace();
            	return idList;
            }
            try {
                if (connectionSet != null)
                    connectionSet.close();
            } catch (SQLException e8) {
                e8.printStackTrace();
                return idList;
            }
		}
		return idList;
	} 
	
	
	public static Cocktail cocktailFromRS(ResultSet rs, int id) {
		try {
			String cName = rs.getString("cocktailName");
			String cUser = rs.getString("cocktailUser");
			String cProcedure = rs.getString("cocktailProcedure");
			Date cDate = rs.getDate("cocktailDate");
			String cImage = rs.getString("image");
    	
			ArrayList<Ingredient> ingrList = IngredientDao.findIngredientByID(id);
			ArrayList<Tag> tList = TagDao.findTagByID(id);
			Recipe r = new Recipe(cProcedure, ingrList);
    	
			return new Cocktail(cUser, cName, tList, r, (java.sql.Date) cDate, id, cImage);

		}catch (SQLException se) {
            se.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } 
	}
	
	
	public static List<Cocktail> findCocktailByIDList(List<Integer> idList) {
		
		ArrayList<Cocktail> cList = new ArrayList<>();
		
		Statement stateSet = null;
		Connection connectionSet = null;
		try {
			
			connectionSet = DriverManager.getConnection(dburl, user, pass);
            stateSet = connectionSet.createStatement();
             
            for(int i = 0; i < idList.size(); i++) {
				
            	int id = idList.get(i);
            	
            	String mysql = "SELECT * FROM test.cocktail where cocktailID = '" + id + "';";
            	ResultSet res = stateSet.executeQuery(mysql);
            	res.next();
            	Cocktail c = cocktailFromRS(res, id);
            	cList.add(c);
            	res.close();
            
            }
            
            stateSet.close();
            connectionSet.close();
			
		} catch (SQLException e1) {
            e1.printStackTrace();
            return cList;
        } catch (Exception e2) {
            e2.printStackTrace();
            return cList;
        } finally {
            try {
                if (stateSet != null)
                    stateSet.close();
            } catch (SQLException e3) {
            	e3.printStackTrace();
            	return cList;
            }
            try {
                if (connectionSet != null)
                    connectionSet.close();
            } catch (SQLException e4) {
                e4.printStackTrace();
                return cList;
            }
		}
		
		return cList;
	}
	
	
}
