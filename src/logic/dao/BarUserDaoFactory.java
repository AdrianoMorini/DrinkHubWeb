package logic.dao;

public class BarUserDaoFactory {

	private static BarUserDaoFactory instance = null;
	
	public synchronized static final BarUserDaoFactory getInstance() {
		if (BarUserDaoFactory.instance == null) {
			BarUserDaoFactory.instance = new BarUserDaoFactory();
		}
		return instance;
	}
	
	public BarUserDao createUtenteBarDao(String dbType) {
		switch (dbType) {
		case "mariaDB":
			return createUtenteBarDaoMDB();
		default:
			throw new IllegalArgumentException("The specified database type is not impemented");
		}
	}
	
	private BarUserDaoMariaDBImpl createUtenteBarDaoMDB() {
		return BarUserDaoMariaDBImpl.getInstance();
	}
}
