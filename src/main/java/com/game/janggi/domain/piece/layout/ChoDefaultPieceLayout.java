package com.game.janggi.domain.piece.layout;

import com.game.janggi.domain.formation.FormationType;
import com.game.janggi.domain.piece.*;
import com.game.janggi.domain.team.TeamType;

import java.util.List;

public class ChoDefaultPieceLayout extends DefaultPieceLayout {
    public ChoDefaultPieceLayout(FormationType formationType) {
        super(formationType);
    }

    @Override
    public List<Piece> createFixedPieces() {
        Cha leftCha = Cha.create(0, 0, TeamType.CHO);
        Cha rightCha = Cha.create(0, 8, TeamType.CHO);
        Sa leftSa = Sa.create(0, 3, TeamType.CHO);
        Sa rightSa = Sa.create(0, 5, TeamType.CHO);
        King king = King.create(1, 4, TeamType.CHO);
        Po leftPo = Po.create(2, 1, TeamType.CHO);
        Po rightPo = Po.create(2, 7, TeamType.CHO);
        Jol jol1 = Jol.create(3, 0, TeamType.CHO);
        Jol jol2 = Jol.create(3, 2, TeamType.CHO);
        Jol jol3 = Jol.create(3, 4, TeamType.CHO);
        Jol jol4 = Jol.create(3, 6, TeamType.CHO);
        Jol jol5 = Jol.create(3, 8, TeamType.CHO);

        return List.of(leftCha, rightCha, leftSa, rightSa, king, leftPo, rightPo, jol1, jol2, jol3, jol4, jol5);
    }

    @Override
    protected List<Piece> createFluidPieces() {
        return createFluidPiecesOnRow(this.formationType, 0, TeamType.CHO);
    }
}
