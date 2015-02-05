// Author: Nikolce Ambukovski
// Student Number: s2008618
// Date: 30-May-2014

package application;

import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import model.interfaces.GameEngine;
import view.ClientWindow;
import application.client.GameEngineClientStub;

public class SpinTheWheelClient {

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException
            | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        GameEngineClientStub gameEngineClientStub = new GameEngineClientStub(4444);
        GameEngine gameEngine = gameEngineClientStub;
        final ClientWindow cw = new ClientWindow(gameEngine);
        
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                public void run() {
                    cw.createGUI();
                }
            });
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        gameEngineClientStub.registerClientStubListener(cw);
        gameEngineClientStub.start();

    }
}