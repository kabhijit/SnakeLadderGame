package com.assignment.game.board.strategy;

import com.assignment.game.board.Player;

public interface GameStrategy {
    public Player getNextPlayer();
    public Player getCurrentPlayer();
    public int play();
}
