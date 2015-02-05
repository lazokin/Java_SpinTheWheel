// Author: Nikolce Ambukovski
// Student Number: s2008618
// Date: 30-May-2014

package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.WheelCallbackImpl;
import model.interfaces.GameEngine;

public class SpinWheelActionListener implements ActionListener {

    private GameEngine gameEngine;

    public SpinWheelActionListener(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        gameEngine.spin(40, 1, 300, 30, new WheelCallbackImpl());

    }

}
