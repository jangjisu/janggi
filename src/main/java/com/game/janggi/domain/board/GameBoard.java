package com.game.janggi.domain.board;

import com.game.janggi.domain.formation.FormationType;
import com.game.janggi.domain.piece.Piece;
import com.game.janggi.domain.piece.Pieces;
import com.game.janggi.domain.piece.layout.ChoDefaultPieceLayout;
import com.game.janggi.domain.piece.layout.HanDefaultPieceLayout;
import com.game.janggi.domain.team.TeamType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class GameBoard {
    private final Pieces pieces;
    @Getter
    private final TeamType currentTurn;

    public static GameBoard initalizePieces(FormationType hanFormationType, FormationType choFormationType) {
        HanDefaultPieceLayout hanDefaultPieceLayout = new HanDefaultPieceLayout(hanFormationType);
        ChoDefaultPieceLayout choDefaultPieceLayout = new ChoDefaultPieceLayout(choFormationType);

        List<Piece> pieces = new ArrayList<>();
        pieces.addAll(hanDefaultPieceLayout.createPieces());
        pieces.addAll(choDefaultPieceLayout.createPieces());

        return new GameBoard(Pieces.create(pieces), TeamType.CHO);
    }

    public int getPiecesSize() {
        return pieces.getSize();
    }
}
