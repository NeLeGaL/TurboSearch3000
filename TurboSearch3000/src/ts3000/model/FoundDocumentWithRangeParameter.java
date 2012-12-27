package ts3000.model;

import java.sql.Date;
import java.util.HashSet;
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
		
		if (wordsAmount != 0) {
			rangeIndex = result / (double)wordsAmount;
		}
		else {
			rangeIndex = 0;
		}
	}
	
	@Override 
	public Double getRangeParameter() {
		return new Double(rangeIndex);
	}	

	private double rangeIndex;

}
