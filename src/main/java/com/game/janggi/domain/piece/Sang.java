package com.game.janggi.domain.piece;

import com.game.janggi.domain.team.TeamType;

public class Sang extends Piece {
    protected Sang(TeamType teamType) {
        super(teamType);
    }

    public static Sang create(TeamType teamType) {
        return new Sang(teamType);
    }

    @Override
    public String printPieceName() {
        return "상";
    }
}
