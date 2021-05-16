package com.assignment.game.dice;

import com.assignment.game.dice.impl.CrookedDice;
import com.assignment.game.dice.impl.NormalDice;
import org.junit.Assert;
import org.junit.Test;

public class DiceTest {

    @Test
    public void test_rollNormalDice_shouldNotExceedSix() {
        Dice normalDice = new NormalDice();
        Assert.assertTrue(normalDice.roll() < 6);
    }

    @Test
    public void test_rollCrookedDice_shouldNotExceedSix() {
        Dice crookedDice = new CrookedDice();
        Assert.assertTrue(crookedDice.roll() < 6);
    }

    @Test
    public void test_rollCrookedDice_shouldBeEven() {
        Dice crookedDice = new CrookedDice();
        Assert.assertEquals(0, crookedDice.roll() % 2);
    }
}
