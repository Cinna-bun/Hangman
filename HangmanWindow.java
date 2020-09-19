import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
*
* This class will provide the window for the hangman game
*
* @author Michael Sanford
* @version September 18, 2020
 */
public class HangmanWindow implements ActionListener {

    private JFrame frame;
    private JPanel panel;
    private JLabel hiddenWord,
                        numLives,
                        errorMsg,
                        guessList;
    private JTextField textField;
    private Hangman game;

    /*
    * Constructor for the hangman window, it creates a new game of hangman,
    * then sets up the frame and inputs
     */
    HangmanWindow() {
        //initialize the Hangman object
        game = new Hangman();

        //initialize the frame
        frame = new JFrame();

        //initialize our jlables
        hiddenWord = new JLabel(game.getRevealedWord());
        numLives = new JLabel("You have 10 guesses left");
        errorMsg = new JLabel(" ");
        guessList = new JLabel("You have not made any guesses yet.");

        //initialize our text input and add the actionlistener
        textField = new JTextField("", 1);
        textField.addActionListener(this);

        //initialize our panel and add all the previous elements
        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(hiddenWord);
        panel.add(guessList);
        panel.add(numLives);
        panel.add(textField);
        panel.add(errorMsg);


        //finally we initialize our frame
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Hangman");
        frame.pack();
        frame.setVisible(true);
    }

    /*
    * This is the main method
     */
    public static void main(String[] args) {
        new HangmanWindow();
    }

    /*
    * This method will reveal all the characters matching the guess made by the user
    *
    * @params letter the letter that the user input into the textbox
     */
    public void revealChar(String letter) {
        //convert the guess into a char
        char guess = letter.charAt(0);

        //grab the word, then get the parts that the player currently knows and put them in
        //a StringBuilder for easy manipulation
        String word = game.getWord();
        StringBuilder revealedWord = new StringBuilder(game.getRevealedWord());

        //look at every character of the word and compare it to the guess
        //if the two match, then replace the "_" at that point with the letter guessed
        for (int i = 0; i < word.length(); i++) {

            if (word.charAt(i) == Character.toLowerCase(guess)) {

                revealedWord.deleteCharAt(i).insert(i, guess);

            }

        }

        //store the new revealed word into word as a string,
        //then put it back into the game object
        word = revealedWord.toString();
        game.setRevealedWord(word);

        //set the hidden word to the new value,
        //then tell the user that they guessed correct
        hiddenWord.setText(word);
        errorMsg.setText("That guess was correct!");
    }

    /*
    * This method will be called when the user makes a guess, it gets the input,
    * checks if its a valid input, reveals if they guessed correct, then checks if
    * the game is over
    *
    * @params ActionEvent e the event that the window listens for
     */
    public void actionPerformed(ActionEvent e) {
        if(game.isWon() || game.isLost()) {
            return;
        }

        //grabbing the input
        String input = textField.getText();

        //making sure it's only one letter in length
        if(input.length() != 1) {
            errorMsg.setText("Sorry, you can only guess one letter at a time!");
            return;
        } //end if

        //checking if the guess is correct
        if (game.makeGuess(input)) {
            //revealing the letter if it was correct
            this.revealChar(input);
        }
        else if(!game.isLost()){
            //giving them a message saying it wasn't right
            errorMsg.setText("That letter isn't part of the word, try again!");
            //update the guess list
            guessList.setText("You've guessed: " + game.getGuessList());
        } //end if

        //update the number of guesses the user has left
        numLives.setText("You have " + game.getNumGuesses() + " guesses left");

        //if the game is over, display the win message,
        //if it's been lost, display the game over message
        if(game.isWon()) {
            errorMsg.setText("Congratulations! You win!");
        }
        else if(game.isLost()) {
            errorMsg.setText("Sorry, you're all out of guesses! Better luck next time. (The word was " + game.getWord() + ")");
        } //end if
    }
}
