package com.circles.wordgames;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{

    static final String ANAGRAMSWORDLIST = "collinsscrabble.txt";
    static final String WORDSNDIR = "wordsbylength";
    static final String WORDSETDIR = "wordsets";


    static Scanner scanner = new Scanner(System.in);

    public static void main( String[] args )
    {
        // have user specify which game they want to play
        
        //Hangman lol = new Hangman(5);

        try {
            //WordTree w = new WordTree(ANAGRAMSWORDLIST);
            
            //ArrayList<String> result = w.findPermutations("lagoon");
            //w.generateAnagramsSets(3, 6, 70);

            


            System.out.println("lol");
            Anagrams a = new Anagrams();
            a.runGame();


            /*
                        while (true) {
                String in = scanner.next();
                in.toLowerCase().trim();

                System.out.println(w.findWord(in));

            }
            */
            
            
            //w.generateAnagramsSets(3, 6, 70);
        } catch (Exception e) {
            e.printStackTrace();
        }
        




        scanner.close();

    }
}
