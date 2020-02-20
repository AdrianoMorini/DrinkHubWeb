package logic.dao;

public class BarUserDaoFactory {

	private static BarUserDaoFactory instance = null;
	
	public  static final synchronized BarUserDaoFactory getInstance() {
		if (BarUserDaoFactory.instance == null) {
			BarUserDaoFactory.instance = new BarUserDaoFactory();
		}
		return instance;
	}
	
	public BarUserDao createUtenteBarDao(String dbType) {
		if (dbType == "mariaDB") {
			return createUtenteBarDaoMDB();
		} else {
			throw new IllegalArgumentException("The specified database type is not impemented");
		}
	}
	
	private BarUserDaoMariaDBImpl createUtenteBarDaoMDB() {
		return BarUserDaoMariaDBImpl.getInstance();
	}
}
