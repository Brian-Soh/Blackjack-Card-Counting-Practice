package ui.decisions;

import ui.BlackJackApp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents a load game decision
public class LoadGame extends Decision {

    public LoadGame(BlackJackApp blackjack, JComponent parent) {
        super(blackjack, parent);
    }

    // MODIFIES: this
    // EFFECTS: constructs a load game button which is then added to the JComponent
    // (parent)
    // which is passed in as a parameter
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Load Game");
        addToParent(parent);
    }

    // MODIFIES: this
    // EFFECTS: constructs a new listener object which is added to the JButton
    @Override
    protected void addListener() {
        button.addActionListener(new LoadGameClickHandler());
    }

    private class LoadGameClickHandler implements ActionListener {

        // EFFECTS: loads game from file when clicked
        @Override
        public void actionPerformed(ActionEvent e) {
            blackjack.loadGame();
        }
    }
}