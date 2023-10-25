package GeneralDiceGame;

/**
 * Remaining rolls.
 */
public class DiceRolling {
    private int remainingRolls;

    public DiceRolling() {
        remainingRolls = 3;
    }

    public int getRemainingRolls() {
        return remainingRolls;
    }

    public void roll() {
        remainingRolls--;
    }

    public void resetRolls() {
        remainingRolls = 3;
    }
}