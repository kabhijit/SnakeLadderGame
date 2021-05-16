package com.assignment.game.board.elements.impl;

import com.assignment.game.board.elements.Element;

import java.util.Objects;

public class Ladder extends Element {
    public Ladder(int start, int end) {
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
        if (!(o instanceof Ladder)) return false;
        Ladder ladder = (Ladder) o;
        return super.getStart() == ladder.getStart();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.getStart());
    }
}
