package ui.decisions;

import ui.BlackJackApp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents a place bet decision
public class PlaceBet extends Decision {

    public PlaceBet(BlackJackApp blackjack, JComponent parent) {
        super(blackjack, parent);
    }

	// MODIFIES: this
	// EFFECTS: constructs a place bet button which is then added to the JComponent
	// (parent)
	// which is passed in as a parameter
    @Override
	protected void createButton(JComponent parent) {
        button = new JButton("Place Bet");
        addToParent(parent);
    }

	// MODIFIES: this
	// EFFECTS: constructs a new listener object which is added to the JButton
    @Override
    protected void addListener() {
        button.addActionListener(new PlaceBetClickHandler());
    }

    private class PlaceBetClickHandler implements ActionListener {

		// EFFECTS: places bet when tool is clicked
        @Override
		public void actionPerformed(ActionEvent e) {
            blackjack.placeBet();
        }
    }
}