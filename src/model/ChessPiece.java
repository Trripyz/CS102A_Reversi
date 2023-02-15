package model;

import java.awt.*;

public enum ChessPiece {
    BLACK(Color.BLACK), WHITE(Color.WHITE),RED(Color.RED),LIGHT_GRAY(Color.LIGHT_GRAY);

    private final Color color;

    ChessPiece(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
