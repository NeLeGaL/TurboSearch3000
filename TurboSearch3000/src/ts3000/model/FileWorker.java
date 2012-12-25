package ts3000.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

class FileWorker extends Thread {
	private static Logger DatabaseLogger = Logger.getLogger(Database.class);
	private IndexatorAndFinder indexatorAndFinder;
	private String filename;
	private long lastModified;
	
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
	
	public FileWorker(IndexatorAndFinder indexatorAndFinder, String filename) {
		//BasicConfigurator.configure();
		this.indexatorAndFinder = indexatorAndFinder;
		this.filename = filename;
		//lastModified = new File(filename).lastModified();
	}
	
	@Override
	public void run() {
		DatabaseLogger.info("Check thread started");
		DatabaseLogger.info("Filename: " + filename);
		while (true) {
		try {
			Thread.sleep(5000);
			File file = new File(filename);
			if (file.lastModified() != this.lastModified) {
				this.lastModified = file.lastModified();
				DatabaseLogger.info("File was changed, let's reindex it");
				loadFile();
			}
			else  {
				DatabaseLogger.info("The same database file.");				
			}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	ArrayList<Document> getListOfDocuments(HashSet<Integer> documentNumbers) {
		ArrayList<Document> result = new ArrayList<Document>();
		
		for (int number : documentNumbers) {
			result.add(documents.get(number));
		}
		
		return result;
	}
	
	void loadFile() {
		categoryDocuments = new HashMap<String, HashMap<String,Document>>();
		documents = new ArrayList<Document>();
		categories = new ArrayList<Category>();
		
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF-8"));
			
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
			
			br.close();
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
		
		DatabaseLogger.info("Read all documents");
		indexatorAndFinder.indexIt(documents);
		DatabaseLogger.info("Indexation finished");
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
	
	private HashMap< String, HashMap<String, Document> > categoryDocuments;
	
	private ArrayList<Document> documents;
	private ArrayList<Category> categories;
}
