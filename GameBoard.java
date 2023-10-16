package GeneralDiceGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * Game board.
 */
public class GameBoard extends JFrame {
    private static Dice[] dices;
    private static Player player;
    private static DiceRolling rolls;

    /**
     * Constr.
     */
    public GameBoard() {
        dices = new Dice[5];
        player = new Player(JOptionPane.showInputDialog("Enter name"));
        rolls = new DiceRolling();

        for (int i = 0; i < dices.length; i++) {
            dices[i] = new Dice();
        }
    }

    /**
     * Run.
     */
    public void run() {
        
        JFrame frame = new JFrame("Main Menu");
        frame.setSize(550, 550);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        frame.getContentPane().add(panel);

        JLabel title = new JLabel("General Dice Game", SwingConstants.CENTER);
        title.setBounds(10, 10, 500, 50);
        panel.add(title); 
        
        JButton newGame = new JButton("New Game");
        newGame.setBounds(277, 262, 158, 67);
        newGame.setHorizontalAlignment(JTextField.CENTER);
        newGame.setFocusPainted(false);
        panel.add(newGame);
        //newGame.addActionListener();
        
        JButton exit = new JButton("Exit");
        exit.setBounds(277, 367, 158, 67);
        exit.setHorizontalAlignment(SwingConstants.CENTER);
        exit.setFocusPainted(false);
        panel.add(exit);
        //exit.addActionListener();
        
        JLabel remainingRollsLabel = 
            new JLabel("You have " + rolls.getRemainingRolls() + "  remaining rolls");
        remainingRollsLabel.setBounds(221, 134, 270, 44);
        remainingRollsLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        remainingRollsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(remainingRollsLabel);

        JButton[] diceButtons = new JButton[5];
        for (int i = 0; i < 5; i++) {
            diceButtons[i] = new JButton();
            diceButtons[i].setHorizontalAlignment(SwingConstants.CENTER);
            diceButtons[i].setEnabled(false);
            diceButtons[i].setFocusPainted(false);
            diceButtons[i].setBounds(221 + i * 56, 77, 46, 46);
            panel.add(diceButtons[i]);
            //diceButtons[i].addActionListener();
        }

        JButton rollDices = new JButton("Roll");
        rollDices.setBounds(277, 174, 158, 44);
        rollDices.setHorizontalAlignment(JTextField.CENTER);
        rollDices.setFocusPainted(false);
        panel.add(rollDices);
        
        //boolean areDicesThrown = false;

        rollDices.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < 5; i++) {
                    if (!diceButtons[i].isEnabled()) {
                        dices[i].roll();
                        diceButtons[i].setEnabled(true);
                        diceButtons[i].setText(Integer.toString(dices[i].getValue()));
                    }
                }

                //areDicesThrown = true;
                rolls.roll();

                if (rolls.getRemainingRolls() == 0) {
                    for (int i = 0; i < 5; i++) {
                        diceButtons[i].setEnabled(false);
                    }
                }

                rollDices.setEnabled(false);
                remainingRollsLabel.setText(
                    "You have " + rolls.getRemainingRolls() + "  remaining rolls"
                );
                
                //calculate_results();
            }
        });

        JButton[] combinationButtons = new JButton[15];
        JLabel[] combinationLabels = new JLabel[15];
        String[] combinationNames = {
            "1", "2", "3", "4", "5", "6",
            "Pair", "Two Pair", "Triple", "Square", 
            "Full", "Small Bucket", "Big Bucket", "Chance", "General"
        };
        boolean[] combinationsSelected = new boolean[15];
        int combinationsUsed = 0;

        for (int i = 0; i < 15; i++) {
            combinationButtons[i] = new JButton();
            combinationButtons[i].setBounds(125, 80 + i * 20, 74, 19);
            combinationButtons[i].setFocusPainted(false);
            combinationButtons[i].setEnabled(false);
            panel.add(combinationButtons[i]);
            //combinationButtons[i].addActionListener();
            
            combinationLabels[i] = new JLabel();
            combinationLabels[i].setText(combinationNames[i]);
            combinationLabels[i].setHorizontalTextPosition(SwingConstants.CENTER);
            combinationLabels[i].setHorizontalAlignment(SwingConstants.RIGHT);
            combinationLabels[i].setBounds(0, 80 + i * 20, 116, 14);
            panel.add(combinationLabels[i]);
            
            combinationsSelected[i] = false;
        }

        JLabel totalLabel = new JLabel();
        totalLabel.setText(String.format("Total score: %2d", player.getTotalScore()));
        totalLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        totalLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        totalLabel.setBounds(0, 420, 200, 14);
        panel.add(totalLabel);

        JLabel gameOver = new JLabel();
        gameOver.setText(
            String.format("Game Over! Your total score is: %2d", player.getTotalScore())
        );
        gameOver.setBounds(81, 445, 439, 40);
        gameOver.setVisible(false);
        panel.add(gameOver);
    }
}
