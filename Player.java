package GeneralDiceGame;

/**
 * Player class.
 */
public class Player {
    private int totalScore;
    private String name;

    /**
     * Constructor.
     */
    public Player() {
        this.totalScore = 0;
        this.name = null;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void updateScore(int score) {
        totalScore += score;
    }

    public void resetScore() {
        totalScore = 0;
    }

    
}

