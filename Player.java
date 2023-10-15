package GeneralDiceGame;

/**
 * Player class.
 */
public class Player {
    private String username;
    private int totalScore;

    /**
     * Constructor.
     */
    public Player(String username) {
        this.username = username;
        this.totalScore = 0;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        username = name;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void updateScore(int score) {
        totalScore += score;
    }
}

