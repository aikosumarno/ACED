package ui;

import model.Card;
import model.Deck;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DeckUI extends JFrame implements ActionListener {

    private Deck currentDeck;
    private JPanel deckButtonPanel;
    private JPanel actionButtonPanel;
    private JPanel cardPanel;
    private JLabel card;

    private JButton study;
    private JButton edit;
    private JButton add;
    private JButton delete;
    private JButton returnToCollection;
    private JButton showAnswerButton;
    private JButton nextButton;
    private java.util.List<String> questions;
    private List<String> answers;
    private int currentFlashcardIndex;

    private ImageIcon logo;

    public DeckUI(Deck deck) {
        this.currentDeck = deck;

        this.setTitle(currentDeck.getName());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setSize(800,600);
        this.getContentPane().setBackground(new Color(205, 239, 255));
        buttonPanel();
        actionPanel();
        cardDisplayPanel();
        initializeLogoIcon();
        this.setLayout(null);
        this.setVisible(true);
    }

    /**
     * helper to create panel for buttons
     */
    public void buttonPanel() {
        deckButtonPanel = new JPanel();
        deckButtonPanel.setBackground(new Color(000435));
        deckButtonPanel.setBounds(0,0, 200,600);
        deckButtonPanel.setLayout(null);
        deckButtons();
        deckButtonPanel.add(study);
        deckButtonPanel.add(add);
        deckButtonPanel.add(edit);
        deckButtonPanel.add(delete);
        deckButtonPanel.add(returnToCollection);
        this.add(deckButtonPanel);
    }

    /**
     * helper to create buttons
     */
    public void deckButtons() {
        study = new JButton("Study");
        study.addActionListener(this);
        study.setBounds(0, 5, 180, 50);
        study.setFont(new Font("Dialog", Font.PLAIN, 20));

        add = new JButton("Add Card");
        add.addActionListener(this);
        add.setBounds(0, 60, 180, 50);
        add.setFont(new Font("Dialog", Font.PLAIN, 20));

        delete = new JButton("Delete Card");
        delete.addActionListener(this);
        delete.setBounds(0, 115, 180, 50);
        delete.setFont(new Font("Dialog", Font.PLAIN, 20));

        edit = new JButton("Edit Card");
        edit.addActionListener(this);
        edit.setBounds(0, 170, 180, 50);
        edit.setFont(new Font("Dialog", Font.PLAIN, 20));

        returnToCollection = new JButton("Main Menu");
        returnToCollection.addActionListener(this);
        returnToCollection.setBounds(0, 225, 180, 50);
        returnToCollection.setFont(new Font("Dialog", Font.PLAIN, 20));
    }

    /**
     * helper to create panel and buttons for show answer and next
     */
    public void actionPanel() {
        actionButtonPanel = new JPanel();
        actionButtonPanel.setBackground(new Color(0,0,128));
        actionButtonPanel.setBounds(200,450, 600,150);
        actionButtonPanel.setLayout(null);
        showAnswerButton = new JButton("Show Answer");
        showAnswerButton.addActionListener(this);
        showAnswerButton.setBounds(0, 5, 600, 40);
        showAnswerButton.setFont(new Font("Dialog", Font.PLAIN, 20));
        nextButton = new JButton("Next Question");
        nextButton.addActionListener(this);
        nextButton.setBounds(0, 50, 600, 40);
        nextButton.setFont(new Font("Dialog", Font.PLAIN, 20));
        actionButtonPanel.add(showAnswerButton);
        actionButtonPanel.add(nextButton);
        this.add(actionButtonPanel);
    }

    /**
     * helper to create panel for displaying the cards
     */
    public void cardDisplayPanel() {
        cardPanel = new JPanel();
        cardPanel.setBackground(new Color(205, 239, 255));
        cardPanel.setBounds(200,0, 600,450);
        cardPanel.setLayout(null);
        questions = currentDeck.getQuestions();
        answers = currentDeck.getAnswers();
        currentFlashcardIndex = 0;

        if (questions.size() > 0) {
            card = new JLabel(questions.get(currentFlashcardIndex));
            card.setFont(new Font("Dialog", Font.PLAIN, 50));
            card.setHorizontalAlignment(JLabel.CENTER);
            cardPanel.add(card, BorderLayout.CENTER);
        } else {
            card = new JLabel("No cards in deck");
            card.setFont(new Font("Dialog", Font.PLAIN, 50));
            card.setHorizontalAlignment(JLabel.CENTER);
            cardPanel.add(card, BorderLayout.CENTER);
        }
        this.add(cardPanel);
        cardPanel.revalidate();
        cardPanel.repaint(); // updates the panel
        this.validate();
        this.repaint();
    }

    public void initializeLogoIcon() {
        ImageIcon img = new ImageIcon("src/main/ui/images/AcedSmallLogo.png");
        Image resizedImg = img.getImage().getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
        logo = new ImageIcon(resizedImg);
    }

    // MODIFIES: adds card to deck
    // EFFECTS: adds a new card to chosen deck
    private void addCard() {
        String question = JOptionPane.showInputDialog("Enter Question: ");
        String answer = JOptionPane.showInputDialog("Enter answer: ");
        Card newCard = new Card(question, answer);
        currentDeck.addCard(newCard);
        System.out.println("Card has been added to deck.");
        cardDisplayPanel();
    }

    // REQUIRES: card exists in the deck
    // MODIFIES: changes the details of the chosen card in deck
    // EFFECTS: edits the details of the card in the deck
    private void editCard() {
        String[] options = currentDeck.getQuestions().toArray(new String[0]);
        var choice = JOptionPane.showOptionDialog(null, "Choose Card to Edit",
                "Edit Card", 0, 3, logo, options, options[0]);

        String newQuestion = JOptionPane.showInputDialog("Enter New Question: ");
        String newAnswer = JOptionPane.showInputDialog("Enter New Answer: ");

        for (int i = 0; i < options.length; i++) {
            if (choice == i) {
                currentDeck.viewDeck().get(i).editQuestion(newQuestion);
                currentDeck.viewDeck().get(i).editAnswer(newAnswer);
                currentDeck.viewDeck().get(i).resetStatus();
                currentDeck.viewDeck().get(i).resetStudyCounter();
            }
        }
    }


    // REQUIRES: card exists in the deck
    // MODIFIES: removes card from deck
    // EFFECTS: deletes card from the deck
    private void deleteCard() {
        String[] options = currentDeck.getQuestions().toArray(new String[0]);
        var choice = JOptionPane.showOptionDialog(null, "Select the Card you want to delete",
                "Delete Card", 0, 3, logo, options, options[0]);

        for (int i = 0; i < options.length; i++) {
            if (choice == i) {
                currentDeck.deleteCard(currentDeck.viewDeck().get(i));
            }
        }
    }

    /**
     * helper method to study deck
     */
    private void study() {
        String[] options = {"Study Card", "Study Entire Deck"};
        var choice = JOptionPane.showOptionDialog(null, "Select study method",
                "Study Time", 0, 3, logo, options, options[0]);
        if (choice == 0) {
            studyCard();
        } else if (choice == 2) {
            studyEntireDeck();
        }
    }

    public void studyCard() {
        String[] questions = currentDeck.getQuestions().toArray(answers.toArray(new String[0]));
        var chosen = JOptionPane.showOptionDialog(null, "Select the Card you want to study",
                "Study Card", 0, 3, logo, questions, questions[0]);
        for (int i = 0; i < questions.length; i++) {
            if (chosen == i) {
                String answer = JOptionPane.showInputDialog(currentDeck.getQuestions().get(i));
                if (answer.equals(currentDeck.viewDeck().get(i).getAnswer())) {
                    JOptionPane.showMessageDialog(null, "That's correct!");
                    currentDeck.viewDeck().get(i).updateStudyCounter();
                    currentDeck.viewDeck().get(i).updateStatus();
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect!"
                            + "\n The correct answer is " + currentDeck.viewDeck().get(i).getAnswer());
                }
            }
        }
    }

    // REQUIRES: deck.getSize() >= 1
    // MODIFIES: cards in chosen deck
    // EFFECTS: prints question of every card in deck, checks user's answer
    //           and updates card study counter and status accordingly
    public void studyEntireDeck() {
        int score = 0;
        int num = 0;
        List<Card> cards = currentDeck.viewDeck();
        for (Card c : cards) {
            String answer = JOptionPane.showInputDialog(c.getQuestion() + "\n Enter Answer: ");
            if (answer.equals(c.getAnswer())) {
                score++;
                JOptionPane.showMessageDialog(null, "Correct!");
                currentDeck.viewDeck().get(num).updateStudyCounter();
                currentDeck.viewDeck().get(num).updateStatus();
            } else {
                JOptionPane.showMessageDialog(null, "Incorrect!"
                        + "\n The correct answer is " + currentDeck.viewDeck().get(num).getAnswer());
            }
            num++;
        }
        JOptionPane.showMessageDialog(null,
                "You got " + score + " out of " + cards.size() + " questions right.");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == showAnswerButton) {
            card.setText(answers.get(currentFlashcardIndex));
            showAnswerButton.setEnabled(false);
            nextButton.setEnabled(true);
        } else if (e.getSource() == nextButton) {
            currentFlashcardIndex = (currentFlashcardIndex + 1) % questions.size();
            card.setText(questions.get(currentFlashcardIndex));
            showAnswerButton.setEnabled(true);
            nextButton.setEnabled(false);
        } else if (e.getSource() == study) {
            study();
        } else if (e.getSource() == add) {
            addCard();
        } else if (e.getSource() == edit) {
            editCard();
        } else if (e.getSource() == delete) {
            deleteCard();
        } else if (e.getSource() == returnToCollection) {
            dispose();
        }
    }
}
