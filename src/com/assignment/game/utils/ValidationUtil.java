package com.assignment.game.utils;

import java.awt.geom.Point2D;

public class ValidationUtil {
    private ValidationUtil() {}

    /**
     * Method to check if start of the snake position is greater than end
     *
     * @param start point of snake / snake head
     * @param end point of snake / enake tail
     * @return start is greater than end
     */
    public static boolean isValidSnake(final Point2D start,
                                       final Point2D end,
                                       final int size) {
        // get (x, y) for source and validate its +ve integers
        int srcX = (int) start.getX();
        int srcY = (int) start.getY();
        // get (x, y) for destination and validate its +ve integers
        int destX = (int) end.getX();
        int destY = (int) end.getY();

        return srcX < size
                && destX < size
                && srcY < size
                && destY < size
                && srcX > destX;
    }

    /**
     * Method to check if start of the ladder position is greater than end
     *
     * @param start point of ladder / ladder head
     * @param end point of ladder / ladder end
     * @return start is lesser than end
     */
    public static boolean isValidLadder(final Point2D start,
                                        final Point2D end,
                                        final int size) {
        // get (x, y) for source and validate its +ve integers
        int srcX = (int) start.getX();
        int srcY = (int) start.getY();
        // get (x, y) for destination and validate its +ve integers
        int destX = (int) end.getX();
        int destY = (int) end.getY();

        return destX < size
                && srcY < size
                && destY < size
                && srcX < destX;
    }
}
