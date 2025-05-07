package com.game.janggi.domain.board;

import com.game.janggi.domain.formation.FormationType;
import com.game.janggi.domain.piece.Piece;
import com.game.janggi.domain.piece.Pieces;
import com.game.janggi.domain.piece.layout.ChoDefaultPieceLayout;
import com.game.janggi.domain.piece.layout.HanDefaultPieceLayout;
import com.game.janggi.domain.piece.position.PiecePosition;
import com.game.janggi.domain.team.TeamType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class GameBoard {
    private final Map<PiecePosition, Piece> pieces;

    @Getter
    private TeamType currentTurn;

    public static GameBoard initializePieces(FormationType hanFormationType, FormationType choFormationType) {
        HanDefaultPieceLayout hanDefaultPieceLayout = new HanDefaultPieceLayout(hanFormationType);
        ChoDefaultPieceLayout choDefaultPieceLayout = new ChoDefaultPieceLayout(choFormationType);

        Map<PiecePosition, Piece> pieces = new HashMap<>();
        pieces.putAll(hanDefaultPieceLayout.createPieces());
        pieces.putAll(choDefaultPieceLayout.createPieces());

        GameBoard gameBoard = new GameBoard(pieces);
        gameBoard.currentTurn = TeamType.CHO;
        return gameBoard;
    }

    public int getPiecesSize() {
        return getPieces().size();
    }

    public Pieces getPieces() {
        ArrayList<Piece> pieceList = new ArrayList<>();

        for (int row = 0; row < getRowSize(); row++) {
            for (int col = 0; col < getColSize(); col++) {
                PiecePosition piecePosition = PiecePosition.create(row, col);
                Piece piece = pieces.get(piecePosition);
                if (piece != null) {
                    pieceList.add(piece);
                }
            }
        }

        return Pieces.create(pieceList);
    }

    public void changeTurn() {
        this.currentTurn = this.currentTurn.getOppositeTeam();
    }

    public int getRowSize() {
        return 10;
    }

    public int getColSize() {
        return 9;
    }

    public Piece getPiece(int row, int col) {
        PiecePosition piecePosition = PiecePosition.create(row, col);
        return pieces.get(piecePosition);
    }

}
