import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUI implements ActionListener{
	
	private static JFrame frame;
	private JButton restartButton, checkButton, addWordsButton;
	private JLabel ticker;
	private JPanel panel;
	
    // word by definition frames and ..
	private JButton learn; // button to choose which option, learn or flashcards or ...
	private JButton addButton;
	private JButton readyButton;
	private JFrame addFrame;
    private JPanel addPanel;
    private JTextField enterNewWord;
    private JTextField enterNewDefinition;
    
    //spelling check
    private JLabel displayDefinition;
    private JTextField enterWord;
    private JFrame checkScreen;
    private JPanel checkPanel;
    private JButton resumeButton;
    
    //flashcards
    private JButton flashcards; // button to choose which option, learn or flashcards or ...
    private JButton flip;
    private JButton nextFlashcard;
    boolean definitionIsShowing; // boolean shows true if definition side of flashcard is what's showing
    
    //To check the word list
    private JButton viewWords;
    //private JLabel list;
    
    static Word currentWord;
	
	public GUI() {
		currentWord = Library.nextWord();
		Library.setIntroduced();
		
	  // display options to choose
		frame = new JFrame();
		frame.setSize(650, 650);
		
		panel = new JPanel();
	    panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
	    panel.setLayout(new GridLayout(0, 1));
		
		//Button to view word set
		viewWords = new JButton("Learning Set");
		viewWords.addActionListener(this);
		frame.add(viewWords, BorderLayout.NORTH);
		
		//adds button to choose to do learn 
		learn = new JButton("Learn");
		learn.addActionListener(this);
		panel.add(learn, BorderLayout.CENTER);
		
		//adds button to choose to do flashcards
		flashcards = new JButton("Flashcards");
		flashcards.addActionListener(this);
		panel.add(flashcards, BorderLayout.SOUTH);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel.setVisible(true);
		
		frame.add(panel);
		frame.setVisible(true);
	}
	
	public void wordByDefinition() {  //Test on getting the right word by giving the user the definition
		
		frame = new JFrame();
		
		addWordsButton = new JButton("Add New Words");
		addWordsButton.addActionListener(this);
		
		restartButton = new JButton("Restart");
		restartButton.addActionListener(this);
		
		checkButton = new JButton("Check");
		checkButton.addActionListener(this);
		
		ticker = new JLabel("Words Mastered: " + Library.mastered + "  Words Learning: " + Library.learning + "  Unfamiliar Words: " + Library.notIntroduced);
		
		panel = new JPanel();
	    panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
	    panel.setLayout(new GridLayout(0, 1));
	    
	    //To display current word
	    try {
	    displayDefinition = new JLabel(currentWord.getDefinition());
	    
	    } catch (Exception e)
	    {    //  If no words are in word bank
	    	frame.dispose();
	    	this.addWords();
	    }
	    
	    enterWord = new JTextField("");
	    
	    panel.add(addWordsButton);
	    panel.add(restartButton);
	    panel.add(ticker);
	    panel.add(displayDefinition);
	    panel.add(enterWord);
	    panel.add(checkButton);
	    
	    frame.add(panel, BorderLayout.CENTER);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setTitle("Learn");
	    frame.pack();
	    frame.setVisible(true);
	    
	}
	
	public void flashcards() {
		
		frame = new JFrame();
		frame.setSize(600, 600);
		frame.setTitle("Flashcards");
		
		flip = new JButton(currentWord.getWrd());  //gets current word spelling
		definitionIsShowing = false;
		
		frame.add(flip);
		flip.addActionListener(this);
		
		addNextFlashCardButton();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public void learningSet() {
		frame = new JFrame();
		frame.setSize(550, 550);
		
		
	}
	
	public static void main (String[] args) throws IOException {
            
			Library l = new Library();	
		    new GUI(); 

		    
	}
	
	public static void startRound() {
		frame.dispose();
		
		frame = new JFrame();
		
		
	}

	
	public void addWords() {
		frame.dispose();
		
		
		addFrame = new JFrame();
		
		addPanel = new JPanel();
		addPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
		addPanel.setLayout(new GridLayout(0, 1));
		
		addButton = new JButton("Enter");
		addButton.addActionListener(this);
		
		readyButton = new JButton("Ready");
		readyButton.addActionListener(this);
		
		enterNewWord = new JTextField("Enter New Word");
		enterNewDefinition = new JTextField("Enter New Definition");
		addPanel.add(enterNewWord);
		addPanel.add(enterNewDefinition);
	    addPanel.add(readyButton);
	    addPanel.add(addButton);
	    addFrame.add(addPanel, BorderLayout.CENTER);
	    addFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		addFrame.pack();
		addFrame.setVisible(true);
	}


    public void check() {
    	frame.dispose();
		checkScreen = new JFrame();
		checkPanel = new JPanel();
			
		checkPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
		checkPanel.setLayout(new GridLayout(0, 1));
			
		resumeButton = new JButton("Resume");
		resumeButton.addActionListener(this);
			
			if (currentWord.checkSpelling(enterWord.getText()))
			{
				checkPanel.add(new JLabel("Corect!"));
				currentWord.setRightAnswers(currentWord.getRightAnswers() + 1);
			} else {
				checkPanel.add(new JLabel("Not Quite"));
				currentWord.setRightAnswers(currentWord.getRightAnswers() - 1);
			}
			checkPanel.add(new JLabel(currentWord.getWrd()));
			checkPanel.add(new JLabel(currentWord.getDefinition()));
			
	    checkScreen.add(checkPanel, BorderLayout.CENTER);
	    checkScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
		checkPanel.add(resumeButton);
	    checkScreen.pack();
		checkScreen.setVisible(true);
    }

    // Function to add the next flashcard button
    public void addNextFlashCardButton() {
    	nextFlashcard = new JButton("Next Flashcard");
    	nextFlashcard.addActionListener(this);
		frame.add(nextFlashcard, BorderLayout.SOUTH);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object listen = e.getSource();
		
		if (listen == learn)
		{
			wordByDefinition();
		}
		if(listen == restartButton) {
			
		}
		if(listen == checkButton) {
			check();
			
		}
		if(listen == addButton) {
			try {
				Word w = new Word(enterNewWord.getText(), enterNewDefinition.getText());
				System.out.println(w.getWrd() + " added to word bank successfully");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			addFrame.dispose();
			addWords();
		}
		if (listen == readyButton) { 
			addFrame.dispose();
			new GUI();
			
		}
		if (listen == addWordsButton) {
			addWords();
			
		}
		if (listen == resumeButton) {
			checkScreen.dispose();
			wordByDefinition();
		}
		
		// Flashcards
		if (listen == flashcards)
		{
			flashcards();
		}
		if (listen == flip) {
			if (definitionIsShowing == false)
			{
			    frame.remove(flip);
			    flip = new JButton(currentWord.getDefinition());
			    frame.add(flip);
			    flip.addActionListener(this);
			    
			    frame.remove(nextFlashcard);
			    addNextFlashCardButton();

			    frame.setVisible(true);
			    definitionIsShowing = true;    
			    
			}
			else
			{
				//Clicking on the word will cause the flashcard to flip
				frame.remove(flip);
				flip = new JButton(currentWord.getWrd());
				flip.addActionListener(this);
				frame.add(flip);
				
				definitionIsShowing = false;
				
				frame.remove(nextFlashcard);
				//Next Flashcard button to move to the next word
				addNextFlashCardButton();
				
				frame.setVisible(true);
			}
		}
		
		if (listen == nextFlashcard) {
			currentWord = Library.nextWord(); //changes current word to another random word in library
			flashcards(); // calls flashcards function again, this time using the new current word
		}
		
		// View Words set
		if (listen == viewWords)
		{
			
		}
	}
	
}
