package com.circles.wordgames;

import java.util.List;
import java.util.Arrays;
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

        try {
            LightWordTree light = new LightWordTree(words);    

            for (String word : words) {
                assertTrue(light.findWord(word));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    @Test
    public void testFastTree() {

        List<String> words = Arrays.asList("hello", "a", "bee");

        try {
            FastWordTree fast = new FastWordTree(words);    

            for (String word : words) {
                assertTrue(fast.findWord(word));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }



    }

}
