package ts3000.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

// 1. Read it.
// 2. ???
// 3. Profit.
// Once again, sorry. Meaningless, yeah. To master

public class Database {
	
	private static Logger DatabaseLogger = Logger.getLogger(Database.class);
	
	public static void main(String ... args) {
		PropertyConfigurator.configureAndWatch("log4j.properties");
		Database db = new Database("input.txt");

		try {
			db.proccessQuery("");
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Database(String filename) {
		initDatabase(filename);
	}
	
	public void initDatabase(String filename) {
		this.filename = filename;
	}
	
	// one word query - just for now
	public Document[] proccessQuery(String query) throws SAXException, IOException, ParserConfigurationException {
		
		
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setValidating(true);
        factory.setNamespaceAware(false);
        SAXParser parser = factory.newSAXParser();
        try {
        	parser.parse(new File(filename), new SAXHandler()); 
        } catch (FileNotFoundException e) {
        	DatabaseLogger.error("File " + filename + " not found!");
        	throw e;
        }
        return null;
	}

	private String filename;

}

class SAXHandler extends DefaultHandler {
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes){
		System.out.println("\n");
		System.out.println("Uri: " + uri);
		System.out.println("Local name: " + localName);
		System.out.println("qName: " + qName);
		System.out.println("Attributes: " + attributes);
		System.out.println("\n");
	}
	
	@Override
	public void characters(char[] ch, int start, int length) {
		System.out.println("");
		for (int i = start; i < start + length; ++i) {
			System.out.print(ch[i]);
		}
		System.out.println("");
	}

}