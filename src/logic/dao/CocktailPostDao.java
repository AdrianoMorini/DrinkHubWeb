package logic.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import logic.bean.CocktailPostBean;
import logic.controller.CocktailPostController;
import logic.model.Cocktail;
import logic.model.Ingredient;
import logic.model.Recipe;
import logic.model.Tag;

public class CocktailPostDao {
    
    private CocktailPostDao() {
        throw new IllegalStateException("Utility class");
    }
	
	public static CocktailPostBean findPostsByUsername() {
		Statement statement = null;
        Connection connection = null;
        DatabaseManager dbMan = MariaDBDatabaseManager.getInstance();
        
        ArrayList<Cocktail> detail = new ArrayList<>();
        CocktailPostController controllerPost = CocktailPostController.getInstance();
        CocktailPostBean bean = controllerPost.getBean();
       
        try {
        	connection = dbMan.openConnection();
            statement = dbMan.openStatement(connection);
            String sql = "SELECT * FROM test.cocktail WHERE cocktailUser='"+ bean.getUsername() +"' order by cocktailDate DESC;";
            ResultSet rs = statement.executeQuery(sql);


            while(rs.next()) {
            	Cocktail c = CocktailDao.cocktailFromRS(rs, rs.getInt("cocktailID"));
            	detail.add(c);
           	
            }
            
            rs.close();
        } catch (SQLException e) {
			// Opening connection, or opening statement, or executing query failed
            e.printStackTrace();
        }
    
		dbMan.closeStatement(statement);
		dbMan.closeConnection(connection);
        bean.setPostList(detail);
        return null;
	}
	
	public static CocktailPostBean findSponsoredPosts() {
		Statement state = null;
        Connection conn = null;
        int id = 0;
        String username = "";
        String name = "";
        Date date = null;
        String image = "";
        
        ArrayList<Cocktail> detail = new ArrayList<>();
        CocktailPostController controllerPost = CocktailPostController.getInstance();
        CocktailPostBean bean = controllerPost.getBean();
        DatabaseManager dbMan = MariaDBDatabaseManager.getInstance();
       
        try {
        	conn = dbMan.openConnection();
            state = dbMan.openStatement(conn);
            String sql = "select cocktailID, cocktailUser, cocktailName, cocktailDate, image from test.cocktail where cocktailUser in (select username from test.sponsor where type = 2 || type=3) order by rand() limit 2;";
            ResultSet rs = state.executeQuery(sql);

            if (!rs.first()) {
            	// empty array
            	bean.setPostList(null);
                return bean;
            }
            do {
            	id = rs.getInt("cocktailID");
            	username = rs.getString("cocktailUser");
            	name = rs.getString("cocktailName");
            	date = rs.getDate("cocktailDate");
            	image = rs.getString("image");
            	ArrayList<Tag> tag = new ArrayList<>();
            	ArrayList<Ingredient> ing = new ArrayList<>();
            	Recipe r = new Recipe("", ing);
            	Cocktail c = new Cocktail(username, name, tag, r, date, id, image);
            	detail.add(c);
            	
            } while(rs.next());
            
            rs.close();
        } catch (SQLException e8) {
			// Opening connection, or opening statement, or executing query failed
            e8.printStackTrace();
    		dbMan.closeStatement(state);
    		dbMan.closeConnection(conn);
        }
    
		dbMan.closeStatement(state);
		dbMan.closeConnection(conn);
        bean.setPostList(detail);
        return null;
	}
	
}
