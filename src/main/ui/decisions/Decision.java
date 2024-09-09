package ui.decisions;

import ui.BlackJackApp;

import javax.swing.*;

// All decisions are adapted from the SimpleDrawingPlayer-Starter
public abstract class Decision {

    protected JButton button;
    protected BlackJackApp blackjack;
    private boolean active;

    public Decision(BlackJackApp blackjack, JComponent parent) {
        this.blackjack = blackjack;
        createButton(parent);
        addToParent(parent);
        active = false;
        addListener();
    }

    // MODIFIES: this
    // EFFECTS: customizes the button used for this tool
    protected JButton customizeButton(JButton button) {
        button.setBorderPainted(true);
        button.setFocusPainted(true);
        button.setContentAreaFilled(true);
        return button;
    }

    // getters
    public boolean isActive() {
        return active;
    }

    // EFFECTS: sets this Tool's active field to true
    public void activate() {
        active = true;
    }

    // EFFECTS: sets this Tool's active field to false
    public void deactivate() {
        active = false;
    }

    // EFFECTS: creates button to activate tool
    protected abstract void createButton(JComponent parent);

    // EFFECTS: adds a listener for this tool
    protected abstract void addListener();

    // MODIFIES: parent
    // EFFECTS: adds the given button to the parent component
    public void addToParent(JComponent parent) {
        parent.add(button);
    }
}
