import java.util.Scanner;

public abstract class Game {
    
    
    interface IGame {
        public void setup();
        public void play();
        public void end();
        public void cleanup();
    }

    
    
    private String getInput(Scanner s) {

        String output;

        output = s.next();

        output = output.toLowerCase().trim();

        return output;

    }
    


}
