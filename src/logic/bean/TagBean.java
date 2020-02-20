package logic.bean;

public class TagBean {
	
	private String tagName;
	
	public TagBean(String t) {
		this.setTagName(t);
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

}
