package logic.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import logic.bean.LoginBean;
import logic.bean.SignUpBean;
import logic.model.Address;

public class UserDao {
	    
	    private UserDao() {
	        throw new IllegalStateException("Utility class");
	    }
	    
	    public static LoginBean findByUserAndPassword(LoginBean bean) {
	    	Statement statement = null;
	        Connection connection = null;
	        DatabaseManager dbMan = MariaDBDatabaseManager.getInstance();
	        
	        String nome = "";
	        String cognome = "";
	        Double latitude = 0.0;
	        Double longitude = 0.0;
	        String image = "";
	        boolean isBar = false;
	        boolean isAdmin = false;
	       
	        try {
	        	//Class.forName("com.mysql.cj.jdbc.Driver") nella parte web
	        	connection = dbMan.openConnection();
	            statement = dbMan.openStatement(connection);

	            String sql = "SELECT * FROM test.user where username = '"
	                    + bean.getUsername() + "' AND password = '" + bean.getPassword() + "';";
	            ResultSet rs = statement.executeQuery(sql);

	            if (!rs.first()) {
	                bean.setUName(null);
	                bean.setSurname(null);
	                dbMan.closeStatement(statement);
	                dbMan.closeConnection(connection);
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
	            isBar = (rs.getInt("isBar") == 1);
	            isAdmin = (rs.getInt("isAdmin") == 1);

	            rs.close();
	            
	        } catch (SQLException e) {
				// Opening connection, or opening statement, or executing query failed
	            e.printStackTrace();
	            bean.setUName(null);
                bean.setSurname(null);
                dbMan.closeStatement(statement);
                dbMan.closeConnection(connection);
            	return bean;
	        }

	       bean.setUName(nome);
	       bean.setSurname(cognome);
	       bean.setImage(image);
	       Address a = new Address(longitude, latitude, "");
	       bean.setAddr(a); 
	       if(isBar) {
	    	   bean.setTypeUser(1);
	       }
	       else if(isAdmin) {
	    	   bean.setTypeUser(2);
	       }
	       dbMan.closeStatement(statement);
	       dbMan.closeConnection(connection);
	       return bean;
	    }
	    
	    public static boolean findExistingUsername(String username) {
	    	Statement statement = null;
	        Connection connection = null;
	        DatabaseManager dbMan = MariaDBDatabaseManager.getInstance();

	        try {
	        	//Class.forName("com.mysql.cj.jdbc.Driver") nella parte web
	        	connection = dbMan.openConnection();
	            statement = dbMan.openStatement(connection);

	            String sql = "SELECT  username FROM test.user where username = '"
	                    + username + "';";
	            ResultSet rs = statement.executeQuery(sql);

	            if (!rs.first()) {
	            	rs.close();
	            	dbMan.closeStatement(statement);
	                dbMan.closeConnection(connection);
	            	return false;
	            }
	            else {
	            	dbMan.closeStatement(statement);
	                dbMan.closeConnection(connection);
	            	rs.close();
	            	return true;
	            }
	            
	            
	        } catch (SQLException e) {
				// Opening connection, or opening statement, or executing query failed
	            e.printStackTrace();
	            dbMan.closeStatement(statement);
                dbMan.closeConnection(connection);
            	return false;
	        }
	    }
	    
	    public static void insertNewUser(SignUpBean bean) {
	    	Statement statement = null;
	        Connection connection = null;
	        DatabaseManager dbMan = MariaDBDatabaseManager.getInstance();
	        
	        String username = bean.getUsernameSign();
	        String password = bean.getPasswordSign();
	        String name = bean.getNameSign();
	        String surname = bean.getSurnameSign();
	        Address a = bean.getAddressSign();
	        Double lat = a.getLatitude();
	        Double lon = a.getLongitude();
	        String addr = a.getAddress();
	        String image = bean.getImageSign();
	        int isBar = bean.getIsBarSign();

	        try {
	        	//Class.forName("com.mysql.cj.jdbc.Driver") nella parte web
	        	connection = dbMan.openConnection();
	            statement = dbMan.openStatement(connection);

	            String sql = "INSERT INTO test.user VALUES ('"
	                    + username + "', '" + password +"', '" + name + "', '" + surname +"', " 
	            		+ lat +", " + lon +", '" + image + "', " + isBar + ", 0,'" + addr +"');";
	            ResultSet rs = statement.executeQuery(sql);

	            if (!rs.first()) {
	            	dbMan.closeStatement(statement);
	                dbMan.closeConnection(connection);
	            	return;
	            }
	            
	            rs.close();
	            
	        } catch (SQLException e1) {
				// Opening connection, or opening statement, or executing query failed
	            e1.printStackTrace();
	        }
	        dbMan.closeStatement(statement);
            dbMan.closeConnection(connection);
	        
	    }
}