package com.game.janggi.domain.piece;

import com.game.janggi.domain.piece.move.MovePosition;
import com.game.janggi.domain.team.TeamType;

import java.util.Objects;

public class Cha extends Piece {
    private Cha(TeamType teamType, MovePosition movePosition) {
        super(teamType, movePosition);
    }

    public static Cha create(TeamType teamType) {
        Objects.requireNonNull(teamType, "TeamType must not be null");
        return new Cha(teamType, MovePosition.createChaMove());
    }

    @Override
    public String printPieceName() {
        return "차";
    }
}
