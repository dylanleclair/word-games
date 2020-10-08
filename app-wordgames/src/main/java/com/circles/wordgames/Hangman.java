package com.circles.wordgames;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import com.circles.wordgames.Game.IGame;

public class Hangman extends Game implements IGame {

    protected String word;
    private final String WORDSPATH = "commonwords.txt";
    private ArrayList<Character> guessed = new ArrayList<Character>();
    private int correct = 0;
    private int guesses;


    // chooses a random word from a list of words in the text file named by WORDSPATH.
    private String chooseWord() throws Exception {

        InputStream is = getClass().getClassLoader().getResourceAsStream(WORDSPATH);

        Scanner s = new Scanner(is);
        ArrayList<String> words = new ArrayList<String>();

        while (s.hasNext()) {
            String word = s.next();
            words.add(word);
        }

        s.close();

        Random rand = new Random();
        int index = rand.nextInt(words.size());

        String out = words.get(index).toLowerCase().trim();

        return out;

    }

    private Character runGuess (Scanner s) {

        String guess = getInput(s);


        if (guess.length() == 1) {
            Character c = guess.charAt(0);
            if (Character.isLetter(c)) {
                return c;
            }
        }
        return null;

    }



    private String wordWithBlanks() {
        String output = "";

        for (Character c : word.toCharArray()) {
            if (guessed.contains(c)) {
                output += c;
            } else {
                output += "_";
            }
        }

        return output;

    }

    @Override
    public void printState() {
        System.out.println(summarizeState());
    }

    public String summarizeState() {
        String output = "";
        output += "Current state: " + wordWithBlanks() +"\n";
        output += "Incorrect guesses remaining: " + guesses + "\n";
        return output;
    }

    
    @Override
    public void setup() {
        System.out.println("Moving into setup state.");
                
        try {
            this.word = chooseWord();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void play(Scanner s) {
        System.out.println("Moving into play state:");

        printState();

        while (correct < word.length() && guesses > 0) {


            Character c = runGuess(s);
            
            if (c != null) {


                if(!guessed.contains(c)) {
                    // if the letter has not already been guessed

                    if (word.contains(c.toString())) {
                        // correct
                        for (char character : word.toCharArray()) {
                            if (c == character) {
                                correct++;
                            }
                        }
                        
                        System.out.println("correct guess.\n");
    
                    } else {
                        // incorrect guess
                        System.out.println("incorrect guess.\n");
                        guesses = guesses - 1;
                    }


                    guessed.add(c);

                } else {
                    // already guessed that letter
                    System.out.println("already guessed that letter.\n");
                    
                }

                // if not already guessed

            } else {
                System.out.println("invalid input");
            }

            printState();

        }
    }

    @Override
    public void end() {
        System.out.println("Moving into end state.");

        if (correct == word.length()) {
            System.out.println("You won! Congrats :D");
        } else {
            System.out.println("You lost!");
            System.out.println("The word was: " + word);
        }

    }

    public Hangman(int guesses) {

        this.guesses = guesses;

    }




}
