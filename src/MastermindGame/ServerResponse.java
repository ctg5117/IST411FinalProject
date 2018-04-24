package MastermindGame;

import java.io.Serializable;

public class ServerResponse implements Serializable {
	
	private String[] message;
	private Phrase phrase;
	
	public void setMessage(String[] message) {
		this.message = message;
	}
	
	public void setPhrase(Phrase phrase) {
		this.phrase = phrase;
	}
	
	public boolean containsMessage() {
		return (message != null);
	}
	
	public boolean containsPhrase() {
		return (phrase != null);
	}
	
	public String[] getMessage() {
		return message;
	}
	
	public Phrase getPhrase() {
		return phrase;
	}
	
}
