import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuessGameGUI {
    private int randomNumber;
    private int maxAttempts = 5;
    private int attemptsLeft;

    private JTextField guessField;
    private JTextArea resultArea;

    public GuessGameGUI() {
        JFrame frame = new JFrame("Guess Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Guess the Number (1-100)");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        guessField = new JTextField(10);
        JButton guessButton = new JButton("Guess");
        resultArea = new JTextArea(10, 30);
        resultArea.setEditable(false);

        panel.add(titleLabel);
        panel.add(guessField);
        panel.add(guessButton);
        panel.add(resultArea);

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleGuess();
            }
        });

        frame.getContentPane().add(panel);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        startNewGame();
    }

    private void startNewGame() {
        randomNumber = (int) (Math.random() * 100) + 1;
        attemptsLeft = maxAttempts;
        resultArea.setText("New game started. You have " + attemptsLeft + " attempts.");
    }

    private void handleGuess() {
        if (attemptsLeft <= 0) {
            resultArea.setText("Sorry, you've run out of attempts. The correct number was: " + randomNumber);
            guessField.setEnabled(false);
        } else {
            try {
                int guess = Integer.parseInt(guessField.getText());
                checkGuess(guess);
            } catch (NumberFormatException ex) {
                resultArea.setText("Please enter a valid number.");
            }
        }
    }

    private void checkGuess(int guess) {
        attemptsLeft--;

        if (guess < 1 || guess > 100) {
            resultArea.setText("Please enter a number between 1 and 100.");
        } else if (guess == randomNumber) {
            resultArea.setText("Congratulations! You guessed the correct number!");
            guessField.setEnabled(false);
        } else if (guess < randomNumber) {
            resultArea.setText("Too low! Try again. Attempts left: " + attemptsLeft);
        } else {
            resultArea.setText("Too high! Try again. Attempts left: " + attemptsLeft);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GuessGameGUI());
    }
}
