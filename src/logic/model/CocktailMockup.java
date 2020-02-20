package logic.model;

public class CocktailMockup 
{
	private String title;
	private String user;
	private String text;
	
	
	public CocktailMockup(String title, String userID, String text) 
	{
		this.setUser(userID);
		this.setTitle(title);
		this.setText(text);
	}


	public String getTitle() 
	{
		return title;
	}


	public void setTitle(String title) 
	{
		this.title = title;
	}


	public String getUser() 
	{
		return user;
	}


	public void setUser(String user) 
	{
		this.user = user;
	}


	public String getText() 
	{
		return text;
	}


	public void setText(String text) 
	{
		this.text = text;
	}
}
