package com.assignment.game.board;

import com.assignment.game.dice.Dice;

public class Player {
    final Character marker;
    private int currentPos;
    private int lastMove;

    private Player() {
        this.marker = '\0';
        this.currentPos = 1;
    }

    public Player(Character marker) {
        this.marker = marker;
        this.currentPos = 1;
    }

    public Character getMarker() {
        return this.marker;
    }

    public int getCurrentPosition() {
        return currentPos;
    }

    public void setCurrentPosition(int position) {
        this.currentPos = position;
    }

    public int getLastMove() {
        return this.lastMove;
    }

    public int play(final Dice dice) {
        this.lastMove = dice.roll();
        return lastMove;
    }
}
