package logic.dao;
import java.sql.*;

import logic.bean.SponsorBean;
import logic.controller.LoginController;

public class SponsorDao {
	private static String passDatab = "password";
    private static String userDatab = "root";
    private static String urlDb = "jdbc:mysql://localhost:3306/test";

    
    private SponsorDao() {
        throw new IllegalStateException("Utility class init");
    }
    
    public static void findSponsorByUsername(SponsorBean bean) {
    	Statement state = null;
        Connection connect = null;
        int type = 0;
        Date timeline = null;
        try {
            connect = DriverManager.getConnection(urlDb, userDatab, passDatab);

            state = connect.createStatement();
            String sql = "SELECT type, timeline FROM test.sponsor where username = '"
                    + bean.getUserSponsor() + "';";
            ResultSet rs = state.executeQuery(sql);

            if (!rs.first()) {
            	bean.setTypeSponsor(0);
            	bean.setTimeSponsor(null);
            	return;
            }

            boolean moreThanOne = rs.first() && rs.next();
            assert !moreThanOne;
            rs.first();

            type = rs.getInt("type");
            timeline = rs.getDate("timeline");

            rs.close();
            state.close();
            connect.close();
        } catch (SQLException se1) {
        	
        	bean.setTypeSponsor(0);
        	bean.setTimeSponsor(null);
            
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (state != null)
                    state.close();
            } catch (SQLException se1) {
            	se1.printStackTrace();
            }
            try {
                if (connect != null)
                    connect.close();
            } catch (SQLException se1) {
                se1.printStackTrace();
            }
        }
    	
    	bean.setTypeSponsor(type);
    	bean.setTimeSponsor(timeline);
    }
    
    
    public static boolean setNewSponsor() {
    	Statement stat = null;
        Connection conne = null;
        try {
            conne = DriverManager.getConnection(urlDb, userDatab, passDatab);

            stat = conne.createStatement();
            LoginController con = LoginController.getInstance();
            String mysql = "INSERT INTO test.sponsor values('" + con.getBean().getUsername() +"','" + SponsorBean.getTyping() + "','" + SponsorBean.getTimeline() + "');";
            ResultSet res = stat.executeQuery(mysql);

            res.close();
            stat.close();
            conne.close();
        } catch (SQLException se2) {
            se2.printStackTrace();
            return false;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        } finally {
            try {
                if (stat != null)
                    stat.close();
            } catch (SQLException se2) {
            	se2.printStackTrace();
            	return false;
            }
            try {
                if (conne != null)
                    conne.close();
            } catch (SQLException se2) {
                se2.printStackTrace();
                return false;
            }
        }
        return true;
    }
    
    public static void deleteSponsor() {
    	Statement state = null;
        Connection connect = null;
        try {
            connect = DriverManager.getConnection(urlDb, userDatab, passDatab);

            state = connect.createStatement();
            String sql = "DELETE FROM test.sponsor WHERE (timeline < current_date);";
            state.executeUpdate(sql);

            state.close();
            connect.close();
        } catch (SQLException se3) {
        	se3.printStackTrace();
            
        } catch (Exception e3) {
            e3.printStackTrace();
        } finally {
            try {
                if (state != null)
                    state.close();
            } catch (SQLException se3) {
            	se3.printStackTrace();
            }
            try {
                if (connect != null)
                    connect.close();
            } catch (SQLException se3) {
                se3.printStackTrace();
            }
        }
    }
    
    
}
