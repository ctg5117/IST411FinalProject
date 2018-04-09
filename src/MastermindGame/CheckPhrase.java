/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MastermindGame;

/**
 *
 * @author kds5314
 */
public class CheckPhrase {
    
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


    public static void main(String[] args) {
        CheckPhrase CP = new CheckPhrase();
        char charPhrase[] = new char[]{'a','b','c','d','e'};
        char charPhraseGuess[] = new char[]{'a','d','d','c','z'};;
        int[] results = CP.check(charPhraseGuess, charPhrase);
        String result = "Match" + results[0] + " Close:" + results[1] + " Wrong:" + results[2];
        System.out.println(result);
    }
}
