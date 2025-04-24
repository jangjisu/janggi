package com.game.janggi.domain.piece.layout;

import com.game.janggi.domain.formation.FormationType;
import com.game.janggi.domain.piece.*;
import com.game.janggi.domain.team.TeamType;

import java.util.List;

public class HanDefaultPieceLayout extends DefaultPieceLayout {
    public HanDefaultPieceLayout(FormationType formationType) {
        super(formationType);
    }

    @Override
    protected List<Piece> createFixedPieces() {
        Cha leftCha = Cha.create(9, 0, TeamType.HAN);
        Cha rightCha = Cha.create(9, 8, TeamType.HAN);
        Sa leftSa = Sa.create(9, 3, TeamType.HAN);
        Sa rightSa = Sa.create(9, 5, TeamType.HAN);
        King king = King.create(8, 4, TeamType.HAN);
        Po leftPo = Po.create(7, 1, TeamType.HAN);
        Po rightPo = Po.create(7, 7, TeamType.HAN);
        Bung bung1 = Bung.create(6, 0, TeamType.HAN);
        Bung bung2 = Bung.create(6, 2, TeamType.HAN);
        Bung bung3 = Bung.create(6, 4, TeamType.HAN);
        Bung bung4 = Bung.create(6, 6, TeamType.HAN);
        Bung bung5 = Bung.create(6, 8, TeamType.HAN);

        return List.of(leftCha, rightCha, leftSa, rightSa, king, leftPo, rightPo, bung1, bung2, bung3, bung4, bung5);
    }

    @Override
    protected List<Piece> createFluidPieces() {
        return createFluidPiecesOnRow(this.formationType, 9, TeamType.HAN);
    }
}
