package ui.decisions;

import ui.BlackJackApp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents a check running count decision
public class RunningCount extends Decision {

    public RunningCount(BlackJackApp blackjack, JComponent parent) {
        super(blackjack, parent);
    }

	// MODIFIES: this
	// EFFECTS: constructs a running count button which is then added to the JComponent
	// (parent)
	// which is passed in as a parameter
    @Override
	protected void createButton(JComponent parent) {
        button = new JButton("Check Running Count");
        addToParent(parent);
    }

	// MODIFIES: this
	// EFFECTS: constructs a new listener object which is added to the JButton
    @Override
    protected void addListener() {
        button.addActionListener(new RunningCountClickHandler());
    }

    private class RunningCountClickHandler implements ActionListener {

		// EFFECTS: displays running count when tool is clicked
        @Override
		public void actionPerformed(ActionEvent e) {
            blackjack.checkRunningCount();
        }
    }
}