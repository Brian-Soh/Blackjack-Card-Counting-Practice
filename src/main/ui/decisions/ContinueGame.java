package ui.decisions;

import ui.BlackJackApp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents a continue game decision
public class ContinueGame extends Decision {

    public ContinueGame(BlackJackApp blackjack, JComponent parent) {
        super(blackjack, parent);
    }

    // MODIFIES: this
    // EFFECTS: constructs a continue button which is then added to the JComponent
    // (parent)
    // which is passed in as a parameter
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Continue Game");
        addToParent(parent);
    }

    // MODIFIES: this
    // EFFECTS: constructs a new listener object which is added to the JButton
    @Override
    protected void addListener() {
        button.addActionListener(new ContinueClickHandler());
    }

    private class ContinueClickHandler implements ActionListener {

        // EFFECTS: provides the player the option to
        // continue the game when clicked
        @Override
        public void actionPerformed(ActionEvent e) {
            blackjack.clearCurrentHand();
        }
    }
}