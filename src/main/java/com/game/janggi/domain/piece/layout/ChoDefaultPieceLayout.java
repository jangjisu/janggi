package com.game.janggi.domain.piece.layout;

import com.game.janggi.domain.formation.FormationType;
import com.game.janggi.domain.piece.*;
import com.game.janggi.domain.piece.position.PiecePosition;
import com.game.janggi.domain.team.TeamType;

import java.util.Map;

public class ChoDefaultPieceLayout extends DefaultPieceLayout {
    public ChoDefaultPieceLayout(FormationType formationType) {
        super(formationType);
    }

    @Override
    protected Map<PiecePosition, Piece> createFixedPieces() {
        PiecePosition leftChaPosition = PiecePosition.create(0, 9);
        Cha leftCha = Cha.create(TeamType.CHO);

        PiecePosition rightChaPosition = PiecePosition.create(8, 9);
        Cha rightCha = Cha.create(TeamType.CHO);

        PiecePosition leftSaPosition = PiecePosition.create(3, 9);
        Sa leftSa = Sa.create(TeamType.CHO);

        PiecePosition rightSaPosition = PiecePosition.create(5, 9);
        Sa rightSa = Sa.create(TeamType.CHO);

        PiecePosition kingPosition = PiecePosition.create(4, 8);
        King king = King.create(TeamType.CHO);

        PiecePosition leftPoPosition = PiecePosition.create(1, 7);
        Po leftPo = Po.create(TeamType.CHO);

        PiecePosition rightPoPosition = PiecePosition.create(7, 7);
        Po rightPo = Po.create(TeamType.CHO);

        PiecePosition jol1Position = PiecePosition.create(0, 6);
        Jol jol1 = Jol.create(TeamType.CHO);

        PiecePosition jol2Position = PiecePosition.create(2, 6);
        Jol jol2 = Jol.create(TeamType.CHO);

        PiecePosition jol3Position = PiecePosition.create(4, 6);
        Jol jol3 = Jol.create(TeamType.CHO);

        PiecePosition jol4Position = PiecePosition.create(6, 6);
        Jol jol4 = Jol.create(TeamType.CHO);

        PiecePosition jol5Position = PiecePosition.create(8, 6);
        Jol jol5 = Jol.create(TeamType.CHO);

        return Map.ofEntries(
                Map.entry(leftChaPosition, leftCha),
                Map.entry(rightChaPosition, rightCha),
                Map.entry(leftSaPosition, leftSa),
                Map.entry(rightSaPosition, rightSa),
                Map.entry(kingPosition, king),
                Map.entry(leftPoPosition, leftPo),
                Map.entry(rightPoPosition, rightPo),
                Map.entry(jol1Position, jol1),
                Map.entry(jol2Position, jol2),
                Map.entry(jol3Position, jol3),
                Map.entry(jol4Position, jol4),
                Map.entry(jol5Position, jol5)
        );
    }

    @Override
    protected Map<PiecePosition, Piece> createFluidPieces() {
        return createFluidPiecesOnRow(this.formationType, 9, TeamType.CHO);
    }
}
