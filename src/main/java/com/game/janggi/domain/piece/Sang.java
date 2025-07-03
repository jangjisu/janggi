package com.game.janggi.domain.piece;

import com.game.janggi.domain.piece.move.MovePosition;
import com.game.janggi.domain.team.TeamType;

public class Sang extends Piece {
    private Sang(TeamType teamType, MovePosition movePosition) {
        super(teamType, movePosition);
    }

    public static Sang create(TeamType teamType) {
        return new Sang(teamType, MovePosition.createSangMove());
    }

    @Override
    public String printPieceName() {
        return "상";
    }
}
