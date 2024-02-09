package model;

public class Card {
    private String question;
    private String answer;
    private boolean status;

    // EFFECTS: Creates an instance of the class with question set to cardQuestion;
    //          answer set to cardAnswer;
    //          and status set to false indicating that it has not been mastered
    public Card(String cardQuestion, String cardAnswer) {
        this.question = cardQuestion;
        this.answer = cardAnswer;
        this.status = false;
    }

    // EFFECTS: returns the question on the card
    public String getQuestion() {
        return question; //stub
    }

    // EFFECTS: returns the answer on the card
    public String getAnswer() {
        return answer; //stub
    }

    // EFFECTS: returns the status of the card
    public boolean getStatus() {
        return status; //stub
    }

    // MODIFIES: this
    // EFFECTS: changes question to the new question
    public String editQuestion(String newQuestion) {
        this.question = newQuestion;
        return question; //stub
    }

    // MODIFIES: this
    // EFFECTS: changes answer to the new answer
    public String editAnswer(String newAnswer) {
        this.answer = newAnswer;
        return answer; //stub
    }

    // MODIFIES: this
    // EFFECTS: set status of card with new status
    //          false indicating that it has not been mastered
    //          and true indicating that it has been mastered
    public boolean changeStatus(boolean newStatus) {
        this.status = newStatus;
        return status; //stub
    }
}
