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
	
	private JFrame frame;
	private JButton restartButton, checkButton, addWordsButton;
	private JLabel ticker;
	private JPanel panel;
	

	private JFrame addFrame;
	private JButton addButton;
	private JButton readyButton;
    private JPanel addPanel;
    private JTextField enterNewWord;
    private JTextField enterNewDefinition;
    
    //spelling check
    private JLabel displayDefinition;
    private JTextField enterWord;
    private JFrame checkScreen;
    private JPanel checkPanel;
    private JButton resumeButton;
    
    static Word currentWord;
	
	public GUI() {
		currentWord = Library.nextWord();
		Library.setIntroduced();
		
		frame = new JFrame();
		
		addWordsButton = new JButton("Add New Words");
		addWordsButton.addActionListener(this);
		
		restartButton = new JButton("Restart");
		restartButton.addActionListener(this);
		
		checkButton = new JButton("Check");
		checkButton.addActionListener(this);
		
		ticker = new JLabel("Words Mastered: " + Library.mastered + "  Words Learning: " + Library.learning + "  Words Not Introduced Yet: " + Library.notIntroduced);
		
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
	
	
	public static void main (String[] args) throws IOException {
            
			Library l = new Library();	
		    new GUI(); 

		    
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


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object listen = e.getSource();
		
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
			new GUI();
		}
	}
	
}