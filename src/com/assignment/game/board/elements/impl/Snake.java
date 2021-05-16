package com.assignment.game.board.elements.impl;

import com.assignment.game.board.elements.Element;

import java.util.Objects;

public class Snake extends Element {
    public Snake(int start, int end) {
        super(start, end);
    }

    public int getStart() {
        return super.getStart();
    }

    public int getEnd() {
        return super.getEnd();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Snake)) return false;
        Snake snake = (Snake) o;
        return super.getStart() == snake.getStart();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.getStart());
    }
}
