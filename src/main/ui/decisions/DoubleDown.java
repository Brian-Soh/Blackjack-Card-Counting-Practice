package ui.decisions;

import ui.BlackJackApp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents a double down decision
public class DoubleDown extends Decision {

    public DoubleDown(BlackJackApp blackjack, JComponent parent) {
        super(blackjack, parent);
    }

    // MODIFIES: this
    // EFFECTS: constructs a double down button which is then added to the JComponent
    // (parent)
    // which is passed in as a parameter
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Double Down");
        addToParent(parent);
    }

    // MODIFIES: this
    // EFFECTS: constructs a new listener object which is added to the JButton
    @Override
    protected void addListener() {
        button.addActionListener(new DoubleDownClickHandler());
    }

    private class DoubleDownClickHandler implements ActionListener {

        // EFFECTS: player doubles down when clicked
        @Override
        public void actionPerformed(ActionEvent e) {
            blackjack.doubleDown();
        }
    }
}