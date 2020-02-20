package logic.dao;

import java.util.ArrayList;

import logic.model.BarUser;
import logic.model.User;

public interface BarUserDao {
	ArrayList<BarUser> getAllBarsByLocation();
	ArrayList<User> getAllUserByName();
	
}
