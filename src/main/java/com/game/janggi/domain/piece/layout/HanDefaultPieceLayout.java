package com.game.janggi.domain.piece.layout;

import com.game.janggi.domain.formation.FormationType;
import com.game.janggi.domain.piece.*;
import com.game.janggi.domain.piece.position.PiecePosition;
import com.game.janggi.domain.team.TeamType;

import java.util.Map;

public class HanDefaultPieceLayout extends DefaultPieceLayout {
    public HanDefaultPieceLayout(FormationType formationType) {
        super(formationType);
    }

    @Override
    protected Map<PiecePosition, Piece> createFixedPieces() {
        PiecePosition leftChaPosition = new PiecePosition(0, 0);
        Cha leftCha = Cha.create(TeamType.HAN);

        PiecePosition rightChaPosition = new PiecePosition(8, 0);
        Cha rightCha = Cha.create(TeamType.HAN);

        PiecePosition leftSaPosition = new PiecePosition(3, 0);
        Sa leftSa = Sa.create(TeamType.HAN);

        PiecePosition rightSaPosition = new PiecePosition(5, 0);
        Sa rightSa = Sa.create(TeamType.HAN);

        PiecePosition kingPosition = new PiecePosition(4, 1);
        King king = King.create(TeamType.HAN);

        PiecePosition leftPoPosition = new PiecePosition(1, 2);
        Po leftPo = Po.create(TeamType.HAN);

        PiecePosition rightPoPosition = new PiecePosition(7, 2);
        Po rightPo = Po.create(TeamType.HAN);

        PiecePosition bung1Position = new PiecePosition(0, 3);
        Bung bung1 = Bung.create(TeamType.HAN);

        PiecePosition bung2Position = new PiecePosition(2, 3);
        Bung bung2 = Bung.create(TeamType.HAN);

        PiecePosition bung3Position = new PiecePosition(4, 3);
        Bung bung3 = Bung.create(TeamType.HAN);

        PiecePosition bung4Position = new PiecePosition(6, 3);
        Bung bung4 = Bung.create(TeamType.HAN);

        PiecePosition bung5Position = new PiecePosition(8, 3);
        Bung bung5 = Bung.create(TeamType.HAN);

        return Map.ofEntries(
                Map.entry(leftChaPosition, leftCha),
                Map.entry(rightChaPosition, rightCha),
                Map.entry(leftSaPosition, leftSa),
                Map.entry(rightSaPosition, rightSa),
                Map.entry(kingPosition, king),
                Map.entry(leftPoPosition, leftPo),
                Map.entry(rightPoPosition, rightPo),
                Map.entry(bung1Position, bung1),
                Map.entry(bung2Position, bung2),
                Map.entry(bung3Position, bung3),
                Map.entry(bung4Position, bung4),
                Map.entry(bung5Position, bung5)
        );
    }

    @Override
    protected Map<PiecePosition, Piece> createFluidPieces() {
        return createFluidPiecesOnRow(this.formationType, 0, TeamType.HAN);
    }
}
