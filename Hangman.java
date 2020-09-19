
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


    /*
    * Empty constructor, doesn't need params as it initializes the same way every time
     */
    Hangman() {
        //set up the word by picking from the array above at random
        word = wordLibrary[(int) (Math.random() * wordLibrary.length)];
        //set what the player currently knows as nothing
        revealedWord = "";

        //build what the player knows as a bunch of underscores since they don't know anything yet
        for(int i = 0; i < word.length(); i++) {
            revealedWord += "_";
        }

        //set the number of guesses left to 10
        numGuesses = 10;
        //set what they've already guessed to nothing
        guessList = "";
    }

    /*
    * This method will see if what the player guessed is correct or not
    * and return true or false accordingly
    *
    * @params letter the letter that the player guessed
     */
    public boolean makeGuess(String letter) {
        //if the player ran out of guesses, return false
        if (this.numGuesses < 1) {
            return false;
        }

        //decrement the number of guesses remaining
        this.numGuesses--;

        //if the word contains the letter guessed, then return true,
        //else return false
        if (this.word.contains(letter.toLowerCase())) {
            return true;
        } else {
            if (!this.revealedWord.contains(letter)) {
                this.guessList += letter + ", ";
            }
            return false;
        }
    } //makeGuess


    /*
    * This method will return a true or false depending on if
    * the player has already won the game
     */
    public boolean isWon() {
        //check if the player still doesn't know any of the characters,
        //if so, return false
        for (int i = 0; i < revealedWord.length(); i++) {
            if(revealedWord.charAt(i) == '_') {
                return false;
            } // end if
        } //end for

        //default to returning true
        return true;
    }


    /*
    * This method will return a true or false depending on if the player has already
    * lost the game
     */
    public boolean isLost() {
        if (numGuesses < 1) {
            return true;
        }

        return false;
    }

    /*
    * This is the section for our accessors and mutators below:
     */
    public String getWord() { return this.word; }

    public String getRevealedWord() { return this.revealedWord; }

    public int getNumGuesses() { return this.numGuesses; }

    public String getGuessList() { return this.guessList; }

    public void setRevealedWord(String s) { this.revealedWord = s; }
}
