package com.assignment.game.board.strategy.impl;

import com.assignment.game.board.Player;
import com.assignment.game.board.strategy.GameStrategy;
import com.assignment.game.constants.CommonConstants;
import com.assignment.game.dice.Dice;
import com.assignment.game.dice.DiceType;
import com.assignment.game.utils.DiceFactory;

import java.util.List;

public class SinglePlayerGameStrategy implements GameStrategy {
    private final Player player;
    private final Dice dice;

    public SinglePlayerGameStrategy() {
        this.player = new Player(CommonConstants.SNAKE_LADDER_GAME_DEFAULT_PLAYER_MARKER);
        this.dice = DiceFactory.getDiceOfType(DiceType.NORMAL.toString());
    }

    public SinglePlayerGameStrategy(final Player player, final Dice dice) {
        if (player != null) {
            this.player = player;
            this.dice = dice;
        } else {
            this.player = new Player(CommonConstants.SNAKE_LADDER_GAME_DEFAULT_PLAYER_MARKER);
            this.dice = DiceFactory.getDiceOfType(DiceType.NORMAL.toString());
        }
    }

    @Override
    public Player getNextPlayer() {
        return this.player;
    }

    @Override
    public Player getCurrentPlayer() {
        return this.player;
    }

    @Override
    public int play() {
        return this.getCurrentPlayer().play(this.dice);
    }
}
