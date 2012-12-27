package ts3000.model;

import java.sql.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.StringTokenizer;

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
		wordInDocumentAmount = new HashMap<String, Integer>();
        
        //calculateWordStatistic();
    }
	
	public Document(Document document) {
		this.title = document.title;
		this.annotation = document.annotation;
		this.htmlText = document.htmlText;
		this.plainText = document.plainText;
		this.date = document.date;
		this.category = document.category;
		this.wordsAmount = document.wordsAmount;
		wordInDocumentAmount = new HashMap<String, Integer> (document.wordInDocumentAmount);

		//calculateWordStatistic();
	}

    @Override
	public int compareTo(Document arg0) {
    	return this.getRangeParameter().compareTo(arg0.getRangeParameter()) ;
	}

    void addNewWordToStatistic(String form) {
		++wordsAmount;
		if (wordInDocumentAmount.containsKey(form)) {
			wordInDocumentAmount.put(form, wordInDocumentAmount.get(form) + 1);
		}
		else {
			wordInDocumentAmount.put(form,  1);
		}
    	
    }
    
	private void calculateWordStatistic() {
		StringTokenizer tokenizer = new StringTokenizer(getDocumentText(), IndexatorAndFinder.DELIMETERS);
		wordInDocumentAmount = new HashMap<String, Integer>();

		while(tokenizer.hasMoreTokens()) {
			String word = tokenizer.nextToken();
			++wordsAmount;
			
			HashSet<String> forms = RegexIndexFindStrategy.tryNormalizeWord(word);
			for (String form : forms) {
				if (wordInDocumentAmount.containsKey(form)) {
					wordInDocumentAmount.put(form, wordInDocumentAmount.get(form) + 1);
				}
				else {
					wordInDocumentAmount.put(form,  1);
				}
			}
		}
	}

	private String getDocumentText() {
		StringBuilder sb = new StringBuilder(this.getPlainText());
		sb.append(" ");
		sb.append(this.getAnnotation());
		sb.append(" ");
		sb.append(this.getTitle());
		sb.append(" ");
		sb.append(this.getCategory());
		
		return sb.toString();
	}
	
	
    
	protected int wordsAmount = 0;
	protected Map<String, Integer> wordInDocumentAmount;
	
	private String title;
	private String annotation;
	private String htmlText;
	private String plainText;
    private String category;
    private Date date;
	
    

}