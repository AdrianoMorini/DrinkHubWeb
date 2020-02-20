package logic.bean;

public class ProfileBean {
	private static String username;
	private static String name;
	private static String surname;
	private static String image;
	private static Double longitude;
	private static Double latitude;
	private static boolean isBar;

	private ProfileBean() {

	}

	public static void setUsername(String user) {
		ProfileBean.username = user;
	}

	public static String getUsername() {
		return ProfileBean.username;
	}

	public static String getName() {
		return ProfileBean.name;
	}

	public static void setUName(String name) {
		ProfileBean.name = name;
	}

	public static String getSurname() {
		return ProfileBean.surname;
	}

	public static void setSurname(String surname) {
		ProfileBean.surname = surname;
	}

	public static String getImage() {
		return ProfileBean.image;
	}

	public static void setImage(String image) {
		ProfileBean.image = image;
	}

	public static Double getLongitude() {
		return ProfileBean.longitude;
	}

	public static void setLongitude(Double longitude) {
		ProfileBean.longitude = longitude;
	}

	public static Double getLatitude() {
		return ProfileBean.latitude;
	}

	public static void setLatitude(Double latitude) {
		ProfileBean.latitude = latitude;
	}

	public static boolean getIsBar() {
		return isBar;
	}

	public static void setIsBar(boolean isBar) {
		ProfileBean.isBar = isBar;
	}
}
