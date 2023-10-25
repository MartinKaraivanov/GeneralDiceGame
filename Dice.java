package GeneralDiceGame;

import java.util.Random;


/**
 * Dice class.
 */
public class Dice {
    private  int value;
    private Random random;

    /**
     * Constructor.
     */
    public Dice() {
        value = 0;
        random = new Random();
    }

    public void roll() {
        value = random.nextInt(6) + 1;
    }

    public int getValue() {
        return value;
    }
   
      
}
