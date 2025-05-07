package com.game.janggi.domain.piece;

import com.game.janggi.domain.team.TeamType;

public class Po extends Piece {
    protected Po(TeamType teamType) {
        super(teamType);
    }

    public static Po create(TeamType teamType) {
        return new Po(teamType);
    }

    @Override
    public String printPieceName() {
        return "포";
    }
}
