package GeneralDiceGame;

/**
 * Player class.
 */
public class Player {
    private int totalScore;

    /**
     * Constructor.
     */
    public Player() {
        this.totalScore = 0;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void updateScore(int score) {
        totalScore += score;
    }
}

