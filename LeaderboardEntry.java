package GeneralDiceGame;

/**
 * Leaderboard class.
 */

public class LeaderboardEntry {
    private String playerName;
    private int score;

    /**
     * Constructor.
     */
    
    public LeaderboardEntry(String playerName, int score) {
        this.playerName = playerName;
        this.score = score;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getScore() {
        return score;
    }
}
