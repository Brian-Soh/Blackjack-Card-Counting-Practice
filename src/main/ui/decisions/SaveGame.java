package ui.decisions;

import ui.BlackJackApp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents a save game decision
public class SaveGame extends Decision {

    public SaveGame(BlackJackApp blackjack, JComponent parent) {
        super(blackjack, parent);
    }

	// MODIFIES: this
	// EFFECTS: constructs a save button which is then added to the JComponent
	// (parent)
	// which is passed in as a parameter
    @Override
	protected void createButton(JComponent parent) {
        button = new JButton("Save Game");
        addToParent(parent);
    }

	// MODIFIES: this
	// EFFECTS: constructs a new listener object which is added to the JButton
    @Override
    protected void addListener() {
        button.addActionListener(new SaveClickHandler());
    }

    private class SaveClickHandler implements ActionListener {

		// EFFECTS: saves game in its current state
        @Override
		public void actionPerformed(ActionEvent e) {
            blackjack.saveGame();
        }
    }
}