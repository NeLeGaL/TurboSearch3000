package ts3000.model;

import java.util.ArrayList;


import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;


public class Database {
	private static Logger DatabaseLogger = Logger.getLogger(Database.class);
	private IndexatorAndFinder indexatorAndFinder;
	
	public Database(String filename) {
		BasicConfigurator.configure();
		indexatorAndFinder = new IndexatorAndFinder(filename, new RegexIndexFindStrategy());
	}
	
	public ArrayList<Document> getDocsByCategory(String category) {
    	return indexatorAndFinder.getDocsByCategory(category);
    }
        
    public ArrayList<Category> getCategories() {
    	return indexatorAndFinder.getCategories();
    }
        
    public Document getDocument(String category, String title) {
    	return indexatorAndFinder.getDocument(category, title);
    }
                
        
	// one word query - just for now
	public ArrayList<Document> proccessQuery(String query) {
		return indexatorAndFinder.processQuery(query);
	}

	
}

