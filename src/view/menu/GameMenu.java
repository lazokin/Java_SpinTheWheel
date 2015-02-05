// Author: Nikolce Ambukovski
// Student Number: s2008618
// Date: 30-May-2014

package view.menu;

import java.awt.Insets;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import model.interfaces.GameEngine;
import controller.ExitActionListener;

@SuppressWarnings("serial")
public class GameMenu extends JMenu {
    
    // External References
    private GameEngine gameEngine;

    // Child Component References
    private JMenuItem exitMenuItem;

    // Constructor
    public GameMenu(GameEngine gameEngine) {
        super();
        this.processParameters(gameEngine);
        this.setupThisComponent();
        this.createChildComponents();
        this.setupChildComponents();
        this.addChildComponents();   
    }

    // Construction Methods
    
    private void processParameters(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    private void setupThisComponent() {
        this.setText("Game");
        this.setMargin(new Insets(4, 8, 4, 4));
    }

    private void createChildComponents() {
        this.exitMenuItem = new JMenuItem();   
    }

    private void setupChildComponents() {
        this.exitMenuItem.setText("Exit Game");  
        this.exitMenuItem.addActionListener(new ExitActionListener(gameEngine));
    }

    private void addChildComponents() {
        this.add(exitMenuItem);   
    }
    
    // ExitMenu Methods

    public void disableControlsDuringSpin() {
        this.exitMenuItem.setEnabled(false);
    }

    public void enableControlsAfterSpin() {
        this.exitMenuItem.setEnabled(true);
    }

}
