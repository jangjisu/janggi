package com.game.janggi.domain.team;

import com.game.janggi.domain.formation.FormationType;
import com.game.janggi.domain.piece.Pieces;

public class Han extends Team {
    public Han(Pieces pieces) {
        super(pieces);
    }

    public static Han create(FormationType formationType) {
        return new Han(Pieces.create(formationType, TeamType.HAN));
    }
}
