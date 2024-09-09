package ui.decisions;

import ui.BlackJackApp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents a new game decision
public class NewGame extends Decision {

    public NewGame(BlackJackApp blackjack, JComponent parent) {
        super(blackjack, parent);
    }

    // MODIFIES: this
    // EFFECTS: constructs a new game button which is then added to the JComponent
    // (parent)
    // which is passed in as a parameter
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("New Game");
        addToParent(parent);
    }

    // MODIFIES: this
    // EFFECTS: constructs a new listener object which is added to the JButton
    @Override
    protected void addListener() {
        button.addActionListener(new NewGameClickHandler());
    }

    private class NewGameClickHandler implements ActionListener {

        // EFFECTS: initializes the creation of a new player when clicked
        @Override
        public void actionPerformed(ActionEvent e) {
            blackjack.createNewPlayer();
        }
    }
}