package com.game.janggi.domain.piece;

import com.game.janggi.domain.team.TeamType;

public class Bung extends Piece {
    protected Bung(TeamType teamType) {
        super(teamType);
    }

    public static Bung create(TeamType teamType) {
        return new Bung(teamType);
    }

    @Override
    public String printPieceName() {
        return "병";
    }
}
