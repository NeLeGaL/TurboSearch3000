package ts3000.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.concurrent.Semaphore;

public class ExactIndexFindStrategy implements IndexFindStrategy {

	@Override
	public void indexIt(List<Document> listOfDocuments,
			Map<String, HashSet<Integer>> grams, Semaphore synchSemaphore) {
		
		Map<String, HashSet<Integer>> newGrams = new HashMap<String, HashSet<Integer>>();
		for (int i = 0; i < listOfDocuments.size(); ++i) {
			indexOneDocument(listOfDocuments.get(i).getPlainText(), i, newGrams);
		}
		
		try {
			synchSemaphore.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		grams = newGrams;
		synchSemaphore.release();
	}

	@Override
	public HashSet<Integer> find(String query, Map<String, HashSet<Integer>> grams, Semaphore synchSemaphore) {
		StringTokenizer tokenizer = new StringTokenizer(query, IndexatorAndFinder.DELIMETERS);
		String word = tokenizer.nextToken();
		
		try {
			synchSemaphore.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		HashSet<Integer> ans = grams.get(word);
		
		while (tokenizer.hasMoreTokens()) {
			word = tokenizer.nextToken();
			if (ans != null) 
				ans.retainAll(grams.get(word.toLowerCase()));
			else {
				ans = grams.get(word.toLowerCase());
			}
		}
		synchSemaphore.release();
		
		return ans;
	}
	
	private void indexOneDocument(String document, int documentNumber, Map<String, HashSet<Integer>> grams) {
		StringTokenizer tokenizer = new StringTokenizer(document, IndexatorAndFinder.DELIMETERS);
		while(tokenizer.hasMoreTokens()) {
			String word = tokenizer.nextToken();
			HashSet<Integer> cur = grams.get(word.toLowerCase());
			if (cur != null)
				cur.add(documentNumber);
			else {
				HashSet<Integer> newHashSet = new HashSet<Integer>();
				newHashSet.add(documentNumber);
				grams.put(word.toLowerCase(), newHashSet);
			}
		}
	}
	
}