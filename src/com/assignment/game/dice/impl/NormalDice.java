package com.assignment.game.dice.impl;

import com.assignment.game.dice.Dice;

public class NormalDice implements Dice {

    @Override
    public int roll() {
        return (int) Math.round(5 * Math.random()) + 1;
    }
}
