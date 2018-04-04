/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MastermindGame;

/**
 *
 * @author ctg5117
 */
public class Phrase 
{
    
   private int intSize; 
   private char[] caPhrase = new char[intSize];
   
   /**Defaults intSize to 5.
    * 
    */
   Phrase()
   {
       intSize = 5;
   }
   
   
   
   /*
   Getter and Setter Block
   */
   public void setPhrase(char[] caInPhrase)
   {
       caPhrase = caInPhrase; 
   }
   
   public char[] getPhrase()
   {
       return caPhrase;
   }
   
   public void setSize(int intInSize)
   {
       intSize = intInSize;
   }
   
   public int getSize()
   {
       return intSize;
   }
   
}
