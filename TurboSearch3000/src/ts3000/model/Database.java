package ts3000.model;

// 1. Read it.
// 2. ???
// 3. Profit.
// Sorry, just want to check, if git works fine. Stas.
// One more meaningfull comment sorry.

public class Document {
	public String getTitle();
	public String getAnnotation();
	public String getText();
}

public abstract class Database {
	public void initDatabase(String filename) {
		// Some cool code here
        // Really some cool code
	}
	
	// one word query - just for now
	public Document[] proccessQuery(String query) {
		
	}
}
