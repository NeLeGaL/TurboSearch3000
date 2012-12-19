package ts3000.model;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Semaphore;

public interface IndexFindStrategy {
	Map<String, HashSet<Integer>> indexIt(List<Document> listOfDocuments);
	HashSet<Integer> find(String query, Map<String, HashSet<Integer>> grams, Semaphore synchSemaphore);
}
