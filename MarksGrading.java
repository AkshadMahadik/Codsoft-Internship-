import javax.swing.*;

public class MarksGrading{
    private static char calculateGrade(double avgPercent) {
        if (avgPercent >= 90) {
            return 'A';
        } else if (avgPercent >= 80) {
            return 'B';
        } else if (avgPercent >= 70) {
            return 'B';
        } else if (avgPercent >= 60) {
            return 'B';
        } else if (avgPercent >= 50) {
            return 'B';
        }
        return 0;
    }

    private static double calculateAveragePercentage(int totalMarks, int numSubjects) {
        return (double) totalMarks / numSubjects;
    }

    private static int calculateTotalMarks(int subjectMarks[]) {
        int totalMarks = 0;
        for (int marks : subjectMarks) {
            totalMarks += marks;
        }
        return totalMarks;
    }

    public static void main(String args[]) {
        // Swing components should be created on the event dispatch thread
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Student Grading Software");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Enter Number of Subjects:");
        JTextField subjectField = new JTextField(10);

        JButton calculateButton = new JButton("Calculate Grades");
        JTextArea resultArea = new JTextArea(10, 30);
        resultArea.setEditable(false);

        calculateButton.addActionListener(e -> {
            int numberOfSubjects = Integer.parseInt(subjectField.getText());
            int[] subjectMarks = new int[numberOfSubjects];

            for (int i = 0; i < numberOfSubjects; i++) {
                String input = JOptionPane.showInputDialog("Enter the marks obtained in Subject " + (i + 1) + " [Out of 100]");
                subjectMarks[i] = Integer.parseInt(input);
            }

            int totalMarks = calculateTotalMarks(subjectMarks);
            double averagePercentage = calculateAveragePercentage(totalMarks, numberOfSubjects);
            char grade = calculateGrade(averagePercentage);

            String result = "Result\nTotal Marks: " + totalMarks +
                            "\nAverage Percentage: " + averagePercentage +
                            "\nGrade: " + grade;
            resultArea.setText(result);
        });

        panel.add(label);
        panel.add(subjectField);
        panel.add(calculateButton);
        panel.add(resultArea);

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }
}
