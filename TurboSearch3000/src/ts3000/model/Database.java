package ts3000.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;


public class Database {
	private static Logger DatabaseLogger = Logger.getLogger(Database.class);
	private IndexatorAndFinder indexatorAndFinder;
	
	public static void main(String ... args) {
		Database db = new Database("documents.txt");
	}
	
	public Database(String filename) {
		BasicConfigurator.configure();
		indexatorAndFinder = new IndexatorAndFinder(filename, new ExactIndexFindStrategy());
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

