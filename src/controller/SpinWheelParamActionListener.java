// Author: Nikolce Ambukovski
// Student Number: s2008618
// Date: 30-May-2014

package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.WheelCallbackImpl;
import model.interfaces.GameEngine;
import view.ClientWindow;

import com.lazokin.util.SwingInput;

public class SpinWheelParamActionListener implements ActionListener {

    private ClientWindow clientWindow;
    private GameEngine gameEngine;

    public SpinWheelParamActionListener(ClientWindow clientWindow,
            GameEngine gameEngine) {
        this.clientWindow = clientWindow;
        this.gameEngine = gameEngine;
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        String[] input = SwingInput.showTextDialog(clientWindow, "Spin Wheel",
                200, new String[] { "Wheel Size", "Initial Delay",
                    "Final Delay", "Delay Increment" }, new String[] {
                        "40", "1", "300", "30" });

        if (input != null) {
            int wheelSize = Integer.parseInt(input[0]);
            int initialDelay = Integer.parseInt(input[1]);
            int finalDelay = Integer.parseInt(input[2]);
            int delayIncrement = Integer.parseInt(input[3]);

            gameEngine.spin(wheelSize, initialDelay, finalDelay,
                    delayIncrement, new WheelCallbackImpl());
        }

    }

}
