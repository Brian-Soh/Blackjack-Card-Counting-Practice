package ui.decisions;

import ui.BlackJackApp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents a stand decision
public class Stand extends Decision {

    public Stand(BlackJackApp blackjack, JComponent parent) {
        super(blackjack, parent);
    }

	// MODIFIES: this
	// EFFECTS: constructs a stand button which is then added to the JComponent
	// (parent)
	// which is passed in as a parameter
    @Override
	protected void createButton(JComponent parent) {
        button = new JButton("Stand");
        addToParent(parent);
    }

	// MODIFIES: this
	// EFFECTS: constructs a new listener object which is added to the JButton
    @Override
    protected void addListener() {
        button.addActionListener(new StandClickHandler());
    }

    private class StandClickHandler implements ActionListener {

		// EFFECTS: finishes player's turn
        @Override
		public void actionPerformed(ActionEvent e) {
            blackjack.stand();
        }
    }
}