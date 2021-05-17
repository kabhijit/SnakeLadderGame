package com.assignment.game.dice;

public enum DiceType {
    DICE_TYPE_CROOKED("C"),
    DICE_TYPE_NORMAL("N");

    private final String diceTypeText;

    DiceType(final String text) {
        this.diceTypeText = text;
    }

    @Override
    public String toString() {
        return this.diceTypeText;
    }
}
