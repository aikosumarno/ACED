package ui.pages;

import model.Deck;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.List;

public class DeckUI extends JFrame implements ActionListener {
    private JLabel deckLabel;
    private JMenuBar menuBar;
    private JMenu study;
    private JMenuItem studyCard;
    private JMenuItem studyDeck;
    private JMenu edit;
    private JMenu add;
    private JMenu delete;
    private JMenu returnToCollection;
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

        createMenuBar();

        displayCards();
    }

    public void createMenuBar() {
        menuBar = new JMenuBar();
        study = new JMenu("Study");
        studyDeck = new JMenuItem("Review Entire Deck");
        studyCard = new JMenuItem("Review a Card");
        add = new JMenu("Add");
        edit = new JMenu("Edit");
        delete = new JMenu("Delete");
        returnToCollection = new JMenu("Return to Collection");

        menuBar.add(study);
        study.add(studyDeck);
        study.add(studyCard);
        menuBar.add(add);
        menuBar.add(edit);
        menuBar.add(delete);
        menuBar.add(returnToCollection);
        setJMenuBar(menuBar);
    }

    public void displayCards() {
        questions = deck.getQuestions();
        answers = deck.getAnswers();
        currentFlashcardIndex = 0;

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

        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 8, 1));
        buttonPanel.add(showAnswerButton);
        buttonPanel.add(nextButton);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JMenuItem) {
            JMenuItem item = (JMenuItem) e.getSource();
            String text = item.getText();
            System.out.println(text);
        }
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
    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            DeckUI deckUI = new DeckUI();
//            deckUI.setVisible(true);
//        });
//    }
}
