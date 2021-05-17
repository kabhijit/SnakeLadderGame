package com.assignment.game.utils;

import com.assignment.game.dice.Dice;
import com.assignment.game.dice.DiceType;
import com.assignment.game.dice.impl.CrookedDice;
import com.assignment.game.dice.impl.NormalDice;

public class DiceFactory {
    private DiceFactory() {}

    public static Dice getDiceOfType(final String type) throws IllegalArgumentException {
        Dice dice;
        switch (DiceType.valueOf(type)) {
            case CROOKED:
                dice = new CrookedDice();
                break;
            default:
                dice = new NormalDice();
        }
        return dice;
    }
}
