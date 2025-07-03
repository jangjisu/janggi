package com.game.janggi.domain.piece;

import com.game.janggi.domain.piece.move.MovePosition;
import com.game.janggi.domain.team.TeamType;

public class King extends Piece {
    private King(TeamType teamType, MovePosition movePosition) {
        super(teamType, movePosition);
    }

    public static King create(TeamType teamType) {
        return new King(teamType, MovePosition.createKingMove());
    }

    @Override
    public String printPieceName() {
        return "왕";
    }
}
