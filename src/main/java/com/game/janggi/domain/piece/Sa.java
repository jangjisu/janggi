package com.game.janggi.domain.piece;

import com.game.janggi.domain.piece.move.MovePosition;
import com.game.janggi.domain.team.TeamType;

import java.util.Objects;

public class Sa extends Piece {
    private Sa(TeamType teamType, MovePosition movePosition) {
        super(teamType, movePosition);
    }

    public static Sa create(TeamType teamType) {
        Objects.requireNonNull(teamType, "TeamType must not be null");
        return new Sa(teamType, MovePosition.createSaMove());
    }

    @Override
    public String printPieceName() {
        return "사";
    }
}
