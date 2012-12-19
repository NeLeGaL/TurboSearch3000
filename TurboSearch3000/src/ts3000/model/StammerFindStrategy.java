package ts3000.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.concurrent.Semaphore;

public class StammerIndexFindStrategy implements IndexFindStrategy {

	@Override
	public Map<String, HashSet<Integer>> indexIt(List<Document> listOfDocuments) {
		Map<String, HashSet<Integer>> newGrams = new HashMap<String, HashSet<Integer>>();
		for (int i = 0; i < listOfDocuments.size(); ++i) {
			indexOneDocument(listOfDocuments.get(i).getPlainText(), i, newGrams);
		}
		
		return newGrams;
	}

	@Override
	public HashSet<Integer> find(String query,
			Map<String, HashSet<Integer>> grams, Semaphore synchSemaphore) {
		StringTokenizer tokenizer = new StringTokenizer(query, IndexatorAndFinder.DELIMETERS);
		String word = tokenizer.nextToken().toLowerCase();
		
		try {
			synchSemaphore.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		HashSet<Integer> ans = processOneQueryWord(word, grams);
		
		while (tokenizer.hasMoreTokens()) {
			word = tokenizer.nextToken();
			if (ans != null) 
				ans.retainAll(processOneQueryWord(word, grams));
			else {
				break;
			}
		}
		synchSemaphore.release();
		
		if (ans == null) return new HashSet<Integer>();
		return ans;
	}
	
	private HashSet<Integer> processOneQueryWord(String word, Map<String, HashSet<Integer>> grams) {
		HashSet<Integer> ans = null;
		int x = 0;
		
		if (word.length() >= 3 && word.length() <= 5) 
			x = 1;
		
		if (word.length() >= 6 && word.length() <= 7)
			x = 2;
		
		if (word.length() > 7)
			x = 3;
		
		for (int i = 0; i <= x; ++i) {
			if (ans != null) {
				ans.addAll(grams.get(word.substring(0, word.length() - i)));
			}
			else {
				ans = grams.get(word.substring(0, word.length() - i));
			}
		}
		return ans;
	}
	
	private void indexOneDocument(String document, int documentNumber, Map<String, HashSet<Integer>> grams) {
		StringTokenizer tokenizer = new StringTokenizer(document, IndexatorAndFinder.DELIMETERS);
		while(tokenizer.hasMoreTokens()) {
			String word = tokenizer.nextToken();
			String wordToIndex = word;
			if (word.length() >= 3 && word.length() <= 5) {
				wordToIndex = word.substring(0, word.length() - 1);
			}

			if (word.length() >= 6 && word.length() <= 7) {
				wordToIndex = word.substring(0, word.length() - 2);
			}

			if (word.length() > 7) {
				wordToIndex = word.substring(0, word.length() - 3);
			}
			
			HashSet<Integer> cur = grams.get(wordToIndex.toLowerCase());
			if (cur != null)
				cur.add(documentNumber);
			else {
				HashSet<Integer> newHashSet = new HashSet<Integer>();
				newHashSet.add(documentNumber);
				grams.put(wordToIndex.toLowerCase(), newHashSet);
			}
		}
	}
	
}
