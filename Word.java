import java.io.IOException;

public class Word {
	private String wrd, definition;
	private boolean mastered;
	private boolean learning;
	private boolean introduced;
	
	private int rightAnswers;
	
	public Word(String w, String d) throws IOException {
		this.setWrd(w);
		this.setDefinition(" " + d);
		this.setMastered(false);
		this.setLearning(false);
		this.setIntroduced(false);
		
		this.setRightAnswers(0);
		
		Library.addWord(this);
	}
	
	public Word(String w, String d, int i) throws IOException {
		this.setWrd(w);
		this.setDefinition(d);
		this.setMastered(false);
		this.setLearning(false);
		this.setIntroduced(false);
		
		switch (i) {
		case 0:
			this.setRightAnswers(0);
			Library.notIntroduced++;
			break;
		case 1:
			this.setIntroduced(true);
			this.setRightAnswers(1);
			Library.learning++;
			break;
		case 2:
			this.setMastered(true);
			this.setRightAnswers(4);
			Library.mastered++;
			break;
		default:
			break;
		}
		
		
		
		Library.addWord(this);
	}
	
	public boolean isMastered() {
		return mastered;
	}

	public void setMastered(boolean mastered) {
		this.mastered = mastered;
	}

	public boolean isLearning() {
		return learning;
	}

	public void setLearning(boolean learning) {
		this.learning = learning;
	}

	public boolean isIntroduced() {
		return introduced;
	}

	public void setIntroduced(boolean introduced) {
		this.introduced = introduced;
	}


	public String getWrd() {
		return wrd;
	}


	public void setWrd(String wrd) {
		this.wrd = wrd;
	}
	
	
	
	public boolean checkSpelling(String input) {
		if (this.getWrd().equalsIgnoreCase(input)) {
			return true;
		}
		else
			return false;
							
	}

	public String getDefinition() {
		return definition;
	}

	public void setDefinition(String definition) {
		this.definition = definition;
	}

	public int getRightAnswers() {
		return rightAnswers;
	}

	public void setRightAnswers(int rightAnswers) {
		this.rightAnswers = rightAnswers;
	}
	
	

}
