package app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class RecentFilesHelper {
	private ArrayList<String> files;
	private final static String PATH = "RecentFiles.txt";
	private final static int RECORDS_COUNT = 20;
	
	public RecentFilesHelper() {
		files = new ArrayList<String>();
	}
	
	public boolean loadRecentFilesList() {
            files = new ArrayList<String>();
            try {
                    String[] content = readFile();
                    int savedRecordsCount = Integer.parseInt(content[0]);
                    for (int i = 1; i < savedRecordsCount + 1; i++) {
                            files.add(content[i]);
                    }
            } catch (IOException e) {
                    return false;
            }

            return true;
	}
	
	public void addNewRecentFile(String newFile) {
            files.add(0, newFile);
            if (files.size() > RECORDS_COUNT) {
                files.remove(files.size() - 1);
            }
	}
	
	public boolean saveRecentFilesList() {
		try {
			writeFile();
		} catch (IOException e) {
			return false;
		}
		return true;
	}
	
	public ArrayList<String> getRecentFilesList() {
		return files;
	}
	
	private String[] readFile() throws IOException {
		File file = new File(PATH);
		FileReader fr = new FileReader(file);
		BufferedReader reader = new BufferedReader(fr);
		
		try {			
			String[] text = new String[RECORDS_COUNT + 1];
			String line;
			
			for (int i = 0; i < RECORDS_COUNT + 1; i++) {
				if ((line = reader.readLine()) != null) {
					text[i] = line;
				}
				else {
					break;
				}
			}		
			return text;
			
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
	}
	
	private void writeFile() throws IOException {
		StringBuilder text = new StringBuilder();
		text.append(files.size());
                text.append("\n");
		for (int i = 0; i < files.size(); i++) {
			text.append(files.get(i));
                        text.append("\n");
		}
		File file = new File(PATH);
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		try {
			writer.write(text.toString());
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}
}
