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
    private Phrase currPhrase;
    
    public Game()
    {
        
    }
    
    public void getPhrase(String strIn, int intPlayer) 
    {

    }
    
    /**Method to display correctness of a phrase.
     * 
     * @param intAIn 0: Match, 1: Close, 2: Wrong
     * @return String to be displayed of the correct letters
     */
    public String[] displayCorrect(int[] intAIn)
    {
        String[] strOutput = {intAIn[0] + " Correct, ", intAIn[1] + " Right Letter, ", intAIn[2] + " Wrong letter"};
        return strOutput;
    }
    
    public static void main(String[] args) 
    {
        int[] intArrTest = {1, 2, 3};
        Game myG = new Game();
        
        System.out.println(myG.displayCorrect(intArrTest));
    }
    
}
