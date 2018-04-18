package MastermindGame;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Component;
import java.awt.GridLayout;

public class PhraseJOP extends JOptionPane{
    
    public PhraseJOP(){
        String messages[] = {"Char1", "Char2", "Char3", "Char4", "Char5"};
        showInputDialog(null, messages);    
    }
    
    public static String[] showInputDialog(Component parentComponent, String messages[]){
        JTextField tFields[] = new JTextField[messages.length];
        JPanel panel = new JPanel();
        String input[] = new String[messages.length];
        panel.setLayout(new GridLayout(messages.length, 2, 0, 0));
        for(int i = 0 ; i < messages.length ; i++){
            panel.add(new JLabel(messages[i]));
            tFields[i] = new JTextField();
            panel.add(tFields[i]);
        }
        JOptionPane.showConfirmDialog(parentComponent, panel, "Input", JOptionPane.OK_CANCEL_OPTION);
        for(int i = 0 ; i < messages.length ; i++)
           input[i] = tFields[i].getText();
       return input;
    }
}