/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MastermindGame;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author ctg5117
 */
public class JPMainMenu extends javax.swing.JPanel {

    /**
     * Creates new form JPMainMenu
     */
    public JPMainMenu() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jbHistory = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jbConnect = new javax.swing.JButton();
        jbHost = new javax.swing.JButton();
        jbSinglePlayer = new javax.swing.JButton();

        jbHistory.setText("Game History");
        jbHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbHistoryActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setText("Password Game");

        jbConnect.setText("Connect To Game");
        jbConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbConnectActionPerformed(evt);
            }
        });

        jbHost.setText("Host Game");
        jbHost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbHostActionPerformed(evt);
            }
        });

        jbSinglePlayer.setText("Single Player");
        jbSinglePlayer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSinglePlayerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbHistory, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbConnect, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbHost, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 21, Short.MAX_VALUE))
                    .addComponent(jbSinglePlayer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbSinglePlayer)
                .addGap(20, 20, 20)
                .addComponent(jbConnect)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbHost)
                .addGap(26, 26, 26)
                .addComponent(jbHistory, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jbHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbHistoryActionPerformed
        JFrame frame = new GameHistory();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }//GEN-LAST:event_jbHistoryActionPerformed

    private void jbConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbConnectActionPerformed
        Client client = new Client();
        client.start();
        JFGameDisplay game = new JFGameDisplay(client);
        game.setVisible(true);
        SwingUtilities.windowForComponent(this).setVisible(false);
    }//GEN-LAST:event_jbConnectActionPerformed


    private void jbSinglePlayerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSinglePlayerActionPerformed
        // TODO add your handling code here:
        JFSinglePlayer jfSP = new JFSinglePlayer();
        jfSP.setVisible(true);
        SwingUtilities.windowForComponent(this).setVisible(false);
        
    }//GEN-LAST:event_jbSinglePlayerActionPerformed

    private void jbHostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbHostActionPerformed
        int intPort = requestServerPort();
        Thread server = new Thread(new Server(intPort));
        server.start();
        Client client = new Client(intPort);
        client.start();
        JFGameDisplay game = new JFGameDisplay(client);
        game.setVisible(true);
        SwingUtilities.windowForComponent(this).setVisible(false);
    }//GEN-LAST:event_jbHostActionPerformed

    private int requestServerPort(){
        JTextField jtfPort = new JTextField();
        JComponent[] inputs = new JComponent[]{
            new JLabel("Server Port"),
            jtfPort
        };
        int result = JOptionPane.showConfirmDialog(null, inputs, "Set Server Port", JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) 
        {
            int intPort = Integer.parseInt(jtfPort.getText());
            return intPort;
        }
        return 2000;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton jbConnect;
    private javax.swing.JButton jbHistory;
    private javax.swing.JButton jbHost;
    private javax.swing.JButton jbSinglePlayer;
    // End of variables declaration//GEN-END:variables
}
