package model;

/**
Represents a card having a question, an answer,
a status to indicate how well the user has studied,
and a counter to track how many times the user has studied the card.
 */

import org.json.JSONObject;
import persistence.Writable;

public class Card implements Writable {
    private String question;
    private String answer;
    private boolean status;
    private int studyCounter;

    // EFFECTS: Creates an instance of the class with question set to cardQuestion;
    //          answer set to cardAnswer;
    //          and status set to false indicating that it has not been mastered
    public Card(String cardQuestion, String cardAnswer) {
        this.question = cardQuestion;
        this.answer = cardAnswer;
        this.studyCounter = 0;
        this.status = false;
    }

    // EFFECTS: Creates an instance of the class with question set to given question;
    //          answer set to given answer; status set to given status;
    //          and studyCounter set to counter
    public Card(String question, String answer, boolean status, int counter) {
        this.question = question;
        this.answer = answer;
        this.status = status;
        this.studyCounter = counter;
    }

    // EFFECTS: returns the question on the card
    public String getQuestion() {
        return question;
    }

    // EFFECTS: returns the answer on the card
    public String getAnswer() {
        return answer;
    }

    // EFFECTS: returns the status of the card
    public boolean getStatus() {
        return status;
    }

    // EFFECTS: returns the studyCounter of the card
    public int getStudyCounter() {
        return studyCounter;
    }

    // MODIFIES: this
    // EFFECTS: changes question to the new question
    public String editQuestion(String newQuestion) {
        this.question = newQuestion;
        EventLog.getInstance().logEvent(new Event("Edited question to: '" + newQuestion +"'"));
        return question;
    }

    // MODIFIES: this
    // EFFECTS: changes answer to the new answer
    public String editAnswer(String newAnswer) {
        this.answer = newAnswer;
        EventLog.getInstance().logEvent(new Event("Edited answer to: '" + newAnswer + "'"));
        return answer;
    }

    // MODIFIES: this
    // EFFECTS: change status of card to true if study counter >= 3
    //          false indicating that it has not been mastered
    //          and true indicating that it has been mastered
    public boolean updateStatus() {
        if (studyCounter >= 3) {
            this.status = true;
        } else {
            this.status = false;
        }
        return status;
    }

    // MODIFIES: this
    // EFFECTS: increments study counter by 1
    public int updateStudyCounter() {
        studyCounter++;
        return studyCounter;
    }

    // MODIFIES: this
    // EFFECTS: resets status to false
    public boolean resetStatus() {
        this.status = false;
        return status;
    }

    // MODIFIES: this
    // EFFECTS: resets studyCounter to 0
    public int resetStudyCounter() {
        this.studyCounter = 0;
        return studyCounter;
    }



    // EFFECTS: returns fields in this collection into JSON
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("question", question);
        json.put("answer", answer);
        json.put("status", status);
        json.put("studyCounter", studyCounter);
        return json;
    }
}
