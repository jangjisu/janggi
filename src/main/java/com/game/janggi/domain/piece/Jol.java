package com.game.janggi.domain.piece;

import com.game.janggi.domain.piece.move.MovePosition;
import com.game.janggi.domain.team.TeamType;
import com.game.janggi.exception.NeedStopException;

import static com.game.janggi.domain.team.TeamType.HAN;

public class Jol extends Piece {
    private Jol(TeamType teamType, MovePosition movePosition) {
        super(teamType, movePosition);
    }

    public static Jol create(TeamType teamType) {
        if (teamType == HAN) {
            throw new NeedStopException("졸은 초나라의 기물입니다");
        }
        return new Jol(teamType, MovePosition.createJolMove());
    }

    @Override
    public String printPieceName() {
        return "졸";
    }
}
