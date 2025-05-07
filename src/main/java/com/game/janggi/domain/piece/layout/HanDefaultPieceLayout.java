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
        PiecePosition leftChaPosition = new PiecePosition(9, 0);
        Cha leftCha = Cha.create(TeamType.HAN);

        PiecePosition rightChaPosition = new PiecePosition(9, 8);
        Cha rightCha = Cha.create(TeamType.HAN);

        PiecePosition leftSaPosition = new PiecePosition(9, 3);
        Sa leftSa = Sa.create(TeamType.HAN);

        PiecePosition rightSaPosition = new PiecePosition(9, 5);
        Sa rightSa = Sa.create(TeamType.HAN);

        PiecePosition kingPosition = new PiecePosition(8, 4);
        King king = King.create(TeamType.HAN);

        PiecePosition leftPoPosition = new PiecePosition(7, 1);
        Po leftPo = Po.create(TeamType.HAN);

        PiecePosition rightPoPosition = new PiecePosition(7, 7);
        Po rightPo = Po.create(TeamType.HAN);

        PiecePosition bung1Position = new PiecePosition(6, 0);
        Bung bung1 = Bung.create(TeamType.HAN);

        PiecePosition bung2Position = new PiecePosition(6, 2);
        Bung bung2 = Bung.create(TeamType.HAN);

        PiecePosition bung3Position = new PiecePosition(6, 4);
        Bung bung3 = Bung.create(TeamType.HAN);

        PiecePosition bung4Position = new PiecePosition(6, 6);
        Bung bung4 = Bung.create(TeamType.HAN);

        PiecePosition bung5Position = new PiecePosition(6, 8);
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
        return createFluidPiecesOnRow(this.formationType, 9, TeamType.HAN);
    }
}
