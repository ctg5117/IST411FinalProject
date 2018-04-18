package MastermindGame;

import java.awt.EventQueue;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.JTextField;


//Adapted from https://stackoverflow.com/questions/17617390/how-to-set-manage-the-layout-of-joptionpane

public class PhraseJOP{
    
    char[] phrase;
       
    public char [] getPhrase()
    {
        phrase = new char[5];
        JTextField jtfChar1 = new JTextField(1);
        JTextField jtfChar2 = new JTextField(1);
        JTextField jtfChar3 = new JTextField(1);
        JTextField jtfChar4 = new JTextField(1);
        JTextField jtfChar5 = new JTextField(1);
//        final JComponent[] inputs = new JComponent[] 
//        {
 //          new JLabel("Phrase"),
//           jtfChar1,
//           jtfChar2,
//           jtfChar3,
//           jtfChar4,
//           jtfChar5
//        };
//        JOptionPane phraseJOP = new JOptionPane();
//        GridLayout myGL = new GridLayout(1, 5);
//        phraseJOP.setLayout(myGL);
//        phraseJOP.add(jtfChar1);
//        phraseJOP.add(jtfChar2);
//        phraseJOP.add(jtfChar3);
//        phraseJOP.add(jtfChar4);
//        phraseJOP.add(jtfChar5);
        
//        int result = phraseJOP.getMessageType();
        
//        JFrame frame = new JFrame();
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setLocationRelativeTo( null );
//        frame.setVisible( true );

        //  Build a custom panel

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
        
        
        //int result = JOptionPane.showConfirmDialog(null, PhraseInputPanel, "Phrase", JOptionPane.PLAIN_MESSAGE);
        //if (result == JOptionPane.OK_OPTION) 
//        {
//            phrase[0] = jtfChar1.getText().charAt(0);
//            phrase[1] = jtfChar2.getText().charAt(0);
//            phrase[2] = jtfChar3.getText().charAt(0);
//            phrase[3] = jtfChar4.getText().charAt(0);
//            phrase[4] = jtfChar5.getText().charAt(0);
//        } 
       // else 
        //{
       //     System.out.println("User canceled / closed the dialog, result = " + result);
       // }
 //       return phrase;
 // getCredentials
    
    
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                PhraseJOP myJOP = new PhraseJOP();
                System.out.println(myJOP.getPhrase());
            }
        });
    }
}