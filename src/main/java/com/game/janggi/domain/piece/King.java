package com.game.janggi.domain.piece;

import com.game.janggi.domain.piece.move.MovePosition;
import com.game.janggi.domain.team.TeamType;

import java.util.Objects;

public class King extends Piece {
    private King(TeamType teamType, MovePosition movePosition) {
        super(teamType, movePosition);
    }

    public static King create(TeamType teamType) {
        Objects.requireNonNull(teamType, "TeamType must not be null");
        return new King(teamType, MovePosition.createKingMove());
    }

    @Override
    public String printPieceName() {
        return "왕";
    }
}
