// Author: Nikolce Ambukovski
// Student Number: s2008618
// Date: 30-May-2014

package view.menu;

import javax.swing.JMenuBar;

import model.interfaces.GameEngine;
import view.ClientWindow;

@SuppressWarnings("serial")
public class MainMenuBar extends JMenuBar {

    // External References
    private ClientWindow clientWindow;
    private GameEngine gameEngine;

    // Child Component References
    private GameMenu gameMenu;
    private PlayerMenu playerMenu;
    private PlayMenu playMenu;

    // Constructor
    public MainMenuBar(ClientWindow clientWindow, GameEngine gameEngine) {
        super();
        this.processParameters(clientWindow, gameEngine);
        this.createChildComponents();
        this.addChildComponents();
    }

    // Construction Methods

    private void processParameters(ClientWindow clientWindow, GameEngine gameEngine) {
        this.clientWindow = clientWindow;
        this.gameEngine = gameEngine;
    }

    private void createChildComponents() {
        this.gameMenu = new GameMenu(gameEngine);
        this.playerMenu = new PlayerMenu(clientWindow, gameEngine);
        this.playMenu = new PlayMenu(clientWindow, gameEngine);
    }

    private void addChildComponents() {
        this.add(gameMenu);
        this.add(playerMenu);
        this.add(playMenu);
    }

    // MainMenuBar Methods

    public void disableControlsDuringSpin() {
        this.gameMenu.disableControlsDuringSpin();
        this.playerMenu.disableControlsDuringSpin();
        this.playMenu.disableControlsDuringSpin();
    }

    public void enableControlsAfterSpin() {
        this.gameMenu.enableControlsAfterSpin();
        this.playerMenu.enableControlsAfterSpin();
        this.playMenu.enableControlsAfterSpin();

    }

    public void disableControlsWhenNoPlayers() {
        this.playerMenu.disableControlsWhenNoPlayers();
        this.playMenu.disableControlsWhenNoPlayers();
    }
    
    public void disableControlsWhenMaxPlayers() {
        this.playerMenu.disableControlsWhenMaxPlayers();
    }

    public void enableControlsWhenSomePlayers() {
        this.playerMenu.enableControlsWhenSomePlayers();
        this.playMenu.enableControlsWhenSomePlayers();
    }

    public void disableAllControls() {
        this.playerMenu.disableAllControls();
        this.playMenu.disableAllControls();
    }

}
