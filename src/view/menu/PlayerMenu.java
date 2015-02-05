// Author: Nikolce Ambukovski
// Student Number: s2008618
// Date: 30-May-2014

package view.menu;

import java.awt.Insets;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import model.interfaces.GameEngine;
import view.ClientWindow;
import controller.AddPlayerActionListener;
import controller.RemovePlayerActionListener;

@SuppressWarnings("serial")
public class PlayerMenu extends JMenu {

    // External References
    private ClientWindow clientWindow;
    private GameEngine gameEngine;

    // Child Component References
    private JMenuItem addPlayerMenuItem;
    private JMenuItem removePlayerMenuItem;

    // Constructor
    public PlayerMenu(ClientWindow clientWindow, GameEngine gameEngine) {
        super();
        this.processParameters(clientWindow, gameEngine);
        this.setupThisComponent();
        this.createChildComponents();
        this.setupChildComponents();
        this.addChildComponents();
    }

    // Construction Methods
    
    private void processParameters(ClientWindow clientWindow, GameEngine gameEngine) {
        this.clientWindow = clientWindow;
        this.gameEngine = gameEngine;
    }

    private void setupThisComponent() {
        this.setText("Player");
        this.setMargin(new Insets(4, 4, 4, 4));
    }

    private void createChildComponents() {
        this.addPlayerMenuItem = new JMenuItem();
        this.removePlayerMenuItem = new JMenuItem();
    }

    private void setupChildComponents() {
        this.addPlayerMenuItem.setText("Add Player");
        this.removePlayerMenuItem.setText("Remove Player");
        this.addPlayerMenuItem.addActionListener(
            new AddPlayerActionListener(clientWindow, gameEngine));
        this.removePlayerMenuItem.addActionListener(
            new RemovePlayerActionListener(clientWindow, gameEngine));
        this.removePlayerMenuItem.setEnabled(false);
    }

    private void addChildComponents() {
        this.add(addPlayerMenuItem);
        this.add(removePlayerMenuItem);
    }
    
    // PlayerMenu Methods

    public void disableControlsDuringSpin() {
        this.addPlayerMenuItem.setEnabled(false);
        this.removePlayerMenuItem.setEnabled(false);
    }

    public void enableControlsAfterSpin() {
        this.addPlayerMenuItem.setEnabled(true);
        this.removePlayerMenuItem.setEnabled(true);
    }

    public void disableControlsWhenNoPlayers() {
        this.removePlayerMenuItem.setEnabled(false);
    }
    
    public void disableControlsWhenMaxPlayers() {
        this.addPlayerMenuItem.setEnabled(false); 
    }

    public void enableControlsWhenSomePlayers() {
        this.addPlayerMenuItem.setEnabled(true);
        this.removePlayerMenuItem.setEnabled(true);
    }

    public void disableAllControls() {
        this.addPlayerMenuItem.setEnabled(false);
        this.removePlayerMenuItem.setEnabled(false);
    }

}
