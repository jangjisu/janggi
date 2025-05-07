package com.game.janggi.domain.piece;

import com.game.janggi.domain.team.TeamType;

public class Sa extends Piece {
    protected Sa(TeamType teamType) {
        super(teamType);
    }

    public static Sa create(TeamType teamType) {
        return new Sa(teamType);
    }

    @Override
    public String printPieceName() {
        return "사";
    }
}
