package com.circles.wordgames;

public enum States {
    

    // use switch
    gameselect, // select a game to play
    setup, // find a word list / set to use for inputted game
    play, // repeatedly get input
    end, // end the game (out of time or player wins)
    cleanup
}
