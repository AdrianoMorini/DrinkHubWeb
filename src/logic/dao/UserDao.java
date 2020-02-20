package logic.dao;
import java.sql.*;

import logic.bean.LoginBean;
import logic.model.Address;

public class UserDao {
	    private static String pass = "password";
	    private static String user = "root";
	    private static String dburl = "jdbc:mysql://localhost:3306/test";
	
	    
	    private UserDao() {
	        throw new IllegalStateException("Utility class");
	    }
	    
	    public static LoginBean findByUserAndPassword(LoginBean bean) {
	    	Statement statement = null;
	        Connection connection = null;
	        String nome = "";
	        String cognome = "";
	        Double latitude = 0.0;
	        Double longitude = 0.0;
	        String image = "";
	       
	        try {
	        	Class.forName("com.mysql.cj.jdbc.Driver");
	            connection = DriverManager.getConnection(dburl, user, pass);

	            statement = connection.createStatement();
	            String sql = "SELECT * FROM test.user where username = '"
	                    + bean.getUsername() + "' AND password = '" + bean.getPassword() + "';";
	            ResultSet rs = statement.executeQuery(sql);

	            if (!rs.first()) {
	                bean.setUName(null);
	                bean.setSurname(null);
	            	return bean;
	            }

	            boolean moreThanOne = rs.first() && rs.next();
	            assert !moreThanOne;
	            rs.first();

	            nome = rs.getString("name");
	            cognome = rs.getString("surname");
	            latitude = rs.getDouble("userLat");
	            longitude = rs.getDouble("userLon");
	            image = rs.getString("image");

	            rs.close();
	            statement.close();
	            connection.close();
	            
	        } catch (SQLException se) {
	            se.printStackTrace();
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                if (statement != null)
	                    statement.close();
	            } catch (SQLException se2) {
	            	se2.printStackTrace();
	            }
	            try {
	                if (connection != null)
	                    connection.close();
	            } catch (SQLException se) {
	                se.printStackTrace();
	            }
	        }

	       bean.setUName(nome);
	       bean.setSurname(cognome);
	       bean.setImage(image);
	       Address a = new Address(longitude, latitude, "");
	       bean.setAddr(a); 
	       return bean;
	    }
}