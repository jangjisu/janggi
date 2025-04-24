package com.game.janggi.domain.piece;

import java.util.List;

public class Pieces {
    private List<Piece> pieces;

    private Pieces(List<Piece> pieces) {
        this.pieces = pieces;
    }

    public static Pieces create(List<Piece> pieces) {
        return new Pieces(pieces);
    }

    public int getSize() {
        return pieces.size();
    }
}
