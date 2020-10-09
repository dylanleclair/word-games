package com.circles.wordgames;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    @Test
    public void testPermutations () {

        String root = "catastrophic";

        List<String> testWords = Arrays.asList("rat", "rats", "cats", "cat", root, "catastrophe", "start", "flat", "fire", "bottle");

        LightWordTree light = new LightWordTree(testWords);

        List<String> results = WordLists.findPermutations(light, root, new Parameters(3,root.length(), 0));

        boolean test = true;

        ArrayList<Character> letters = new ArrayList<Character>();

        for (Character c : root.toCharArray()) {
            letters.add(c);
        }

        for (String result : results) {
            for (Character c : result.toCharArray()) {
                if (!letters.contains(c)) {
                    test = false;
                }
            }
        }

        assertTrue(test);

        assertTrue(results.contains("cat"));
        assertTrue(results.contains("cats"));
        assertTrue(results.contains("rat"));
        assertTrue(results.contains("rats"));
        assertTrue(results.contains("start"));

        assertFalse(results.contains("rope"));
        assertFalse(results.contains("star"));



    }


}
