package ui.decisions;

import ui.BlackJackApp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents a check balance decision
public class CheckBalance extends Decision {

    public CheckBalance(BlackJackApp blackjack, JComponent parent) {
        super(blackjack, parent);
    }

    // MODIFIES: this
    // EFFECTS: constructs a check balance button which is then added to the JComponent
    // (parent)
    // which is passed in as a parameter
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Check Balance");
        addToParent(parent);
    }

    // MODIFIES: this
    // EFFECTS: constructs a new listener object which is added to the JButton
    @Override
    protected void addListener() {
        button.addActionListener(new CheckBalanceClickHandler());
    }

    private class CheckBalanceClickHandler implements ActionListener {

        // EFFECTS: displays player's balance when clicked
        @Override
        public void actionPerformed(ActionEvent e) {
            blackjack.checkBalance();
        }
    }
}