package ts3000.model;

public class Document {
	public String getTitle();
	public String getAnnotation();
	public String getText();
}

public abstract class Database {
	public void initDatabase(String filename) {
		// Some cool code here
	}
	
	// one word query - just for now
	public Document[] proccessQuery(String query) {
		
	}
}
