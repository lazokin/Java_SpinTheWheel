// Author: Nikolce Ambukovski
// Student Number: s2008618
// Date: 30-May-2014

package view.menu;

import java.awt.Insets;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import model.interfaces.GameEngine;
import view.ClientWindow;
import controller.PlaceBetActionListener;
import controller.SpinWheelActionListener;
import controller.SpinWheelParamActionListener;

@SuppressWarnings("serial")
public class PlayMenu extends JMenu {

    // External References
    private ClientWindow clientWindow;
    private GameEngine gameEngine;

    // Child Component References
    private JMenuItem placeBetMenuItem;
    private JMenuItem spinWheelMenuItem;
    private JMenuItem spinWheelParamMenuItem;

    // Constructor
    public PlayMenu(ClientWindow clientWindow, GameEngine gameEngine) {
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
        this.setText("Play");
        this.setMargin(new Insets(4, 4, 4, 4));
    }

    private void createChildComponents() {
        this.placeBetMenuItem = new JMenuItem();
        this.spinWheelMenuItem = new JMenuItem();
        this.spinWheelParamMenuItem = new JMenuItem();
    }

    private void setupChildComponents() {
        this.placeBetMenuItem.setText("Place Bet");
        this.spinWheelMenuItem.setText("Spin Wheel");
        this.spinWheelParamMenuItem.setText("Spin Wheel (Param)");
        this.placeBetMenuItem.addActionListener(
                new PlaceBetActionListener(clientWindow, gameEngine));
        this.spinWheelMenuItem.addActionListener(
                new SpinWheelActionListener(gameEngine));
        this.spinWheelParamMenuItem.addActionListener(
                new SpinWheelParamActionListener(clientWindow, gameEngine));
        this.placeBetMenuItem.setEnabled(false);
        this.spinWheelMenuItem.setEnabled(false);
        this.spinWheelParamMenuItem.setEnabled(false);
    }

    private void addChildComponents() {
        this.add(placeBetMenuItem);
        this.add(spinWheelMenuItem);
        this.add(spinWheelParamMenuItem);
    }
    
    // PlayMenu Methods

    public void disableControlsDuringSpin() {
        this.placeBetMenuItem.setEnabled(false);
        this.spinWheelMenuItem.setEnabled(false);
        this.spinWheelParamMenuItem.setEnabled(false);
    }

    public void enableControlsAfterSpin() {
        this.placeBetMenuItem.setEnabled(true);
        this.spinWheelMenuItem.setEnabled(true);
        this.spinWheelParamMenuItem.setEnabled(true);
    }

    public void disableControlsWhenNoPlayers() {
        this.placeBetMenuItem.setEnabled(false);
        this.spinWheelMenuItem.setEnabled(false);
        this.spinWheelParamMenuItem.setEnabled(false);
    }

    public void enableControlsWhenSomePlayers() {
        this.placeBetMenuItem.setEnabled(true);
        this.spinWheelMenuItem.setEnabled(true);
        this.spinWheelParamMenuItem.setEnabled(true);
    }

    public void disableAllControls() {
        this.placeBetMenuItem.setEnabled(false);
        this.spinWheelMenuItem.setEnabled(false);
        this.spinWheelParamMenuItem.setEnabled(false);
    }

}
