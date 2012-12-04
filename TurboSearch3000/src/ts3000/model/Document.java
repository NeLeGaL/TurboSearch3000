package ts3000.model;

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
	
	private String title;
	private String annotation;
	private String text;
}