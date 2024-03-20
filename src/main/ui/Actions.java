package ui;

import javax.swing.*;


public abstract class Actions {
    protected JButton button;
    protected FlashcardApp app;
    private boolean active;

    // EFFECTS: constructs an Action associated with the given app
    // with its activation button inside the given parent
    public Actions(FlashcardApp app, JComponent parent) {
        this.app = app;
        createButton(parent);
        addToParent(parent);
        active = false;
    }

    // MODIFIES: this
    // EFFECTS:  customizes the button used for this tool
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

    // EFFECTS: sets this Action's active field to true
    public void activate() {
        active = true;
    }

    // EFFECTS: sets this Action's active field to false
    public void deactivate() {
        active = false;
    }

    // EFFECTS: creates button to activate action
    protected abstract void createButton(JComponent parent);

    // MODIFIES: parent
    // EFFECTS:  adds the given button to the parent component
    public void addToParent(JComponent parent) {
        parent.add(button);
    }

}
