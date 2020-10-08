package com.circles.wordgames;

import java.util.HashMap;

public class FastNode extends Node  implements INode {

    protected HashMap<Character, FastNode> letters;

    public FastNode (FastNode parent, char value)   {
        super(parent,value);
        letters = new HashMap<Character, FastNode>();
    }

    @Override
    public FastNode addChild (Character value) {
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

    @Override
    public FastNode getChild (Character value) {
        
        return letters.get(Character.valueOf(value));
    }


    
}
