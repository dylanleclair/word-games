package com.circles.wordgames;

public class Node {
    
    protected Node parent;
    protected Character value;
    protected boolean accepting;

    public Node () {

    }

    public Node (Node parent, Character value) {
        this.parent = parent;
        this.value = value;
        accepting = false;
    }


}
