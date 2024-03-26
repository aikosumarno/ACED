package ui.pages;

import model.Card;
import model.Deck;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.List;

public class DeckUI extends JFrame implements ActionListener {
    private JLabel deckLabel;
    private JButton study;
    private JButton edit;
    private JButton add;
    private JButton delete;
    private JButton returnToCollection;
    private JButton showAnswerButton;
    private JButton nextButton;
    private List<String> questions;
    private List<String> answers;
    private int currentFlashcardIndex;

    private Deck deck;

    public DeckUI(Deck deck) {
        this.deck = deck;
        setTitle(deck.getName());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(new Color(205, 239, 255));
        setSize(800, 500);
        setLayout(new FlowLayout());
        setLocationRelativeTo(null);

        displayCards();
    }

//    public void createMenuBar() {
//        menuBar = new JMenuBar();
//        study = new JMenu("Study");
//        studyDeck = new JMenuItem("Review Entire Deck");
//        studyDeck.addActionListener(this);
//        studyCard = new JMenuItem("Review a Card");
//        studyCard.addActionListener(this);
//        add = new JMenu("Add");
//        add.addActionListener(this);
//        edit = new JMenu("Edit");
//        edit.addActionListener(this);
//        delete = new JMenu("Delete");
//        delete.addActionListener(this);
//        returnToCollection = new JMenu("Return to Collection");
//
//        menuBar.add(study);
//        study.add(studyDeck);
//        study.add(studyCard);
//        menuBar.add(add);
//        menuBar.add(edit);
//        menuBar.add(delete);
//        menuBar.add(returnToCollection);
//        setJMenuBar(menuBar);
//    }
    /**
    helper method to create buttons
     */
    public void createButtons() {
        study = new JButton("Study");
        study.addActionListener(this);
        study.setSize(800, 50);
        study.setFont(new Font("Dialog", Font.PLAIN, 20));

        add = new JButton("Add Card");
        add.addActionListener(this);
        add.setSize(800, 50);
        add.setFont(new Font("Dialog", Font.PLAIN, 20));

        delete = new JButton("Delete Card");
        delete.addActionListener(this);
        delete.setSize(800, 50);
        delete.setFont(new Font("Dialog", Font.PLAIN, 20));

        edit = new JButton("Delete Card");
        edit.addActionListener(this);
        edit.setSize(800, 50);
        edit.setFont(new Font("Dialog", Font.PLAIN, 20));

        returnToCollection = new JButton("Main Menu");
        returnToCollection.addActionListener(this);
        returnToCollection.setSize(800, 50);
        returnToCollection.setFont(new Font("Dialog", Font.PLAIN, 20));

    }

    /**
     * helper method to display cards in the deck
     */
    public void displayCards() {
        questions = deck.getQuestions();
        answers = deck.getAnswers();
        currentFlashcardIndex = 0;

        if (questions.size() > 0) {
            deckLabel = new JLabel(questions.get(currentFlashcardIndex));
            deckLabel.setHorizontalAlignment(JLabel.CENTER);

            // buttons
            showAnswerButton = new JButton("Show Answer");
            showAnswerButton.addActionListener(this);

            nextButton = new JButton("Next");
            nextButton.addActionListener(this);

            // components
            Container contentPane = getContentPane();
            contentPane.setLayout(new BorderLayout());
            contentPane.add(deckLabel, BorderLayout.CENTER);

            JPanel buttonPanel = new JPanel(new GridLayout(7, 2, 8, 1));
            buttonPanel.add(showAnswerButton);
            buttonPanel.add(nextButton);
            buttonPanel.add(study);
            buttonPanel.add(add);
            buttonPanel.add(edit);
            buttonPanel.add(delete);
            buttonPanel.add(returnToCollection);
            contentPane.add(buttonPanel, BorderLayout.SOUTH);
        } else {
            deckLabel = new JLabel("No cards in deck");
            deckLabel.setHorizontalAlignment(JLabel.CENTER);
        }

    }

    // MODIFIES: adds card to deck
    // EFFECTS: adds a new card to chosen deck
    private void addCard() {
        String question = JOptionPane.showInputDialog("Enter Question: ");
        String answer = JOptionPane.showInputDialog("Enter answer: ");
        Card newCard = new Card(question, answer);
        deck.addCard(newCard);
        System.out.println("Card has been added to deck.");
        displayCards();
    }

    // REQUIRES: card exists in the deck
    // MODIFIES: changes the details of the chosen card in deck
    // EFFECTS: edits the details of the card in the deck
//    private void editCard(Deck deck) {
//        int num = 0;
//
//        while (num <= 0 || num > deck.getSize()) {
//            System.out.println("\nEnter number of card you would like to edit:");
//            num = input.nextInt();
//        }
//
//        num = num - 1;
//        System.out.println("Enter New Question: ");
//        String newQuestion = input.next();
//        System.out.println("Enter New Answer: ");
//        String newAnswer = input.next();
//
//        deck.viewDeck().get(num).editQuestion(newQuestion);
//        deck.viewDeck().get(num).editAnswer(newAnswer);
//        deck.viewDeck().get(num).resetStatus();
//        deck.viewDeck().get(num).resetStudyCounter();
//        viewDeck(deck);
//    }
//
//    // REQUIRES: card exists in the deck
//    // MODIFIES: removes card from deck
//    // EFFECTS: deletes card from the deck
//    private void deleteCard(Deck deck) {
//        int num = 0;
//
//        while (num <= 0 || num > deck.getSize()) {
//            System.out.println("\nEnter number of card you would like to delete:");
//            num = input.nextInt();
//        }
//        num = num - 1;
//
//        deck.deleteCard(deck.viewDeck().get(num));
//        viewDeck(deck);
//    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        if (e.getSource() instanceof JMenuItem) {
//            JMenuItem item = (JMenuItem) e.getSource();
//            String text = item.getText();
//            System.out.println(text);
//        }
        if (e.getSource() == showAnswerButton) {
            deckLabel.setText(answers.get(currentFlashcardIndex));
            showAnswerButton.setEnabled(false);
            nextButton.setEnabled(true);
        } else if (e.getSource() == nextButton) {
            currentFlashcardIndex = (currentFlashcardIndex + 1) % questions.size();
            deckLabel.setText(questions.get(currentFlashcardIndex));
            showAnswerButton.setEnabled(true);
            nextButton.setEnabled(false);
        }
        if (e.getSource() == add) {
            addCard();
        }
    }

}