package com.game.janggi.domain.piece;

import com.game.janggi.domain.piece.move.MovePosition;
import com.game.janggi.domain.team.TeamType;
import com.game.janggi.exception.NeedStopException;

import static com.game.janggi.domain.team.TeamType.CHO;

public class Bung extends Piece {
    private Bung(TeamType teamType, MovePosition movePosition) {
        super(teamType, movePosition);
    }

    public static Bung create(TeamType teamType) {
        if (teamType == CHO) {
            throw new NeedStopException("병은 한나라의 기물입니다");
        }
        return new Bung(teamType, MovePosition.createBungMove());
    }

    @Override
    public String printPieceName() {
        return "병";
    }
}
