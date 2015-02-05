// Author: Nikolce Ambukovski
// Student Number: s2008618
// Date: 30-May-2014

package view;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import model.interfaces.Player;
import application.server.ServerStubListener;

@SuppressWarnings("serial")
public class ServerWindow extends JFrame implements ServerStubListener {

    // Child Component References
    private JTextArea textArea;
    private JScrollPane scrollPane;

    // Constants
    private final int WIDTH = 400;
    private final int HEIGHT = 300;

    // Constructor
    public ServerWindow() {
        super();
    }

    // Construction Methods

    public void createGUI() {
        this.setupThisComponent();
        this.createChildComponents();
        this.setupChildComponents();
        this.addChildComponents();
    }

    private void setupThisComponent() {
        this.setTitle("Spin The Wheel Server");
        this.setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }

    private void createChildComponents() {
        this.textArea = new JTextArea();
        this.scrollPane = new JScrollPane(textArea);
    }

    private void setupChildComponents() {
        this.textArea.setEditable(false);
        this.scrollPane.setViewportBorder(null);
    }

    private void addChildComponents() {
        this.setContentPane(scrollPane);

    }

    // ServerWindow Methods

    private void updateTextArea(String status) {

        SwingUtilities.invokeLater(new Runnable() {

            private ServerWindow serverWindow;
            private String status;

            @Override
            public void run() {
                serverWindow.textArea.append(status);
            }

            public Runnable init(ServerWindow serverWindow, String status) {
                this.serverWindow = serverWindow;
                this.status = status;
                return this;
            }

        }.init(this, status));

    }

    // ServerStubListener Interface Methods

    @Override
    public void notifyServerStarted() {
        String status = " -> Server started\n";
        this.updateTextArea(status);
    }

    @Override
    public void notifyClientConnected() {
        String status = " -> Client connected\n";
        this.updateTextArea(status);
    }

    @Override
    public void notifyPlayerAdded(Player player) {
        String name = player.getPlayerName();
        int points = player.getPoints();
        String status = " -> Player " + name + " joined with " + points
                + " points\n";
        this.updateTextArea(status);
    }

    @Override
    public void notifyClientDisconnected() {
        String status = " -> Client disconnected\n";
        this.updateTextArea(status);
    }

    @Override
    public void notifyPlayerRemoved(Player player) {
        String name = player.getPlayerName();
        int points = player.getPoints();
        String status = " -> Player " + name + " left with " + points
                + " points\n";
        this.updateTextArea(status);
    }

    @Override
    public void notifyBetPlaced(Player player) {
        String name = player.getPlayerName();
        int number = player.getNumberPick();
        int bet = player.getBet();
        String status = " -> Player " + name + " placed a bet of " + bet
                + " on number " + number + "\n";
        this.updateTextArea(status);
    }

    @Override
    public void notifyWheelSpinning() {
        String status = " -> Wheel spinning ...\n";
        this.updateTextArea(status);
    }

    @Override
    public void notifySpinComplete(int result) {
        String status = " -> Wheel stopeed on number  " + result + "\n";
        this.updateTextArea(status);
    }

}
