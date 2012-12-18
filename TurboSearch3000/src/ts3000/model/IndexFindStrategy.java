package ts3000.model;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Semaphore;

public interface IndexFindStrategy {
	void indexIt(List<Document> listOfDocuments, Map<String, HashSet<Integer>> grams, Semaphore synchSemaphore);
	HashSet<Integer> find(String query, Map<String, HashSet<Integer>> grams, Semaphore synchSemaphore);
}
