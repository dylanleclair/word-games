package com.circles.wordgames;

import java.util.ArrayList;

public class LightNode {
    
    public LightNode parent;
    public Character value;
    public ArrayList<LightNode> letters;
    public boolean accepting;

    public LightNode (LightNode parent, Character value) {
        this.parent = parent;
        this.value = value;
        letters = new ArrayList<LightNode>();
        accepting = false;
    }

    public LightNode addChild (Character value) {
        LightNode n = new LightNode(this,value);
        letters.add(n);
        return n;
    }

    public boolean hasChild (Character value) {
        for (LightNode letter : letters) {
            if (letter.value == value)
                return true;
        }

        return false;
    }

    public int indexOf (Character letter) throws Exception {
        for (int i = 0; i < letters.size();i++) {
            if (letter == letters.get(i).value) {
                return i;
            }
        }

        throw new NoSuchFieldException();
    }


}
