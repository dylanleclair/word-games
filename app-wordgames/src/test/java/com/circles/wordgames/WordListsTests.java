package com.circles.wordgames;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import com.circles.wordgames.WordLists.Parameters;

import org.junit.Test;


public class WordListsTests {
    

    @Test
    public void testAnagramsListSize() {
        ArrayList<String> output = WordLists.generateAnagramsList(new Parameters(3, 6, 70));

        assertTrue(output.size() >= 70);

    }

    @Test
    public void testAnagramsListWordSize() {
        ArrayList<String> output = WordLists.generateAnagramsList(new Parameters(3, 6, 40));
 
        for (String s : output) {
            assertTrue(s.length() >= 3 && s.length() <= 6);
        }

    }


}
