/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MastermindGame;

import java.io.Serializable;

/**
 *
 * @author ctg5117
 */
public class Phrase implements Serializable
{
    
   private int intSize = 5; 
   private char[] caPhrase = new char[intSize];
   
   /**Defaults intSize to 5.
    * 
    */
   Phrase(char[] caIn)
   {
       intSize = 5;
       
       caPhrase = caIn;
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
   
   @Override
   public String toString(){
       StringBuilder strPhrase = new StringBuilder();
       for (char c : caPhrase){
            strPhrase.append(c);
        }
       return strPhrase.toString();
   }
   
}
