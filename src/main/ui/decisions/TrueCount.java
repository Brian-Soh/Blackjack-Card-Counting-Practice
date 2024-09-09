package ui.decisions;

import ui.BlackJackApp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents a check true count decision
public class TrueCount extends Decision {

    public TrueCount(BlackJackApp blackjack, JComponent parent) {
        super(blackjack, parent);
    }

	// MODIFIES: this
	// EFFECTS: constructs a true count button which is then added to the JComponent
	// (parent)
	// which is passed in as a parameter
    @Override
	protected void createButton(JComponent parent) {
        button = new JButton("Check True Count");
        addToParent(parent);
    }

	// MODIFIES: this
	// EFFECTS: constructs a new listener object which is added to the JButton
    @Override
    protected void addListener() {
        button.addActionListener(new TrueCountClickHandler());
    }

    private class TrueCountClickHandler implements ActionListener {

		// EFFECTS: displays the true count when clicked
        @Override
		public void actionPerformed(ActionEvent e) {
            blackjack.checkTrueCount();
        }
    }
}