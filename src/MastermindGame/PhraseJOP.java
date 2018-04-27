package MastermindGame;

import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.JTextField;


//Adapted from https://stackoverflow.com/questions/17617390/how-to-set-manage-the-layout-of-joptionpane

public class PhraseJOP{
    
       
    public static char[] getPhrase()
    {
        char[] phrase = new char[5];
        JTextField jtfChar1 = new JTextField(1);
        JTextField jtfChar2 = new JTextField(1);
        JTextField jtfChar3 = new JTextField(1);
        JTextField jtfChar4 = new JTextField(1);
        JTextField jtfChar5 = new JTextField(1);

        JPanel panel = new JPanel( new GridLayout(1, 5) );
        panel.add(jtfChar1);
        panel.add(jtfChar2);
        panel.add(jtfChar3);
        panel.add(jtfChar4);
        panel.add(jtfChar5);

        int result = JOptionPane.showConfirmDialog(null,panel,"Please Enter Your Phrase",JOptionPane.OK_CANCEL_OPTION);

        if(result == JOptionPane.OK_OPTION)
        {
            phrase[0] = jtfChar1.getText().toUpperCase().charAt(0);
            phrase[1] = jtfChar2.getText().toUpperCase().charAt(0);
            phrase[2] = jtfChar3.getText().toUpperCase().charAt(0);
            phrase[3] = jtfChar4.getText().toUpperCase().charAt(0);
            phrase[4] = jtfChar5.getText().toUpperCase().charAt(0);
        }
        else
        {
            System.out.println("Canceled");
        }

        return phrase;
    }
}

        

