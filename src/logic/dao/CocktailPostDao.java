package logic.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
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
	private static String pass = "password";
    private static String user = "root";
    private static String dburl = "jdbc:mysql://localhost:3306/test";

    
    private CocktailPostDao() {
        throw new IllegalStateException("Utility class");
    }
	
	public static CocktailPostBean findPostsByUsername() {
		Statement statement = null;
        Connection connection = null;
        int id = 0;
        String name = "";
        Date date = null;
        String image = "";
        
        ArrayList<Cocktail> detail = new ArrayList<>();
        CocktailPostController controllerPost = CocktailPostController.getInstance();
        CocktailPostBean bean = controllerPost.getBean();
       
        try {
            connection = DriverManager.getConnection(dburl, user, pass);
            
            statement = connection.createStatement();
            String sql = "SELECT cocktailID, cocktailName, cocktailDate, image FROM test.cocktail WHERE cocktailUser='"+ bean.getUsername() +"' order by cocktailDate DESC;";
            ResultSet rs = statement.executeQuery(sql);

            if (!rs.first()) {
            	//array vuoto
            	bean.setPostList(null);
                return bean;
            }
            do {
            	id = rs.getInt("cocktailID");
            	name = rs.getString("cocktailName");
            	date = rs.getDate("cocktailDate");
            	image = rs.getString("image");
            	ArrayList<Tag> tag = new ArrayList<>();
            	ArrayList<Ingredient> ing = new ArrayList<>();
            	Recipe r = new Recipe("", ing);
            	Cocktail c = new Cocktail(bean.getUsername(), name, tag, r, date, id, image);
            	detail.add(c);
            	
            } while(rs.next());
            
            rs.close();
            statement.close();
            connection.close();
        } catch (SQLException se1) {
            se1.printStackTrace();
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (statement != null)
                    statement.close();
            } catch (SQLException se1) {
            	se1.printStackTrace();
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se1) {
                se1.printStackTrace();
            }
        }
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
       
        try {
            conn = DriverManager.getConnection(dburl, user, pass);
            
            state = conn.createStatement();
            String sql = "select cocktailID, cocktailUser, cocktailName, cocktailDate, image from test.cocktail where cocktailUser in (select username from test.sponsor where type = 2 || type=3) order by rand() limit 2;";
            ResultSet rs = state.executeQuery(sql);

            if (!rs.first()) {
            	//array vuoto
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
            state.close();
            conn.close();
        } catch (SQLException se3) {
            se3.printStackTrace();
        } catch (Exception e3) {
            e3.printStackTrace();
        } finally {
            try {
                if (state != null)
                    conn.close();
            } catch (SQLException se3) {
            	se3.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se3) {
                se3.printStackTrace();
            }
        }
        bean.setPostList(detail);
        return null;
	}
	
}
