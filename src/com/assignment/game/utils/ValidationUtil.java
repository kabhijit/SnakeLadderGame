package com.assignment.game.utils;

public class ValidationUtil {
    private ValidationUtil() {}

    /**
     * Method to check if start of the snake position is greater than end
     * and one more constraint is snake head can't be at end tile
     *
     * @param start point of snake / snake head
     * @param end point of snake / enake tail
     * @return start is greater than end
     */
    public static boolean isValidSnake(final int start,
                                       final int end,
                                       final int size) {

        return start < size
                && end < size
                && start > end;
    }

    /**
     * Method to check if start of the ladder position is greater than end
     *
     * @param start point of ladder / ladder head
     * @param end point of ladder / ladder end
     * @return start is lesser than end
     */
    public static boolean isValidLadder(final int start,
                                        final int end,
                                        final int size) {

        return end <= size
                && start < end;
    }
}
