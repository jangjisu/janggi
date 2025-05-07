package com.game.janggi.domain.piece;

import com.game.janggi.domain.team.TeamType;

public class Cha extends Piece {
    protected Cha(TeamType teamType) {
        super(teamType);
    }

    public static Cha create(TeamType teamType) {
        return new Cha(teamType);
    }

    @Override
    public String printPieceName() {
        return "차";
    }
}
