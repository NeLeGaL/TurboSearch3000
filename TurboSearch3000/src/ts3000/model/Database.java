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
	
	public static void main(String ... args) {
		Database db = new Database("documents.txt");
	}
	
	public Database(String filename) {
		BasicConfigurator.configure();
		initDatabase(filename);
	}
	
	private String smartSplit(String source, String from, String to) {
		String result = "";
		int start = source.indexOf(from) + from.length();
		int finish = source.lastIndexOf(to);
		while (start < finish) {
			result += source.charAt(start);
			++start;
		}
		return result;
	}
	
	public void initDatabase(String filename) {
		this.filename = filename;
		
		categoryDocuments = new HashMap<String, HashMap<String,Document>>();
		documents = new ArrayList<Document>();
		categories = new ArrayList<Category>();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(filename)));
			
			String line;
			while ((line = br.readLine()) != null) {
				if (!line.contains("div id")) continue;
				
				String category = smartSplit(line, "div id=\"", "\">");
				
				line = br.readLine();
				String title = smartSplit(line, "<h2 id=\"docTitle\" style=\"\">", "</h2>");
				
				line = br.readLine();
				String annotation = smartSplit(line, "<div id=\"docAnnotation\" style=\"\">", "</div>");
				
				line = br.readLine();
				String date = smartSplit(line, "<div id=\"docDate\" style=\"\">", "</div>");
				
				line = br.readLine();
				StringBuilder sb = new StringBuilder();
				while (!line.contains("//END_OF_DOCUMENT")) {
					sb.append(line);
					sb.append("\n");
					line = br.readLine();
				}
				line = br.readLine();
				
				Document doc = new Document(title, annotation, sb.toString(), category, new Date(date));
				documents.add(doc);
				
				if (!categoryDocuments.containsKey(category)) {
					categoryDocuments.put(category, new HashMap<String, Document>());
				}
				
				categoryDocuments.get(category).put(title, doc);
				
				DatabaseLogger.info("Load document " + title + " from category " + category);
			}
 		} catch (FileNotFoundException e) {
			DatabaseLogger.error("Can't load new database. Not such file. Stuck to old version");
		} catch (IOException e) {
			DatabaseLogger.error("IO error");
		}
		
		for (String category : categoryDocuments.keySet()) {
			String annotation = "";
			int iteration = 0;
			HashMap<String, Document> tempMap = categoryDocuments.get(category);
			for (String title : tempMap.keySet()) {
				++iteration;
				annotation += "" + iteration + ": " + title;
				if (iteration == 3) break;
			}
			if (iteration == 0) annotation = "Empty category";
			
			Category cat = new Category(category, annotation, tempMap.size());
			categories.add(cat);
		}
	}
	
    public ArrayList<Document> getDocsByCategory(String category) {
    	if (categoryDocuments.containsKey(category)) {
    		return new ArrayList<Document>(categoryDocuments.get(category).values());
    	}
    	return new ArrayList<Document>();
    }
        
    public ArrayList<Category> getCategories() {
    	return categories;
    }
        
    public Document getDocument(String category, String title) {
    	if (categoryDocuments.containsKey(category)) {
    		HashMap<String, Document> docs = categoryDocuments.get(category);
    		if (docs.containsKey(title)) return docs.get(title);
    	}
    	return null;
    }
                
        
	// one word query - just for now
	public ArrayList<Document> proccessQuery(String query) {
		return documents;
	}

	private HashMap< String, HashMap<String, Document> > categoryDocuments;
	
	private String filename;
	private ArrayList<Document> documents;
	private ArrayList<Category> categories;
}