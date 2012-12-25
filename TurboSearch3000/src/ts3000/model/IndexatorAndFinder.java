
package ts3000.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.concurrent.Semaphore;

class IndexatorAndFinder {
	static final String DELIMETERS = "\t\n .<>,!&?$%#@()[]{}-";
	private FileWorker fileWorker;
	private Map<String, HashSet<Integer>> grams = new HashMap<String, HashSet<Integer>>();
	private Semaphore synchSemaphore = new Semaphore(1);
	private IndexFindStrategy strategy;
	
	public IndexatorAndFinder(String filename, IndexFindStrategy strategy) {
		this.strategy = strategy;
		fileWorker = new FileWorker(this, filename);
		fileWorker.start();
	}
	
	ArrayList<Document> processQuery(String query) {
		return fileWorker.getListOfDocuments(strategy.find(query, grams, synchSemaphore));
	}
	
	void indexIt(ArrayList<Document> listOfDocuments) {
		Map<String, HashSet<Integer>> result;
		
		result = strategy.indexIt(listOfDocuments);
		
		try {
			synchSemaphore.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		grams = result;
		
		synchSemaphore.release();
	}
		
	public ArrayList<Document> getDocsByCategory(String category) {
    	return fileWorker.getDocsByCategory(category);
    }
        
    public ArrayList<Category> getCategories() {
    	return fileWorker.getCategories();
    }
        
    public Document getDocument(String category, String title) {
    	return fileWorker.getDocument(category, title);
    }
}

