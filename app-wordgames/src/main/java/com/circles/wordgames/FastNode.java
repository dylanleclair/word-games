package com.circles.wordgames;

import java.util.HashMap;

public class FastNode {


    public FastNode parent;
    public char value;
    public HashMap<Character, FastNode> letters;
    public boolean accepting;

    public FastNode (FastNode parent, char value) {
        this.parent = parent;
        this.value = value;
        letters = new HashMap<Character, FastNode>();
        accepting = false;
    }

    public FastNode addChild (char value) {
        FastNode n = new FastNode(this,value);
        letters.put(value,n);
        return n;
    }

    public boolean hasChild (char value) {
        if (letters.containsKey(Character.valueOf(value))) {
            return true;
        } else {
            return false;
        }
    }

    public FastNode getChild (char letter) {
        
        return letters.get(Character.valueOf(value));
    }


    
}
