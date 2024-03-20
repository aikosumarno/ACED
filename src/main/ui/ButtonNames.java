package ui;

public enum ButtonNames {
    ADDDECK("Add New Deck"),
    SELECTDECK("Select Existing Deck"),
    LOADCOLLECTION("Load Existing Collection From File"),
    SAVECOLLECTION("Save Collection To File"),
    QUIT("QUIT"),
    STUDYCARD("Study Card"),
    ADDCARD("Add Card"),
    EDITCARD("Edit Card"),
    DELETECARD("Delete Card"),
    RETURNTOCOLLECTION("Return To Collection"),
    REVIEWCARD("Review A Single Card"),
    REVIEWDECK("Review The Entire Deck");

    private final String name;

    ButtonNames(String name) {
        this.name = name;
    }

    //EFFECTS: returns name value of this button
    public String getValue() {
        return name;
    }
}
