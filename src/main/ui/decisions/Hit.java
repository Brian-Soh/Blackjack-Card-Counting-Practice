package ui.decisions;

import ui.BlackJackApp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents a hit decision
public class Hit extends Decision {

    public Hit(BlackJackApp blackjack, JComponent parent) {
        super(blackjack, parent);
    }

    // MODIFIES: this
    // EFFECTS: constructs a hit button which is then added to the JComponent
    // (parent)
    // which is passed in as a parameter
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Hit");
        addToParent(parent);
    }

    // MODIFIES: this
    // EFFECTS: constructs a new listener object which is added to the JButton
    @Override
    protected void addListener() {
        button.addActionListener(new HitClickHandler());
    }

    private class HitClickHandler implements ActionListener {

        // EFFECTS: player hits when clicked
        @Override
        public void actionPerformed(ActionEvent e) {
            blackjack.hit();
        }
    }
}