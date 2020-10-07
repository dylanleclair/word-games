package com.circles.wordgames;

import java.util.ArrayList;

public class Node {
    
    public Node parent;
    public Character value;
    public ArrayList<Node> letters;
    public boolean accepting;

    public Node (Node parent, Character value) {
        this.parent = parent;
        this.value = value;
        letters = new ArrayList<Node>();
        accepting = false;
    }

    public Node addChild (Character value) {
        Node n = new Node(this,value);
        letters.add(n);
        return n;
    }

    public boolean hasChild (Character value) {
        for (Node letter : letters) {
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
