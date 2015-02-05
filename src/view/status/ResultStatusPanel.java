// Author: Nikolce Ambukovski
// Student Number: s2008618
// Date: 30-May-2014

package view.status;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Collection;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import model.interfaces.Player;

@SuppressWarnings("serial")
public class ResultStatusPanel extends JPanel {

    // Child Component References
    private JTextArea resultsStatusTextArea;
    private JScrollPane resultsStatusScrollPane;

    // Constructor
    public ResultStatusPanel() {
        super();
        this.setupThisComponent();
        this.createChildComponents();
        this.setupChildComponents();
        this.addChildComponents();    
    }
    
    // Construction Methods

    private void setupThisComponent() {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(300, 1));
        this.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));  
    }

    private void createChildComponents() {
        this.resultsStatusTextArea = new JTextArea();
        this.resultsStatusScrollPane = new JScrollPane(resultsStatusTextArea);  
    }

    private void setupChildComponents() {
        this.resultsStatusTextArea.setEditable(false);
        this.resultsStatusScrollPane.setViewportBorder(null);
    }

    private void addChildComponents() {
        this.add(resultsStatusScrollPane);   
    }
    
    // ResultsStatusPanel Methods

    public void printResults(Collection<Player> allPlayers,
        Collection<Player> spinPlayers, int result, int wheelSize) {
        
        String status = " Wheel is size " + wheelSize + "\n";
        status += " Wheel stopped on " + result + "\n";

        if (allPlayers.isEmpty()) {
            status += " No players this spin" + "\n";
        } else if (spinPlayers.isEmpty()) {
            status += " All players excluded from spin" + "\n";
        } else {
            for (Player player : spinPlayers) {
                if (player.getNumberPick() == result) {
                    status += " " + player.getPlayerName() + " won "
                        + player.getBet() + "\n";
                } else {
                    status += " " + player.getPlayerName() + " lost "
                        + player.getBet() + "\n";
                }
            }
        }
        status += "\n";
        this.updateRestultsStatusTextArea(status);
    }

    private void updateRestultsStatusTextArea(String status) {
        this.resultsStatusTextArea.append(status);
        this.resultsStatusScrollPane.getVerticalScrollBar().setValue(100);
    }

}
