
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MastermindGame;

import java.util.Random;
import java.util.Scanner;

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
    
    
    public Game()
    {
        
    }
    
    public Game(Phrase phrase){
        gamePhrase = phrase;
    }
    
    public int[] check(char charInPhraseGuess[],char charInPhrase[])
    {
        int intArrSize = 3;
        int[] aIntResults = new int[]{-1,-1,-1};
        if(getIntGameLength() < getIntTurnCount())
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
        }
        else
        {
            for(int i = 0; i < intArrSize; i++)
            {
                aIntResults[i] = 1;
            }
        }
        return aIntResults;    
    }
    
    public void initializePhrase(char[] caIn) 
    {
        getGamePhrase().setPhrase(caIn);
    }
    
    public String[] retrievePhrase(Phrase phrase)
    {
        setCurrPhrase(phrase);
        
        return sendCorrect(check(getCurrPhrase().getPhrase(), getGamePhrase().getPhrase()));
    }
           
    public static Phrase intializeFilePhrase()
    {
        String[] filePhrase = new String[500];
        int counter = 0;
        char[] p = new char[5];
             
        Scanner sc = new Scanner("X:\\My Documents\\NetBeansProjects\\IST411FinalProject\\Phrases.txt"); // list retrieved from thefreedictionary.com
        
        while(sc.hasNextLine()){
            filePhrase[counter] = sc.nextLine();
            counter++;
        }
        
        Random rand = new Random();
        int randomNumber = rand.nextInt(filePhrase.length);
        String temp = filePhrase[randomNumber];
        for(int x = 0; x < 5; x++){
            p[x] = temp.charAt(x);
        }
        
        return new Phrase(p);
    }
    

     
    
    
    /**Method to display correctness of a phrase.
     * 
     * @param intAIn 0: Match, 1: Close, 2: Wrong
     * @return String to be displayed of the correct letters
     */
    public String[] sendCorrect(int[] intAIn)
    {
        String[] strOutput = {intAIn[0] + " Correct, ", intAIn[1] + " Right Letter, ", intAIn[2] + " Wrong letter"};
        int[] intALose = new int[]{-1,-1,-1};
        
        if(intAIn[0] == 5)
        {
            setbWon(true);
            return new String[] {"You've won!"};
        }

        return strOutput;
    }
    
    public static void main(String[] args) 
    {
        int[] intArrTest = {1, 2, 3};
        Game myG = new Game();
        
        System.out.println(myG.sendCorrect(intArrTest));
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
    
}



