
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MastermindGame;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author ctg5117
 */
public class Game 
{
    private int intTurnCount = 0;
    private int intGameLength = 10; 
    private Phrase gamePhrase;
    private Phrase currPhrase;
    private boolean bWon = false;
    private boolean bLose = false;
    
    
    public Game()
    {
        setGamePhrase(initializeFilePhrase());
    }
    
    public Game(Phrase phrase){
        gamePhrase = phrase;
        
    }
    
    /**Check a phrase to make sure it is correct.
     * 
     * @param charInPhraseGuess
     * @param charInPhrase
     * @return Int array, (# exactly right, #close, #wrong) 
     */
    public int[] check(char charInPhraseGuess[],char charInPhrase[])
    {
        int intArrSize = 3;
        int[] aIntResults = new int[intArrSize];
        if(getIntGameLength() > getIntTurnCount())
        {
            
            char charPhrase[] = charInPhrase;
            char charPhraseGuess[] = charInPhraseGuess;
            int length = charPhrase.length;

            for (int i = 0; i < length; i++) 
            {
                if(charPhrase[i] == charPhraseGuess[i])
                {
                    aIntResults[0]++;
                }
                else
                { 
                    for (int j = 0; j < length; j++) 
                    {
                        if(charPhrase[i] == charPhraseGuess[j])
                        {
                            aIntResults[1]++;
                            break;                    
                        }
                    }
                }
            }
            aIntResults[2] = ((5-aIntResults[0])-aIntResults[1]);

            setIntTurnCount(getIntTurnCount() + 1);
            if(getIntTurnCount() > getIntGameLength())
            {
                setbLose(false);
            }
        }
        return aIntResults;    
    }
    
    public void initializePhrase(char[] caIn) 
    {
        setGamePhrase(new Phrase(caIn));
    }
    
    public String[] retrievePhrase(Phrase phrase)
    {
        setCurrPhrase(phrase);
        
        return sendCorrect(check(getCurrPhrase().getPhrase(), getGamePhrase().getPhrase()));
    }
     
    
    /**Create a phrase from a file
     * adapted from https://stackoverflow.com/questions/12028205/randomly-choose-a-word-from-a-text-file
     * 
     * @return phrase from file 
     */
    public static Phrase initializeFilePhrase()
    {
        String outPhrase = " ";
             
        try
        {
            System.out.println("in try");
            
            //Chris @ home testing: C:\\Users\\geiss\\OneDrive\\Documents\\NetBeansProjects\\IST411FinalProject
            //Testing @ school: X:\\My Documents\\NetBeansProjects\\IST411FinalProject\\Phrases.txt
            
            BufferedReader r = new BufferedReader(new FileReader("C:\\Users\\geiss\\OneDrive\\Documents\\NetBeansProjects\\IST411FinalProject\\Phrases.txt"));
            String in = r.readLine();
            ArrayList<String> words = new ArrayList<>();
            while(in != null)
            {
                String[] wordsLine = in.split(" ");
                for(String word : wordsLine)
                {
                    words.add(word);
                }
                in = r.readLine();
            }
            Random rand = new Random();
            String phrase = words.get(rand.nextInt(words.size()));
            outPhrase = phrase;
        }
        catch (Exception e)
        {

        }                            
        
        Phrase myP = new Phrase(outPhrase.toUpperCase().toCharArray());
        return myP;
    }
    
    /**Method to display correctness of a phrase.
     * 
     * @param intAIn 0: Match, 1: Close, 2: Wrong
     * @return String to be displayed of the correct letters
     */
    public String[] sendCorrect(int[] intAIn)
    {
        String[] strOutput = {intAIn[0] + " Right Letter, Right Place", intAIn[1] + " Right Letter, Wrong Place", intAIn[2] + " Wrong letter"};
        
        if(intAIn[0] == 5)
        {
            setbWon(true);
            return new String[] {"You guessed the phrase in " + intTurnCount + " turns!"};
        }

        return strOutput;
    }
    
    public static void main(String[] args) 
    {
        int[] intArrTest = {1, 2, 3};
        Game myG = new Game();
        
        
    }

    /**
     * @return the intTurnCount
     */
    public int getIntTurnCount() {
        return intTurnCount;
    }

    /**
     * @param intTurnCount the intTurnCount to set
     */
    public void setIntTurnCount(int intTurnCount) {
        this.intTurnCount = intTurnCount;
    }

    /**
     * @return the intGameLength
     */
    public int getIntGameLength() {
        return intGameLength;
    }

    /**
     * @param intGameLength the intGameLength to set
     */
    public void setIntGameLength(int intGameLength) {
        this.intGameLength = intGameLength;
    }

    /**
     * @return the gamePhrase
     */
    public Phrase getGamePhrase() {
        return gamePhrase;
    }

    /**
     * @param gamePhrase the gamePhrase to set
     */
    public void setGamePhrase(Phrase gamePhrase) {
        this.gamePhrase = gamePhrase;
    }

    /**
     * @return the currPhrase
     */
    public Phrase getCurrPhrase() {
        return currPhrase;
    }

    /**
     * @param currPhrase the currPhrase to set
     */
    public void setCurrPhrase(Phrase currPhrase) {
        this.currPhrase = currPhrase;
    }

    /**
     * @return the bWon
     */
    public boolean isbWon() {
        return bWon;
    }

    /**
     * @param bWon the bWon to set
     */
    public void setbWon(boolean bWon) {
        this.bWon = bWon;
    }

    /**
     * @return the bLose
     */
    public boolean isbLose() {
        return bLose;
    }

    /**
     * @param bLose the bLose to set
     */
    public void setbLose(boolean bLose) {
        this.bLose = bLose;
    }
    
    
}



