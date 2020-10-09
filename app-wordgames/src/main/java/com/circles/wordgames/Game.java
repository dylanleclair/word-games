package com.circles.wordgames;
import java.util.Scanner;

public abstract class Game {
    
    
    interface IGame {
        public void setup();
        public void play(Scanner s);
        public void end();
        public void printState();
    }
    
    public String getInput(Scanner s) {

        String output;

        output = s.next();

        output = output.toLowerCase().trim();

        return output;

    }
    


}
