package GeneralDiceGame;


import javax.swing.SwingUtilities;

/**
 * Main file.
 */
public class GeneralDiceGame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameBoard gameBoard = new GameBoard();
            gameBoard.run();
        });
    }
}
