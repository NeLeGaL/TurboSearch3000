package ts3000.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.concurrent.Semaphore;

class IndexatorAndFinder {
	private static final String DELIMETERS = "\t\n .<>,!&?$%#@()";
	private FileWorker fileWorker;
	private Map<String, HashSet<Integer>> grams = new HashMap<String, HashSet<Integer>>();
	private Semaphore synchSemaphore = new Semaphore(1);
	
	public IndexatorAndFinder(String filename) {
		//fileWorker creation
	}
	
	List<Document> processQuery(String query) {
		StringTokenizer tokenizer = new StringTokenizer(query, DELIMETERS);
		String word = tokenizer.nextToken();
		
		try {
			synchSemaphore.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HashSet<Integer> ans = grams.get(word);
		
		while (tokenizer.hasMoreTokens()) {
			word = tokenizer.nextToken();
			ans.retainAll(grams.get(word));
		}
		synchSemaphore.release();
		
		return fileWorker.getListOfDocuments(ans);
	}
	
	void indexIt(List<Document> listOfDocuments) {
		Map<String, HashSet<Integer>> newGrams = new HashMap<String, HashSet<Integer>>();
		for (int i = 0; i < listOfDocuments.size(); ++i) {
			indexOneDocument(listOfDocuments.get(i).getPlainText(), i, newGrams);
		}
		
		try {
			synchSemaphore.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		grams = newGrams;
		synchSemaphore.release();
	}
	
	private void indexOneDocument(String document, int documentNumber, Map<String, HashSet<Integer>> grams) {
		StringTokenizer tokenizer = new StringTokenizer(document, DELIMETERS);
		while(tokenizer.hasMoreTokens()) {
			String word = tokenizer.nextToken();
			HashSet<Integer> cur = grams.get(word);
			cur.add(documentNumber);
		}
	}
	
}
