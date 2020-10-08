package com.circles.wordgames;

import java.util.List;
import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class TreeTests {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void testLightTree() {

        List<String> words = Arrays.asList("hello", "a", "bee");
        List<String> fakeWords = Arrays.asList("h", "", "be");
        try {
            IWordTree light = new LightWordTree(words);    

            for (String word : words) {
                assertTrue(light.findWord(word));
            }

            for (String word : fakeWords) {
                assertFalse(light.findWord(word));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    @Test
    public void testFastTree() {

        List<String> words = Arrays.asList("hello", "a", "bee");
        List<String> fakeWords = Arrays.asList("h", "", "be");

        try {
            IWordTree fast = new FastWordTree(words);    

            for (String word : words) {
                assertTrue(fast.findWord(word));
            }


            for (String word : fakeWords) {
                assertFalse(fast.findWord(word));
            }


        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    @Test
    public void TestTreesTogether() {

        List<String> words = Arrays.asList("hello", "a", "bee");
        List<String> fakeWords = Arrays.asList("h", "", "be");

        //WordTree fast = new FastWordTree(words);
        WordTree light = new LightWordTree(words);
        WordTree fast = new FastWordTree(words);


        List<IWordTree> trees = Arrays.asList(fast,light);

        for (IWordTree tree : trees) {

            for (String word : words) {
                assertTrue(tree.findWord(word));
            }

            for (String word : fakeWords) {
                assertFalse(tree.findWord(word));
            }
        }



    }


}
