package com.circles.wordgames;

public interface INode {
    

    void setParent (INode parent);
    INode getParent ();

    void setAccepting (boolean accepting);
    boolean getAccepting ();

    void setValue (Character value);
    Character getValue ();

    INode addChild(Character value);
    INode getChild(Character value);


}
