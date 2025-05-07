package com.game.janggi.domain.piece;

import java.util.List;

public class Pieces {
    private final List<Piece> pieceList;

    private Pieces(List<Piece> pieceList) {
        this.pieceList = pieceList;
    }

    public static Pieces create(List<Piece> pieceList) {
        return new Pieces(pieceList);
    }

    public int size() {
        return pieceList.size();
    }
}
