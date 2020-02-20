package logic.model;

import java.sql.Date;

public class Sponsor {
	private String username;
    private int type;
    private Date timeline;
    
    public Sponsor(String username, int type, Date timeline) {
    	this.setUsername(username);
    	this.setType(type);
    	this.setTimeline(timeline);
    }
    
    public Sponsor() {
    	
    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Date getTimeline() {
		return timeline;
	}

	public void setTimeline(Date timeline) {
		this.timeline = timeline;
	}
}
