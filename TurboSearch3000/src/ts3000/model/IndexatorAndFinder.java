package ts3000.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.concurrent.Semaphore;

class IndexatorAndFinder {
	static final String DELIMETERS = "\t\n .<>,!&?$%#@()[]{}";
	private FileWorker fileWorker;
	private Map<String, HashSet<Integer>> grams = new HashMap<String, HashSet<Integer>>();
	private Semaphore synchSemaphore = new Semaphore(1);
	private IndexFindStrategy strategy;
	
	public IndexatorAndFinder(String filename, IndexFindStrategy strategy) {
		//fileWorker creation
		this.strategy = strategy;
	}
	
	List<Document> processQuery(String query) {
		return fileWorker.getListOfDocuments(strategy.find(query, grams, synchSemaphore));
	}
	
	void indexIt(List<Document> listOfDocuments) {
		strategy.indexIt(listOfDocuments, grams, synchSemaphore);
	}
		
}
