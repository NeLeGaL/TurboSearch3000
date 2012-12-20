package ts3000.model;

import java.util.Date;

import org.jsoup.Jsoup;

public class Document {
	public String getTitle() {
		return title;
	}
	
	public String getAnnotation() {
		return annotation;
	}
	
	public String getText() {
		return htmlText;
	}
        
    public String getCategory() {
    	return category;
    }
        
    public Date getDate() {
    	return date;
    }
    
    public String getPlainText() {
    	return plainText;
    }
    
    public Document(String title, String annotation, String text, String category, Date date)
    {
    	this.title = title;
        this.annotation = annotation;
        this.category = category;
        this.htmlText = text;
        this.date = date;
        this.plainText = Jsoup.parse(htmlText).text();
    }
	
	private String title;
	private String annotation;
	private String htmlText;
	private String plainText;
    private String category;
    private Date date;
}