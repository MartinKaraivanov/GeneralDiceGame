package GeneralDiceGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
    private static CombinationsUse combinationsSelected;

    /**
     * Constr.
     */
    public GameBoard() {
        dices = new Dice[5];
        player = new Player();
        rolls = new DiceRolling();
        combinationsSelected = new CombinationsUse();

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

        JLabel remainingRollsLabel = 
            new JLabel("You have " + rolls.getRemainingRolls() + " remaining rolls");
        remainingRollsLabel.setBounds(221, 134, 270, 44);
        remainingRollsLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        remainingRollsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(remainingRollsLabel);

        JLabel totalLabel = new JLabel();
        totalLabel.setText("Total score: " + player.getTotalScore());
        totalLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        totalLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        totalLabel.setBounds(0, 420, 200, 14);
        panel.add(totalLabel);

        JLabel gameOver = new JLabel();
        gameOver.setBounds(81, 445, 439, 40);
        gameOver.setVisible(false);
        panel.add(gameOver);

        JButton rollDices = new JButton("Roll");
        rollDices.setBounds(277, 174, 158, 44);
        rollDices.setHorizontalAlignment(JTextField.CENTER);
        rollDices.setFocusPainted(false);
        panel.add(rollDices);

        JButton[] diceButtons = new JButton[5];
        for (int i = 0; i < 5; i++) {
            diceButtons[i] = new JButton();
            diceButtons[i].setHorizontalAlignment(SwingConstants.CENTER);
            diceButtons[i].setEnabled(false);
            diceButtons[i].setFocusPainted(false);
            diceButtons[i].setBounds(221 + i * 56, 77, 46, 46);
            panel.add(diceButtons[i]);
            
            final int currentDice = i;

            diceButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    diceButtons[currentDice].setEnabled(false);
                    diceButtons[currentDice].setText(null);
                    rollDices.setEnabled(true);
                }
            });
        }

        JButton[] combinationButtons = new JButton[15];
        JLabel[] combinationLabels = new JLabel[15];
        String[] combinationNames = {
            "1", "2", "3", "4", "5", "6",
            "Pair", "Two Pair", "Triple", "Square", 
            "Full", "Small Bucket", "Big Bucket", "Chance", "General"
        };

        for (int i = 0; i < 15; i++) {
            combinationButtons[i] = new JButton();
            combinationButtons[i].setBounds(125, 80 + i * 20, 74, 19);
            combinationButtons[i].setFocusPainted(false);
            combinationButtons[i].setEnabled(false);
            panel.add(combinationButtons[i]);

            final int currentCombination = i;

            combinationButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    combinationsSelected.useCombination(currentCombination);
                    
                    rolls.resetRolls();
                    
                    remainingRollsLabel.setText(
                        "You have " + rolls.getRemainingRolls() + " remaining rolls"
                    );
                    
                    player.updateScore(
                        Integer.parseInt(combinationButtons[currentCombination].getText())
                    );
                    
                    totalLabel.setText("Total score: " + player.getTotalScore());
                    
                    for (int j = 0; j < 5; j++) {
                        diceButtons[j].setText(null);
                        diceButtons[j].setEnabled(false);
                        diceButtons[j].setFocusPainted(false);
                    }

                    rollDices.setEnabled(true);

                    for (int j = 0; j < 15; j++) {
                        combinationButtons[j].setEnabled(false);
                        if (!combinationsSelected.isCombinationUsed(j)) {
                            combinationButtons[j].setText(null);
                        }
                    }

                    if (combinationsSelected.getNumberOfUsed() == 15) {
                        gameOver.setText(
                            ("Game Over! Your total score is: " + player.getTotalScore())
                        );
                        gameOver.setVisible(true);
                        rollDices.setEnabled(false);
                    }
                }
            });
            
            combinationLabels[i] = new JLabel();
            combinationLabels[i].setText(combinationNames[i]);
            combinationLabels[i].setHorizontalTextPosition(SwingConstants.CENTER);
            combinationLabels[i].setHorizontalAlignment(SwingConstants.RIGHT);
            combinationLabels[i].setBounds(0, 80 + i * 20, 116, 14);
            panel.add(combinationLabels[i]);
        }

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

                rolls.roll();

                if (rolls.getRemainingRolls() == 0) {
                    for (int i = 0; i < 5; i++) {
                        diceButtons[i].setEnabled(false);
                    }
                }

                rollDices.setEnabled(false);
                remainingRollsLabel.setText(
                    "You have " + rolls.getRemainingRolls() + " remaining rolls"
                );
                
                int[] whatNumbers = new int[5];

                for (int i = 0; i < 5; i++) {
                    whatNumbers[i] = dices[i].getValue();
                }
                
                Arrays.sort(whatNumbers);
                
                for (int i = 1; i <= 6; i++) {
                    if (combinationsSelected.isCombinationUsed(i - 1)) {
                        continue;
                    }

                    combinationButtons[i - 1].setEnabled(true);

                    int numberOfThis = 0;
                    for (int j = 0; j < 5; j++) {
                        if (whatNumbers[j] == i) {
                            numberOfThis++;
                        }
                    }
                    
                    if (numberOfThis < 3) {
                        combinationButtons[i - 1].setText("-25");
                    } else {
                        combinationButtons[i - 1].setText(Integer.toString((numberOfThis - 3) * i));
                    }
                
                }

                /// Pair
                if (!combinationsSelected.isCombinationUsed(6)) {
                    combinationButtons[6].setEnabled(true);
                    combinationButtons[6].setText("0");
                    for (int i = 3; i >= 0; i--) {
                        if (whatNumbers[i] == whatNumbers[i + 1]) {
                            combinationButtons[6].setText(Integer.toString(whatNumbers[i] * 2));
                        }
                    }
                }
                
                /// 2 Pair
                if (!combinationsSelected.isCombinationUsed(7)) {
                    combinationButtons[7].setEnabled(true);
                    combinationButtons[7].setText("0");
                    for (int i = 3; i >= 0; i--) {
                        if (whatNumbers[i] == whatNumbers[i + 1]) {
                            for (int j = i - 2; j >= 0; j--) {
                                if (whatNumbers[j] == whatNumbers[j + 1]) {
                                    combinationButtons[7].setText(
                                        Integer.toString(whatNumbers[i] * 2 + whatNumbers[j] * 2)
                                    );
                                }
                            }
                        }
                    }
                }
                
                /// Triple
                if (!combinationsSelected.isCombinationUsed(8)) {
                    combinationButtons[8].setEnabled(true);
                    combinationButtons[8].setText("0");
                    for (int i = 2; i >= 0; i--) {
                        if (whatNumbers[i] == whatNumbers[i + 2]) {
                            combinationButtons[8].setText(Integer.toString(whatNumbers[i] * 3));
                        }
                    }
                }
                
                /// Square
                if (!combinationsSelected.isCombinationUsed(9)) {
                    combinationButtons[9].setEnabled(true);
                    combinationButtons[9].setText("0");
                    for (int i = 1; i >= 0; i--) {
                        if (whatNumbers[i] == whatNumbers[i + 3]) {
                            combinationButtons[9].setText(Integer.toString(whatNumbers[i] * 4));
                        }
                    }
                }

                ///Full
                if (!combinationsSelected.isCombinationUsed(10)) {
                    combinationButtons[10].setEnabled(true);
                    combinationButtons[10].setText("0");
                    if (whatNumbers[0] == whatNumbers[2] && whatNumbers[3] == whatNumbers[4]) {
                        combinationButtons[10].setText(
                            Integer.toString(whatNumbers[0] * 3 + whatNumbers[3] * 2)
                        );
                    } else {
                        if (whatNumbers[0] == whatNumbers[1] && whatNumbers[2] == whatNumbers[4]) {
                            combinationButtons[10].setText(
                                Integer.toString(whatNumbers[0] * 2 + whatNumbers[2] * 3)
                            );
                        }
                    }
                }
                
                ///Small Bucket
                if (!combinationsSelected.isCombinationUsed(11)) {
                    combinationButtons[11].setEnabled(true);
                    combinationButtons[11].setText("0");
                    if (whatNumbers[0] == 1 
                        && whatNumbers[1] == 2 
                        && whatNumbers[2] == 3 
                        && whatNumbers[3] == 4 
                        && whatNumbers[4] == 5
                    ) {
                        combinationButtons[11].setText("15");
                    }
                }
                
                ///Big Bucket
                if (!combinationsSelected.isCombinationUsed(12)) {
                    combinationButtons[12].setEnabled(true);
                    combinationButtons[12].setText("0");
                    if (whatNumbers[0] == 2 
                        && whatNumbers[1] == 3 
                        && whatNumbers[2] == 4 
                        && whatNumbers[3] == 5 
                        && whatNumbers[4] == 6
                    ) {
                        combinationButtons[12].setText("20");
                    }
                }
                
                ///Chance
                if (!combinationsSelected.isCombinationUsed(13)) {
                    combinationButtons[13].setEnabled(true);
                    combinationButtons[13].setText(
                        Integer.toString(
                            whatNumbers[0] 
                            + whatNumbers[1] 
                            + whatNumbers[2] 
                            + whatNumbers[3] 
                            + whatNumbers[4]
                        )
                    );
                }
                
                ///General
                if (!combinationsSelected.isCombinationUsed(14)) {
                    combinationButtons[14].setEnabled(true);
                    combinationButtons[14].setText("0");
                    if (whatNumbers[0] == whatNumbers[4]) {
                        combinationButtons[14].setText(Integer.toString(whatNumbers[0] * 5 + 50));
                    }
                }
            }
        });

        JButton exit = new JButton("Exit");
        exit.setBounds(277, 367, 158, 67);
        exit.setHorizontalAlignment(SwingConstants.CENTER);
        exit.setFocusPainted(false);
        panel.add(exit);
        
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JButton newGame = new JButton("New Game");
        newGame.setBounds(277, 262, 158, 67);
        newGame.setHorizontalAlignment(JTextField.CENTER);
        newGame.setFocusPainted(false);
        panel.add(newGame);
        
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rolls.resetRolls();
                remainingRollsLabel.setText(
                    "You have " + rolls.getRemainingRolls() + " remaining rolls"
                );
                
                player.updateScore(-player.getTotalScore());
                totalLabel.setText("Total score: " + player.getTotalScore());
                
                for (int i = 0; i < 5; i++) {
                    diceButtons[i].setText(null);
                    diceButtons[i].setEnabled(false);
                    diceButtons[i].setFocusPainted(false);
                }
                rollDices.setEnabled(true);

                for (int i = 0; i < 15; i++) {
                    combinationButtons[i].setText(null);
                    combinationButtons[i].setEnabled(false);
                }
                combinationsSelected.resetCombinations();

                gameOver.setVisible(false);
            }
        });
    }
}
