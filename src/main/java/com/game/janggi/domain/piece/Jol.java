package com.game.janggi.domain.piece;

import com.game.janggi.domain.piece.move.MovePosition;
import com.game.janggi.domain.team.TeamType;
import com.game.janggi.exception.NeedStopException;

import java.util.Objects;

import static com.game.janggi.domain.team.TeamType.HAN;

public class Jol extends Piece {
    private Jol(TeamType teamType, MovePosition movePosition) {
        super(teamType, movePosition);
    }

    public static Jol create(TeamType teamType) {
        Objects.requireNonNull(teamType, "TeamType must not be null");

        if (teamType == HAN) {
            throw new IllegalArgumentException("Jol is Cho Piece");
        }
        return new Jol(teamType, MovePosition.createJolMove());
    }

    @Override
    public String printPieceName() {
        return "졸";
    }
}
