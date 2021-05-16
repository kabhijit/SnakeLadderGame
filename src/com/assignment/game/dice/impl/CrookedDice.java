package com.assignment.game.dice.impl;

import com.assignment.game.dice.Dice;

public class CrookedDice implements Dice {

    @Override
    public int roll() {
        return 2 * (int) Math.round(3 * Math.random());
    }
}
