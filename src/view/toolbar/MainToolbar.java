// Author: Nikolce Ambukovski
// Student Number: s2008618
// Date: 30-May-2014

package view.toolbar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JToolBar;

import model.interfaces.GameEngine;
import view.ClientWindow;
import controller.AddPlayerActionListener;
import controller.PlaceBetActionListener;
import controller.RemovePlayerActionListener;
import controller.SpinWheelActionListener;
import controller.SpinWheelParamActionListener;

@SuppressWarnings("serial")
public class MainToolbar extends JToolBar {
    
    // External References
    private ClientWindow clientWindow;
    private GameEngine gameEngine;

    // Child Component References
    private JButton addPlayerButton;
    private JButton removePlayerButton;
    private JButton placeBetButton;
    private JButton spinWheelButton;
    private JButton spinWheelParamButton;

    // Constructor
    public MainToolbar(ClientWindow clientWindow, GameEngine gameEngine) {
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
        this.setFloatable(false);
        this.setLayout(new FlowLayout(FlowLayout.CENTER));  
    }

    private void createChildComponents() {
        this.addPlayerButton = new JButton();
        this.removePlayerButton = new JButton();
        this.placeBetButton = new JButton();
        this.spinWheelButton = new JButton();
        this.spinWheelParamButton = new JButton();
    }

    private void setupChildComponents() {
        
        this.addPlayerButton.setText("Add Player");
        this.removePlayerButton.setText("Remove Player");
        this.placeBetButton.setText("Place Bet");
        this.spinWheelButton.setText("SpinWheel");
        this.spinWheelParamButton.setText("SpinWheel (Param)");
        
        Dimension buttonDimension = new Dimension(125, 50);
        this.addPlayerButton.setPreferredSize(buttonDimension);
        this.removePlayerButton.setPreferredSize(buttonDimension);
        this.placeBetButton.setPreferredSize(buttonDimension);
        this.spinWheelButton.setPreferredSize(buttonDimension);
        this.spinWheelParamButton.setPreferredSize(new Dimension(175, 50));
        
        this.addPlayerButton.setBorder(BorderFactory
            .createLineBorder(Color.GRAY));
        this.removePlayerButton.setBorder(BorderFactory
            .createLineBorder(Color.GRAY));
        this.placeBetButton.setBorder(BorderFactory
            .createLineBorder(Color.GRAY));
        this.spinWheelButton.setBorder(BorderFactory
            .createLineBorder(Color.GRAY));
        this.spinWheelParamButton.setBorder(BorderFactory
                .createLineBorder(Color.GRAY));
        
        this.addPlayerButton.addActionListener(
            new AddPlayerActionListener(clientWindow, gameEngine));
        this.removePlayerButton.addActionListener(
            new RemovePlayerActionListener(clientWindow, gameEngine));
        this.placeBetButton.addActionListener(
            new PlaceBetActionListener(clientWindow, gameEngine));
        this.spinWheelButton.addActionListener(
            new SpinWheelActionListener(gameEngine));
        this.spinWheelParamButton.addActionListener(
                new SpinWheelParamActionListener(clientWindow, gameEngine));
        
        this.removePlayerButton.setEnabled(false);
        this.placeBetButton.setEnabled(false);
        this.spinWheelButton.setEnabled(false);
        this.spinWheelParamButton.setEnabled(false);
        
    }

    private void addChildComponents() {
        this.add(addPlayerButton);
        this.add(removePlayerButton);
        this.add(placeBetButton);
        this.add(spinWheelButton);
        this.add(spinWheelParamButton);  
    }
    
    // MainToolbar Methods

    public void disableControlsDuringSpin() {
        this.addPlayerButton.setEnabled(false);
        this.removePlayerButton.setEnabled(false);
        this.placeBetButton.setEnabled(false);
        this.spinWheelButton.setEnabled(false);
        this.spinWheelParamButton.setEnabled(false);
    }

    public void enableControlsAfterSpin() {
        this.addPlayerButton.setEnabled(true);
        this.removePlayerButton.setEnabled(true);
        this.placeBetButton.setEnabled(true);
        this.spinWheelButton.setEnabled(true);
        this.spinWheelParamButton.setEnabled(true);
    }

    public void disableControlsWhenNoPlayers() {
        this.removePlayerButton.setEnabled(false);
        this.placeBetButton.setEnabled(false);
        this.spinWheelButton.setEnabled(false);
        this.spinWheelParamButton.setEnabled(false);
    }
    
    public void disableControlsWhenMaxPlayers() {
        this.addPlayerButton.setEnabled(false);
    }

    public void enableControlsWhenSomePlayers() {
        this.addPlayerButton.setEnabled(true);
        this.removePlayerButton.setEnabled(true);
        this.placeBetButton.setEnabled(true);
        this.spinWheelButton.setEnabled(true);
        this.spinWheelParamButton.setEnabled(true);
    }

    public void disableAllControls() {
        this.addPlayerButton.setEnabled(false);
        this.removePlayerButton.setEnabled(false);
        this.placeBetButton.setEnabled(false);
        this.spinWheelButton.setEnabled(false);
        this.spinWheelParamButton.setEnabled(false);
    }

}
