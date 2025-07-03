package com.game.janggi.domain.piece;

import com.game.janggi.domain.piece.move.MovePosition;
import com.game.janggi.domain.team.TeamType;

import java.util.Objects;

public class Ma extends Piece {
    private Ma(TeamType teamType, MovePosition movePosition) {
        super(teamType, movePosition);
    }

    public static Ma create(TeamType teamType) {
        Objects.requireNonNull(teamType, "TeamType must not be null");
        return new Ma(teamType, MovePosition.createMaMove());
    }

    @Override
    public String printPieceName() {
        return "마";
    }
}
