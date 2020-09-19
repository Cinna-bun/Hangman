
/*
* This is the hangman game class
*
* @author Michael Sanford
* @version September 18, 2020
 */
public class Hangman {
    private static String[] wordLibrary = {"summer", "winter", "revenue", "data",
            "income", "imagination", "response",
            "agile", "progress", "catalogue", "outline",
            "trumpet", "tuba", "horn", "cookie", "waffel",
            "butter", "knife", "grave", "math", "calculus",
            "physics", "german", "spanish", "italian", "french",
            "bread", "oblivion", "advil", "cactus", "candy", "pirate",
            "tulip", "garden", "farmer", "gold", "noble", "nuclear"}; //preset list of words
    private String word; //the word they need to guess to win
    private String revealedWord; //how much of the word they currently have done
    private int numGuesses; //how many guesses they have left
    private String guessList; //the list of characters they've guessed so far


    Hangman() {
        word = wordLibrary[(int) (Math.random() * wordLibrary.length)];
        revealedWord = "";

        for(int i = 0; i < word.length(); i++) {
            revealedWord += "_";
        }

        numGuesses = 10;
        guessList = "";
    }

    public boolean makeGuess(String letter) {
        if (this.numGuesses < 1) {
            return false;
        }

        this.numGuesses--;

        if (this.word.toLowerCase().contains(letter.toLowerCase())) {
            return true;
        } else {
            if (!this.revealedWord.contains(letter)) {
                this.guessList += letter + ", ";
            }
            return false;
        }
    } //makeGuess

    public boolean isWon() {
        for (int i = 0; i < revealedWord.length(); i++) {
            if(revealedWord.charAt(i) == '_') {
                return false;
            } // end if
        } //end for

        return true;
    }

    public boolean isLost() {
        if (numGuesses < 1) {
            return true;
        }

        return false;
    }

    public String getWord() { return this.word; }

    public String getRevealedWord() { return this.revealedWord; }

    public int getNumGuesses() { return this.numGuesses; }

    public String getGuessList() { return this.guessList; }

    public void setRevealedWord(String s) { this.revealedWord = s; }
}
