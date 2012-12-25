package ts3000.model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.concurrent.Semaphore;
import java.util.logging.Logger;



public class RegexIndexFindStrategy implements IndexFindStrategy {
	class Rule {
		public Rule(String ending, int cutLength, String newEnding) {
			this.ending = ending;
			this.cutLength = cutLength;
			if (newEnding.equals("<empty>")) this.newEnding = "";
			else this.newEnding = newEnding;
		}
		
		public boolean canApply(String word) {
			return word.endsWith(ending);
		}
		
		public String apply(String word) {
			return word.substring(0, word.length() - cutLength) + newEnding;
		}
		
		String ending, newEnding;
		int cutLength;
	}
	
	private ArrayList<Rule> rules = new ArrayList<Rule>();
	private HashSet<String> vocabulary = new HashSet<String>();
	
	@SuppressWarnings("resource")
	public RegexIndexFindStrategy() {
		BufferedReader br;
		BufferedReader vocBr;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream("rules.txt"), "UTF-8"));
			vocBr = new BufferedReader(new InputStreamReader(new FileInputStream("voc.txt"), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			Logger.getAnonymousLogger().severe("UTF-8 is not supported?! Go home, local JVM, you're drunk.");
			return;
		} catch (FileNotFoundException e) {
			Logger.getAnonymousLogger().severe("Can't find files with rules. Will use exact index strategy instead");
			return;
		}
		
		String line;
		try {
			while ((line = br.readLine()) != null) {
				String[] tokens = line.split("\t");
				if (tokens.length != 3) {
					continue;
				}
				
				rules.add(new Rule(tokens[0], Integer.parseInt(tokens[1]), tokens[2]));
			}
			
			while ((line = vocBr.readLine()) != null) {
				String word = line.trim();
				if (word.length() > 0) vocabulary.add(word);
			}
		} catch (IOException e1) {
			Logger.getAnonymousLogger().severe("IO-exception while reading rules. I just reject to work in such conditions");
		}
		
		try {
			br.close();
		} catch (IOException e) {
			Logger.getAnonymousLogger().severe("Oh my God. Why, just why you throw IO-exception while closing file opened for reading?");
		}
	}
	
	@Override
	public Map<String, HashSet<Integer>> indexIt(List<Document> listOfDocuments) {
		Map<String, HashSet<Integer>> newGrams = new HashMap<String, HashSet<Integer>>();
		for (int i = 0; i < listOfDocuments.size(); ++i) {
			Document d = (Document) (listOfDocuments.get(i));
			StringBuilder sb = new StringBuilder(d.getPlainText());
			sb.append(" ");
			sb.append(d.getAnnotation());
			sb.append(" ");
			sb.append(d.getTitle());
			sb.append(" ");
			sb.append(d.getCategory());
			indexOneDocument(listOfDocuments.get(i).getPlainText() , i, newGrams);
		}
		
		return newGrams;
	}

	@Override
	public HashSet<Integer> find(String query,
		Map<String, HashSet<Integer>> grams, Semaphore synchSemaphore) {
		StringTokenizer tokenizer = new StringTokenizer(query, IndexatorAndFinder.DELIMETERS);
		if (!tokenizer.hasMoreTokens()) return new HashSet<Integer>();
		
		try {
			synchSemaphore.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		boolean wasWord = false;
		
		HashSet<Integer> ans = new HashSet<Integer>();
		while (tokenizer.hasMoreTokens()) {
			String word = tokenizer.nextToken();
			if (word.length() < 3) continue;
			
			HashSet<Integer> newGrams = processOneQueryWord(word, grams);
			if (wasWord) {
				ans.retainAll(processOneQueryWord(word, grams));
			} else {
				ans = newGrams;
				wasWord = true;
			}
		}
		synchSemaphore.release();
		
		return ans;
	}
	
	private HashSet<Integer> processOneQueryWord(String word, Map<String, HashSet<Integer>> grams) {
		HashSet<Integer> ans = new HashSet<Integer>();
		
		HashSet<String> forms = tryNormalizeWord(word);
		
		for (String form : forms) {
			HashSet<Integer> addGrams = grams.get(form);
			if (addGrams != null) {
				ans.addAll(addGrams);
			}
		}
		return ans;
	}
	
	private void indexOneDocument(String document, int documentNumber, Map<String, HashSet<Integer>> grams) {
		StringTokenizer tokenizer = new StringTokenizer(document, IndexatorAndFinder.DELIMETERS);
		while(tokenizer.hasMoreTokens()) {
			String word = tokenizer.nextToken();
			
			HashSet<String> forms = tryNormalizeWord(word);
			
			for (String form : forms) {
				HashSet<Integer> cur = grams.get(form);
				if (cur != null) {
					cur.add(documentNumber);
				} else {
					HashSet<Integer> newHashSet = new HashSet<Integer>();
					newHashSet.add(documentNumber);
					grams.put(form, newHashSet);
				}
			}
		}
	}
	
	final int MAX_LEVEL = 6;
	final int MAX_FORMS = 15;
	
	private HashSet<String> tryNormalizeWord(String word) {
		HashSet<String> result = new HashSet<String>();
		word = word.toLowerCase().replace('ё', 'е').trim();
		
		result.add(word);
		
		for (int level = 0; level < MAX_LEVEL && result.size() <= MAX_FORMS; ++level) {
			HashSet<String> secondLevel = new HashSet<String>();
			for (String form : result) {
				for (Rule rule : rules) {
					if (rule.canApply(form)) {
						String newForm = rule.apply(form);
						secondLevel.add(newForm);
					}
				}
			}
			result.addAll(secondLevel);
		}
		
		if (result.size() > 0) {
			HashSet<String> approvedGrams = new HashSet<String>();
			for (String gram : result) 
				if (vocabulary.contains(gram)) {
					approvedGrams.add(gram);
				}
			if (approvedGrams.size() > 0) result = approvedGrams;
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		RegexIndexFindStrategy a = (new RegexIndexFindStrategy());
		
		ArrayList<String> tests = new ArrayList<String>();
		tests.add("кузнеца");
		tests.add("плечом");
		tests.add("студентом");
		tests.add("самосвал");
		tests.add("обороноспособностью");
		tests.add("магией");
		tests.add("покоя");
		tests.add("первого");
		tests.add("второму");
		tests.add("первым");
		tests.add("третьего");
		
		tests.add("Воспитывающий");
		tests.add("увлекательнейший");
		tests.add("печататься");
		tests.add("закручивающегося");
		tests.add("бесподобнейший");
		
		tests.add("Спалось");
		tests.add("золотому");
		tests.add("снесло");
		tests.add("надоевшему");
		tests.add("огнями");
		tests.add("полочкой");
		tests.add("упало");
		tests.add("диванами");
		tests.add("рамки");
		tests.add("шоколадке");
		tests.add("пробелу");
		
		
		tests.add("забавному");
		tests.add("везения");
		tests.add("узнаваемому");
		tests.add("сожалению");
		tests.add("прикусив");
		tests.add("оглушающими");
		tests.add("страданиями");
		tests.add("стремясь");
		tests.add("возвышаемыми");
		tests.add("воробья");
		
		for (String test : tests) {
			System.out.println(test);
			for (String s : a.tryNormalizeWord(test)) {
				System.out.println("\t" + s);
			}
		}
	}

}
