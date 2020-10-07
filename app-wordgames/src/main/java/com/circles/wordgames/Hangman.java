package com.circles.wordgames;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Hangman {

    private String word;
    private final String WORDSPATH = "commonwords.txt";
    private States gamestate;
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


    private String getInput(Scanner s) {

        
        String output;

        output = App.scanner.next();

        output = output.toLowerCase().trim();

        return output;


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

    public void runGame () {


        Scanner s = App.scanner;
        
        while (gamestate != States.cleanup) {


            switch (gamestate) {
                case setup:

                    System.out.println("Moving into setup state.");
                
                    try {
                        this.word = chooseWord();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    gamestate = States.play;
    
                    break;
                case play:
    
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
    
                    gamestate = States.end;
    
                    
                    break;
    
                case end:
    
                    System.out.println("Moving into end state.");

                    if (correct == word.length()) {
                        System.out.println("You won! Congrats :D");
                    } else {
                        System.out.println("You lost!");
                        System.out.println("The word was: " + word);
                    }

                    gamestate = States.cleanup;
    
                    break;
    
                default:
                    break;
    
            }



        }

    }


    public void printState() {
        System.out.println(summarizeState());
    }

    public String summarizeState() {
        String output = "";
        output += "Current state: " + wordWithBlanks() +"\n";
        output += "Incorrect guesses remaining: " + guesses + "\n";
        return output;
    }

    public Hangman(int guesses) {

        gamestate = States.setup;

        correct = 0;

        this.guesses = guesses;



    }



}
