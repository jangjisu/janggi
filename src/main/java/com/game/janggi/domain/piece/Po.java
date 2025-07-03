package com.game.janggi.domain.piece;

import com.game.janggi.domain.piece.move.MovePosition;
import com.game.janggi.domain.team.TeamType;

public class Po extends Piece {
    private Po(TeamType teamType, MovePosition movePosition) {
        super(teamType, movePosition);
    }

    public static Po create(TeamType teamType) {
        return new Po(teamType, MovePosition.createPoMove());
    }

    @Override
    public String printPieceName() {
        return "포";
    }
}
