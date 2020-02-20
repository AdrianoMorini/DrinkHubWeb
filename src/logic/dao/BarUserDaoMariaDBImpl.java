package logic.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import logic.model.Address;
import logic.model.BarUser;
import logic.model.User;

public class BarUserDaoMariaDBImpl implements BarUserDao {

	private static BarUserDaoMariaDBImpl instance = null;
	
	public  static final synchronized BarUserDaoMariaDBImpl getInstance() {
		if (BarUserDaoMariaDBImpl.instance == null) {
			BarUserDaoMariaDBImpl.instance = new BarUserDaoMariaDBImpl();
		}
		return instance;
	}
	
	private BarUserDaoMariaDBImpl() {
		
	}
	
	@Override
    public ArrayList<BarUser> getAllBarsByLocation() {
    	
    	Statement stmt = null;
        Connection conn = null;
        ArrayList<BarUser> bar = new ArrayList<>();
        DatabaseManager dbMan = MariaDBDatabaseManager.getInstance();

        try {
            
        	conn = dbMan.openConnection();
            stmt = dbMan.openStatement(conn);
            
            String sql = "SELECT * FROM test.user WHERE isBar = 1;";
            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next()) {
            	
            	String usBUsername = rs.getString("username");
            	String usBI = rs.getString("image");
            	Double usBLat = rs.getDouble("userLat");
            	Double usBLon = rs.getDouble("userLon");
            	String usBAddr = rs.getString("address");
            	String usBName = rs.getString("name");
            	String usBSurn = rs.getString("surname");
            	Boolean usBb = rs.getBoolean("isBar");
            	Address addre = new Address(usBLat, usBLon, usBAddr);
            	BarUser uBar = new BarUser(usBUsername, usBName,
            			usBSurn, usBI, addre, usBb);
            	bar.add(uBar);
            }
            rs.close();
            
        } catch (SQLException e) {
			// Opening connection, or opening statement, or executing query failed
            e.printStackTrace();
            
        }
    
		dbMan.closeStatement(stmt);
		dbMan.closeConnection(conn);
        return bar;
    }

	@Override
	public ArrayList<User> getAllUserByName() {
		
		Statement stmt = null;
        Connection conn = null;
        DatabaseManager dbMan = MariaDBDatabaseManager.getInstance();

        ArrayList<User> usr = new ArrayList<>();
        
        try {
          
        	conn = dbMan.openConnection();
            stmt = dbMan.openStatement(conn);
            String sql = "SELECT * FROM test.user;";
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()) {
            
            	String username = rs.getString("username");
            	String image = rs.getString("image");
            	Double latitude = rs.getDouble("userLat");
            	Double longitude = rs.getDouble("userLon");
            	String address = rs.getString("address");
            	String name = rs.getString("name");
            	String surname = rs.getString("surname");
            	Address addr = new Address(latitude, longitude, address);
            	User   user1 = new User(username, name, surname, image, addr);
            	usr.add(user1); 	
            }
            rs.close();
            
        } catch (SQLException e1) {
			// Opening connection, or opening statement, or executing query failed
            e1.printStackTrace();
            
        }
    
		dbMan.closeStatement(stmt);
		dbMan.closeConnection(conn);
        return usr;
	}
}
