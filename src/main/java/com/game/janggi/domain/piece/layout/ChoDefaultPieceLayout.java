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
        Bung bung1 = Bung.create(3, 0, TeamType.CHO);
        Bung bung2 = Bung.create(3, 2, TeamType.CHO);
        Bung bung3 = Bung.create(3, 4, TeamType.CHO);
        Bung bung4 = Bung.create(3, 6, TeamType.CHO);
        Bung bung5 = Bung.create(3, 8, TeamType.CHO);

        return List.of(leftCha, rightCha, leftSa, rightSa, king, leftPo, rightPo, bung1, bung2, bung3, bung4, bung5);
    }

    @Override
    protected List<Piece> createFluidPieces() {
        return createFluidPiecesOnRow(this.formationType, 0, TeamType.CHO);
    }
}
