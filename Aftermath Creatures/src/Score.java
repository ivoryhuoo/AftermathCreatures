public class Score {
	private int score = 0; // Initialize the score


    // Constructor to initialize the score
    public Score() {
        this.score = 0; // Initial score
    }

    // Method to get the current score
    public int getScore() {
        return score;
    }

    // Method to increase the score
    public void increaseScore(int points) {
        score += points;
    }

    // Method to decrease the score (optional, for game over scenarios, etc.)
    public void decreaseScore(int points) {
        score -= points;
        if (score < 0) score = 0; // Ensure the score doesn't go negative
    }

    // Method to reset the score (e.g., when restarting the game)
    public void resetScore() {
        score = 0;
    }
}
