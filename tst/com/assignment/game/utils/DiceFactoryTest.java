package com.assignment.game.utils;

import com.assignment.game.dice.Dice;
import com.assignment.game.dice.impl.CrookedDice;
import com.assignment.game.dice.impl.NormalDice;
import junit.framework.TestCase;
import org.junit.Assert;

public class DiceFactoryTest extends TestCase {

    public void test_getDiceOfType_shouldGiveNormalDiceWhenNormalCrookedValue() {
        Dice test = DiceFactory.getDiceOfType("NORMAL");
        Assert.assertTrue(test instanceof NormalDice);
    }

    public void test_getDiceOfType_shouldGiveCrookedDiceWhenPassedCrookedValue() {
        Dice test = DiceFactory.getDiceOfType("CROOKED");
        Assert.assertTrue(test instanceof CrookedDice);
    }
}