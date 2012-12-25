
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
			System.out.println("Cannot index");
			return;
		}
		
		grams = result;
		
		synchSemaphore.release();
	}
		
	public ArrayList<Document> getDocsByCategory(String category) {
    	return fileWorker.getDocsByCategory(category);
    }
        
    public ArrayList<Category> getCategories() {
    	ArrayList<Category> categories = fileWorker.getCategories();
    	if (categories.size() >= 3) {
    		for (int i = 0; i < categories.size(); ++i) {
    			if (categories.get(i).getName().equals("Секс")) {
    				swap(categories, 0, i);
    				continue;
    			}
    			if (categories.get(i).getName().equals("Наркотики")) {
    				swap(categories, 1, i);
    				continue;
    			}
    			if (categories.get(i).getName().equals("Рок-н-ролл")) {
    				swap(categories, 2, i);
    				continue;
    			}    			
    		}
    	}
    	return categories;
    }
        
    public Document getDocument(String category, String title) {
    	return fileWorker.getDocument(category, title);
    }
    
    private void swap(ArrayList<Category> a, int i, int j) {
    	Category k = a.get(i);
    	a.set(i, a.get(j));
    	a.set(j, k);
    }
    
    void finishIt() {
    	System.out.println("Indexator and finder: finishing...");
    	fileWorker.interrupt();
    }
}

