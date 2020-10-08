package com.circles.wordgames;

import java.util.HashMap;

public class FastNode extends Node {

    protected HashMap<Character, FastNode> letters;

    public FastNode (FastNode parent, char value)   {
        super(parent,value);
        letters = new HashMap<Character, FastNode>();
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
