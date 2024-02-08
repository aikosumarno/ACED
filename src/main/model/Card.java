package model;

public class Card {

    // EFFECTS: Creates an instance of the class with question set to cardQuestion;
    //          answer set to cardAnswer;
    //          and status set to false indicating that it has not been mastered
    public Card(String cardQuestion, String cardAnswer) {

    }

    // EFFECTS: returns the question on the card
    public String getQuestion() {
        return null; //stub
    }

    // EFFECTS: returns the answer on the card
    public String getAnswer() {
        return null; //stub
    }

    // EFFECTS: returns the status of the card
    public boolean getStatus() {
        return false; //stub
    }

    // MODIFIES: this
    // EFFECTS: changes question to the new question
    public String editQuestion(String newQuestion) {
        return null; //stub
    }

    // MODIFIES: this
    // EFFECTS: changes answer to the new answer
    public String editAnswer(String newAnswer) {
        return null; //stub
    }

    // MODIFIES: this
    // EFFECTS: set status of card with new status
    //          false indicating that it has not been mastered
    //          and true indicating that it has been mastered
    public boolean changeStatus(boolean newStatus) {
        return false; //stub
    }
}
