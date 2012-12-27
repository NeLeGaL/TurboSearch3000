package ts3000.model;

import java.sql.Date;

import org.jsoup.Jsoup;

public class Document implements Comparable<Document> {
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
    
    // It's specially for FoundDocumentWithRangeParameter
    public Double getRangeParameter() {
    	return new Double(0);
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
	
	public Document(Document document) {
		this.title = document.title;
		this.annotation = document.annotation;
		this.htmlText = document.htmlText;
		this.plainText = document.plainText;
		this.date = document.date;
		this.category = document.category;
	}

	private String title;
	private String annotation;
	private String htmlText;
	private String plainText;
    private String category;
    private Date date;
	
    @Override
	public int compareTo(Document arg0) {
    	return this.getRangeParameter().compareTo(arg0.getRangeParameter()) ;
	}

}