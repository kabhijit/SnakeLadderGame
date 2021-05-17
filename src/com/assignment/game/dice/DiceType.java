package com.assignment.game.dice;

public enum DiceType {
    CROOKED("CROOKED"),
    NORMAL("NORMAL");

    private final String diceTypeText;

    DiceType(final String text) {
        this.diceTypeText = text;
    }

    @Override
    public String toString() {
        return this.diceTypeText;
    }
}
