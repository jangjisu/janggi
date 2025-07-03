package com.game.janggi.domain.piece;

import com.game.janggi.domain.piece.move.MovePosition;
import com.game.janggi.domain.team.TeamType;

import java.util.Objects;

import static com.game.janggi.domain.team.TeamType.CHO;

public class Bung extends Piece {
    private Bung(TeamType teamType, MovePosition movePosition) {
        super(teamType, movePosition);
    }

    public static Bung create(TeamType teamType) {
        Objects.requireNonNull(teamType, "TeamType must not be null");

        if (teamType == CHO) {
            throw new IllegalArgumentException("Bung is Han Piece");
        }
        return new Bung(teamType, MovePosition.createBungMove());
    }

    @Override
    public String printPieceName() {
        return "병";
    }
}
