package GeneralDiceGame;

import java.util.Random;


/**
 * Dice class.
 */
public class Dice {
    private int value;
    private Random random;
    private boolean isUsed;

    /**
     * Constructor.
     */
    public Dice() {
        value = 0;
        random = new Random();
        isUsed = false;
    }

    /**
     * Roll the dice.
     */
    public void roll() {
        value = random.nextInt(6) + 1;
    }

    public int getValue() {
        return value;
    } 

    public boolean getUsed() {
        return isUsed;
    }

    public void use() {
        isUsed = true;
    }

    public void unuse() {
        isUsed = false;
    }
}
