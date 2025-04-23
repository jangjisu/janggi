package com.game.janggi.domain.team;

import com.game.janggi.domain.formation.FormationType;
import com.game.janggi.domain.piece.Pieces;

public class Cho extends Team {
    public Cho(Pieces pieces) {
        super(pieces);
    }

    public static Cho create(FormationType formationType) {
        return new Cho(Pieces.create(formationType, TeamType.CHO));
    }
}
