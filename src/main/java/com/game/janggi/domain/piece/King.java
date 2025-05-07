package com.game.janggi.domain.piece;

import com.game.janggi.domain.team.TeamType;

public class King extends Piece {
    protected King(TeamType teamType) {
        super(teamType);
    }

    public static King create(TeamType teamType) {
        return new King(teamType);
    }

    @Override
    public String printPieceName() {
        return "왕";
    }
}
