package ts3000.model;

import java.util.Date;

public class Document {
	public String getTitle() {
		return title;
	}
	
	public String getAnnotation() {
		return annotation;
	}
	
	public String getText() {
		return text;
	}
	
	public Date getDate() {
		return date;
	}
	
	String getPlainText() {
		// some code here
	}
	
	private String title;
	private Date date;
	private String annotation;
	private String text;
}