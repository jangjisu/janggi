package com.game.janggi.domain.piece;

import com.game.janggi.domain.team.TeamType;

public class Jol extends Piece {
    protected Jol(TeamType teamType) {
        super(teamType);
    }

    public static Jol create(TeamType teamType) {
        return new Jol(teamType);
    }

    @Override
    public String printPieceName() {
        return "졸";
    }
}
