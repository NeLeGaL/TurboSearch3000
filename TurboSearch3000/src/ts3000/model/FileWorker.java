package ts3000.model;

import java.util.HashSet;
import java.util.List;

interface FileWorker {
	List<Document> getListOfDocuments(HashSet<Integer> documentNumbers);
}
