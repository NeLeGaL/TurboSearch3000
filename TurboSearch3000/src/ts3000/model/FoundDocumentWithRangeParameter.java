package ts3000.model;

import java.sql.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.StringTokenizer;

public class FoundDocumentWithRangeParameter extends Document {

	public FoundDocumentWithRangeParameter(String title, String annotation,
			String text, String category, Date date, String query) {
		super(title, annotation, text, category, date);		
		
		calculateRangeIndex(query);
	}

	public FoundDocumentWithRangeParameter(Document document, String query) {
		super(document);
		calculateRangeIndex(query);
	}
	
	private void calculateRangeIndex(String query) {
		Map<String, Integer> wordInDocumentAmount = getWordStatistic();
		
		StringTokenizer tokenizer = new StringTokenizer(query, IndexatorAndFinder.DELIMETERS);
		
		int result = 0;
		while (tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken().toLowerCase();
			HashSet<String> forms = RegexIndexFindStrategy.tryNormalizeWord(token);
			
			for (String form : forms) {
				if (wordInDocumentAmount.containsKey(form)) {
					result += wordInDocumentAmount.get(form);
				}
			}
		}
		
		rangeIndex = result / (double)wordsAmount;
	}
	
	@Override 
	public Double getRangeParameter() {
		return new Double(rangeIndex);
	}
	
	private Map<String, Integer> getWordStatistic() {
		StringTokenizer tokenizer = new StringTokenizer(getDocumentText(), IndexatorAndFinder.DELIMETERS);
		Map<String, Integer> wordInDocumentAmount = new HashMap<String, Integer>();

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
		
		return wordInDocumentAmount;
	}

	
	private String getDocumentText() {
		StringBuilder sb = new StringBuilder(super.getPlainText());
		sb.append(" ");
		sb.append(super.getAnnotation());
		sb.append(" ");
		sb.append(super.getTitle());
		sb.append(" ");
		sb.append(super.getCategory());
		
		return sb.toString();
	}
	
	private int wordsAmount = 0;
	private double rangeIndex;

}
