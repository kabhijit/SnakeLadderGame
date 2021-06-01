package com.assignment.game.board.elements.impl;

import com.assignment.game.board.elements.SpecialElement;
import com.assignment.game.utils.DisplayUtil;

public class GreenSnake extends Snake implements SpecialElement {
    private boolean isProcessed;

    public GreenSnake(int start, int end,
                      boolean isProcessed) {
        super(start, end);
        this.isProcessed = isProcessed;
    }

    @Override
    public void visit() {
        this.isProcessed = true;
        DisplayUtil.displayMessage("GreenSnake processed");
    }

    @Override
    public boolean shouldProcessed() {
        DisplayUtil.displayMessage("GreenSnake processing: " + this.isProcessed);
        return this.isProcessed;
    }
}
