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
        
        // from UI team - for tests
        public Document(String title, String annotation, String text)
        {
            this.title = title;
            this.annotation = annotation;
            this.text = text;
        }
	
	private String title;
	private String annotation;
	private String text;
}