// Author: Nikolce Ambukovski
// Student Number: s2008618
// Date: 30-May-2014

package view.wheel;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class WheelPanel extends JPanel {

    // Child Component References
    private JLabel wheelNumberLabel;

    // Constructor
    public WheelPanel() {
        super();
        this.setupThisComponent();
        this.createChildComponents();
        this.setupChildComponents();
        this.addChildComponents();   
    }

    // Construction Methods
    
    private void setupThisComponent() {
        this.setLayout(new GridBagLayout());
        this.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 1, Color.BLACK)); 
    }

    private void createChildComponents() {
        this.wheelNumberLabel = new JLabel();
    }

    private void setupChildComponents() {
        this.wheelNumberLabel.setText("0");
        this.wheelNumberLabel.setFont(new Font("Monospace", Font.PLAIN, 100)); 
    }

    private void addChildComponents() {
        this.add(wheelNumberLabel); 
    }

    // WheelPanel Methods
    
    public void updateNuber(int number) {
        this.wheelNumberLabel.setText(String.valueOf(number));
    }

}
