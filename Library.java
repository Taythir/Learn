

import java.io.File;
import java.io.FileNotFoundException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.Set;

//in file, 0 means not introduced, 1 means learning, 2 means mastered

public class Library {
	
    static HashMap<Integer, Word> wordBank = new HashMap<Integer, Word>();
    static int counter = 0, notIntroduced, learning = 0, mastered = 0;
    static boolean extraSpace = true;
    static public Library l;
    
    public Library() throws IOException {
    	File f = new File("library.txt");
    	
    	int i;
    	String s, d;
    	
    	if (f.createNewFile()) {
    		System.out.println("Created new library file");
    	}
    	Scanner sc = new Scanner(f);
    	//to read from t=file to load existing word bank
    	try {
    	    extraSpace = true;
    	    while (sc.hasNextLine()) {
    		    i = sc.nextInt();
    		    s = sc.next();
    		    d = sc.nextLine();
    		    Word w = new Word(s, d, i);
    	    } 
    	} catch  (Exception e){
    		if (wordBank.containsKey(0) == false) {
    		    Word p = new Word("Placeholder", "A person or thing that occupies the position or place of another thing");
    	        counter++;
    		}
    	}
    		
    		sc.close();
    }
    
    public static void addWord(Word n) throws IOException {
    	FileWriter writer = new FileWriter("library.txt");
    	wordBank.put(counter, n);
    	counter++;
    	
    	int howFamiliar = 0;
    	
    	for (Word i : wordBank.values()) {
    		
    		if (i.isMastered()) {
    			howFamiliar = 4;
    		} else if (i.isLearning()) {
    			howFamiliar = 1;
    		} 
    		
    		if (extraSpace) {
    			writer.write(howFamiliar + " " + i.getWrd() + " " + i.getDefinition() + "\n");
    		} else {
    		    writer.write(howFamiliar + " " + i.getWrd() + i.getDefinition() + "\n");
    		}
    	}
    	extraSpace = false;
    	
    	
    	writer.close();
    }
    
    
    public static Word nextWord() {
    	//Word x = wordBank.get(0);
    	
    	Set<Integer> keySet = wordBank.keySet();
    	List<Integer> keyList = new ArrayList<>(keySet);
    	
    	int size = keyList.size();
    	int rand = new Random().nextInt(size);
    	
    	int randomKey = keyList.get(rand);
    	Word randomWord = wordBank.get(randomKey);
    	/*
    	for (Word i : wordBank.values()) {
    		if (i.getRightAnswers() < x.getRightAnswers())
    			x = i;
    	}
    	*/
    	return randomWord; 
    }
    
    public static void setIntroduced() {
    	int ni = 0, l = 0, m = 0, temp;
    	for (Word i : wordBank.values()) {
    		temp = i.getRightAnswers();
    		if(temp <= 0) {
    		    ni++;
    		} else if(temp < 4) {
    			l++;
    		} else {
    			m++;
    		}
    		
    	}
    	notIntroduced = ni;
    	learning = l;
    	mastered = m;
    }
}
