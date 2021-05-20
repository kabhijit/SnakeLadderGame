package com.assignment.game.board;

import com.assignment.game.dice.Dice;

public class Player {
    final Dice dice;
    final Character marker;
    private int currentPos;
    private int lastMove;

    private Player() {
        this.dice = null;
        this.marker = '\0';
        this.currentPos = 0;
    }

    public Player(Dice dice, Character marker) {
        this.dice = dice;
        this.marker = marker;
        this.currentPos = 0;
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

    public int play() {
        this.lastMove = this.dice.roll();
        return lastMove;
    }
}
