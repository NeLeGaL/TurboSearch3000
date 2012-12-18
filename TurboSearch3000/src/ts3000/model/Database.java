package ts3000.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Database {
	
	private static Logger DatabaseLogger = Logger.getLogger(Database.class);
	private static String DELIMETERS = ".,! 	:;$#&";
	
	
	public static void main(String ... args) {
		PropertyConfigurator.configureAndWatch("log4j.properties");
		Database db = new Database("input.txt");
		
	}
	
	public Database(String filename) {
		initDatabase(filename);
	}
	
	public void initDatabase(String filename) {
		this.filename = filename;
	}
	
	public Document[] proccessQuery(String query) throws SAXException, IOException, ParserConfigurationException {
		StringTokenizer tokenizer = new StringTokenizer(query.toLowerCase(), DELIMETERS);
		

        return null;
	}

	private String filename;

}

public class Indexator {
	
}
