package com.game.janggi.domain.piece;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Pieces {
    private final List<Piece> pieceList;

    public static Pieces create(Piece... pieceList) {
        return new Pieces(List.of(pieceList));
    }

    public int size() {
        return pieceList.size();
    }
}
