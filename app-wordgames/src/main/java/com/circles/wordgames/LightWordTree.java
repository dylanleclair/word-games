package com.circles.wordgames;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LightWordTree extends WordTree {



    public LightWordTree () throws Exception {

        InputStream is = getClass().getClassLoader().getResourceAsStream(App.ANAGRAMSWORDLIST);

        Scanner scanner = new Scanner (is);
        ArrayList<String> wordsFromFile = new ArrayList<String>();

        root = new LightNode(null, '0');

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            line = line.toLowerCase().trim();
            addWord(line);
            wordsFromFile.add(line);
        }

        wordsLengthN(wordsFromFile);
        
        scanner.close();
    }

    // to be used when running an anagrams game
    public LightWordTree (List<String> words) {;

        root = new LightNode(null, '0');
        for (String word : words) {
            word.toLowerCase().trim();
            addWord(word);
        }
    
    }


}
