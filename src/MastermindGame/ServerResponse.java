package MastermindGame;

import java.io.Serializable;

public class ServerResponse implements Serializable {
	
	private String[] message;
	private Phrase phrase;
        private int intTurnCount;
	
	public void setMessage(String[] message) {
		this.message = message;
	}
	
	public void setPhrase(Phrase phrase) {
		this.phrase = phrase;
	}
        
        public void setTurnCount(int count){
            intTurnCount = count;
        }
	
	public boolean containsMessage() {
		return (message != null);
	}
	
	public boolean containsPhrase() {
		return (phrase != null);
	}
        
        public boolean containsTurnCount(){
            return (intTurnCount != 0);
        }
	
	public String[] getMessage() {
		return message;
	}
	
	public Phrase getPhrase() {
		return phrase;
	}
        
        public int getTurnCount(){
            return intTurnCount;
        }
	
}
