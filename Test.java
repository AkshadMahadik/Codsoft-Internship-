import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Test extends JFrame {
    private int currentQuestionIndex = 0;
    private int score = 0;
    private Timer timer;
    private int timeLeft = 30; 

    private JLabel questionLabel;
    private JRadioButton[] optionButtons;
    private JButton submitButton;
    private JLabel timerLabel;
    private JLabel resultLabel;
    private Question[] questions = {
        new Question("What does CPU stand for?", new String[]{"Central Processing Unit", "Computer Personal Unit", "Central Processor Unit"}, 0),
        new Question("Which programming language is known as the 'mother of all languages'?", new String[]{"Java", "C", "Assembly"}, 1),
        new Question("What is the purpose of RAM in a computer?", new String[]{"Permanent storage", "Temporary storage", "Processor"}, 1),
        new Question("What is an IP address used for?", new String[]{"Identifying a device on the internet", "Sending emails", "Running software"}, 0),
        new Question("Which data structure follows the Last In, First Out (LIFO) principle?", new String[]{"Queue", "Stack", "Linked List"}, 1)
};

    public Test() {
        setTitle("Quiz Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel questionPanel = new JPanel();
        questionPanel.setLayout(new BoxLayout(questionPanel, BoxLayout.Y_AXIS));
        questionLabel = new JLabel();
        questionPanel.add(questionLabel);
        optionButtons = new JRadioButton[3];
        ButtonGroup buttonGroup = new ButtonGroup();
        for (int i = 0; i < 3; i++) {
            optionButtons[i] = new JRadioButton();
            optionButtons[i].setActionCommand(String.valueOf(i));
            buttonGroup.add(optionButtons[i]);
            questionPanel.add(optionButtons[i]);
        }
        submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAnswer();
            }
        });
        questionPanel.add(submitButton);
        timerLabel = new JLabel("Time Left: " + timeLeft + " seconds");
        questionPanel.add(timerLabel);
        resultLabel = new JLabel();
        questionPanel.add(resultLabel);
        add(questionPanel, BorderLayout.CENTER);
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeLeft--;
                timerLabel.setText("Time Left: " + timeLeft + " seconds");
                if (timeLeft == 0) {
                    timer.stop();
                    checkAnswer();
                }
            }
        });
        timer.start();
        displayQuestion();
    }

    private void displayQuestion() {
        if (currentQuestionIndex < questions.length) {
            Question currentQuestion = questions[currentQuestionIndex];
            questionLabel.setText(currentQuestion.getQuestion());

            for (int i = 0; i < optionButtons.length; i++) {
                optionButtons[i].setText(currentQuestion.getOptions()[i]);
            }

            timerLabel.setText("Time Left: " + timeLeft + " seconds");
            resultLabel.setText("");
            submitButton.setEnabled(true);

            timeLeft = 15; // Reset timer for each question
            timer.restart();
        } else {
            showResult();
        }
    }

    private void checkAnswer() {
        timer.stop();
        submitButton.setEnabled(false);
        int selectedOption = -1;
        for (int i = 0; i < optionButtons.length; i++) {
            if (optionButtons[i].isSelected()) {
                selectedOption = i;
                break;
            }
        }
          if (selectedOption == questions[currentQuestionIndex].getCorrectAnswer()) {
            score++;
            resultLabel.setText("Correct!");
        } else {
            resultLabel.setText("Incorrect. Correct answer: " + questions[currentQuestionIndex].getCorrectAnswerText());
        }
        currentQuestionIndex++;
        displayQuestion();
    }

    private void showResult() {
        questionLabel.setText("Quiz Completed!");
        for (JRadioButton button : optionButtons) {
            button.setVisible(false);
        }
        submitButton.setVisible(false);
        timerLabel.setVisible(false);
        resultLabel.setText("Your Final Score: " + score + " out of " + questions.length);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Test().setVisible(true);
            }
        });
    }
}

class Question {
    private String question;
    private String[] options;
    private int correctAnswer;

    public Question(String question, String[] options, int correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public String getCorrectAnswerText() {
        return options[correctAnswer];
    }
}
