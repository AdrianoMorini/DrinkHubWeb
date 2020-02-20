package logic.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import logic.model.Address;
import logic.model.BarUser;
import logic.model.User;

public class BarUserDaoMariaDBImpl implements BarUserDao {

	
	private static String pass = "password";
	private static String user = "root";
	private static String dburl = "jdbc:mysql://localhost:3306/test";
	private static BarUserDaoMariaDBImpl instance = null;
	
	public synchronized static final BarUserDaoMariaDBImpl getInstance() {
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
        ArrayList<BarUser> Bar = new ArrayList<BarUser>();
        
        try {
            conn = DriverManager.getConnection(dburl, user, pass);

            stmt = conn.createStatement();
            String sql = "SELECT * FROM test.user WHERE isBar = 1;";
            ResultSet rs = stmt.executeQuery(sql);

            if (!rs.first()) {
                return null;
            }
            
            do {
            	
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
            	Bar.add(uBar);
            	
            	
            	
            }while(rs.next());
            

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            	se2.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        

        return Bar;
    }

	@Override
	public ArrayList<User> getAllUserByName() {
		
		Statement stmt = null;
        Connection conn = null;
        ArrayList<User> Usr = new ArrayList<User>();
        
        try {
            conn = DriverManager.getConnection(dburl, user, pass);

            stmt = conn.createStatement();
            String sql = "SELECT * FROM test.user;";
            ResultSet rs = stmt.executeQuery(sql);

            if (!rs.first()) 
                return null;
            
            do {
            	
            	String username = rs.getString("username");
            	String image = rs.getString("image");
            	Double latitude = rs.getDouble("userLat");
            	Double longitude = rs.getDouble("userLon");
            	String address = rs.getString("address");
            	String name = rs.getString("name");
            	String surname = rs.getString("surname");
            	Address addr = new Address(latitude, longitude, address);
            	User   user = new User(username, name, surname, image, addr);
            	Usr.add(user);
            	
            	
            	
            }while(rs.next());
            

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            	se2.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        

        return Usr;
	}
}
