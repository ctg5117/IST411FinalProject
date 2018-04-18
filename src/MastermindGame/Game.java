/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MastermindGame;

import java.util.ArrayList;

/**
 *
 * @author ctg5117
 */
public class Game 
{
    private int intTurnCount = 0;
    private Phrase gamePhrase;
    private Phrase currPhrase;
    
    
    public Game()
    {
        
    }
    
    public Game(Phrase phrase){
        gamePhrase = phrase;
    }
    
    public int[] check(char charInPhraseGuess[],char charInPhrase[]){
        int[] results = new int[3];
        char charPhrase[] = charInPhrase;
        char charPhraseGuess[] = charInPhraseGuess;
        int length = charPhrase.length;
        
        for (int i = 0; i < length; i++) 
        {
            if(charPhrase[i] == charPhraseGuess[i])
            {
                results[0]++;
            }
            else
            { 
                for (int j = 0; j < length; j++) 
                {
                    if(charPhrase[i] == charPhraseGuess[j])
                    {
                        results[1]++;
                        break;                    
                    }
                }
            }
        }
        results[2] = ((5-results[0])-results[1]);
        return results;    
    }
    
    public void initializePhrase(char[] caIn) 
    {
        gamePhrase.setPhrase(caIn);
    }
    
    public String[] retrievePhrase(Phrase phrase)
    {
        currPhrase = phrase;
        
        return sendCorrect(check(currPhrase.getPhrase(), gamePhrase.getPhrase()));
    }
           
         
    
    
    /**Method to display correctness of a phrase.
     * 
     * @param intAIn 0: Match, 1: Close, 2: Wrong
     * @return String to be displayed of the correct letters
     */
    public String[] sendCorrect(int[] intAIn)
    {
        String[] strOutput = {intAIn[0] + " Correct, ", intAIn[1] + " Right Letter, ", intAIn[2] + " Wrong letter"};
        return strOutput;
    }
    
    public static void main(String[] args) 
    {
        int[] intArrTest = {1, 2, 3};
        Game myG = new Game();
        
        System.out.println(myG.sendCorrect(intArrTest));
    }
    
}


