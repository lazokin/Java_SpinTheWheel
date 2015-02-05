// Author: Nikolce Ambukovski
// Student Number: s2008618
// Date: 30-May-2014

package application;

import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager.LookAndFeelInfo;

import model.GameEngineImpl;
import model.interfaces.GameEngine;
import view.ServerWindow;
import application.server.GameEngineServerStub;

public class SpinTheWheelServer {

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException
            | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        GameEngine ge = new GameEngineImpl();
        GameEngineServerStub gess = new GameEngineServerStub(4444, ge);

        final ServerWindow serverWindow = new ServerWindow();
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                public void run() {
                    serverWindow.createGUI(); 
                }
            });
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        gess.registerServerStubListener(serverWindow);
        gess.start();
    }
}