package GeneralDiceGame;

/**
 * Combinations use.
 */
public class CombinationsUse {
    private int numberOfUsedComb;
    private boolean[] combinationUsed;

    /**
     * Constructor.
     */
    public CombinationsUse() {
        numberOfUsedComb = 0;
        combinationUsed = new boolean[15];

        for (int i = 0; i < 15; i++) {
            combinationUsed[i] = false;
        }
    }

    public int getNumberOfUsed() {
        return numberOfUsedComb;
    }

    public boolean isCombinationUsed(int combination) {
        return combinationUsed[combination];
    }

    /**
     * Use function.
     * @param combination - which combination.
     */
    public void useCombination(int combination) {
        combinationUsed[combination] = true;
        numberOfUsedComb++;
    }

    /**
     * Reset function.
     */
    public void resetCombinations() {
        numberOfUsedComb = 0;
        for (int i = 0; i < 15; i++) {
            combinationUsed[i] = false;
        }
    }
}
