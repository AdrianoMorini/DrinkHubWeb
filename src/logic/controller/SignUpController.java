package logic.controller;

import logic.bean.SignUpBean;
import logic.dao.UserDao;

public class SignUpController {
	private static SignUpController inst;
    public SignUpBean beanSign = new SignUpBean();
    
    public static SignUpController getInstance() {
        if (inst == null)
            inst = new SignUpController();
        return inst;
    }
    
    public SignUpBean getBean() {
		return this.beanSign;
	}

	public void setBean(SignUpBean bean) {
		this.beanSign = bean;
	}
	
	public boolean findUsername() {
		boolean find;
		find = UserDao.findExistingUsername(this.beanSign.getUsernameSign());
		return find;
	}
	
	public void saveNewUser() {
		UserDao.insertNewUser(this.beanSign);
	}
}
