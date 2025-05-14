package com.game.janggi.domain.board;

import com.game.janggi.domain.formation.FormationType;
import com.game.janggi.domain.piece.Piece;
import com.game.janggi.domain.piece.Pieces;
import com.game.janggi.domain.piece.layout.ChoDefaultPieceLayout;
import com.game.janggi.domain.piece.layout.HanDefaultPieceLayout;
import com.game.janggi.domain.piece.position.PiecePosition;
import com.game.janggi.domain.team.TeamType;
import com.game.janggi.exception.RecoverableException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class GameBoard {
    private final Map<PiecePosition, Piece> pieces;
    private GameStatus gameStatus;

    @Getter
    private TeamType currentTurn;

    @Getter
    private Piece selectedPiece;

    public static GameBoard initializePieces(FormationType hanFormationType, FormationType choFormationType) {
        HanDefaultPieceLayout hanDefaultPieceLayout = new HanDefaultPieceLayout(hanFormationType);
        ChoDefaultPieceLayout choDefaultPieceLayout = new ChoDefaultPieceLayout(choFormationType);

        Map<PiecePosition, Piece> pieces = new HashMap<>();
        pieces.putAll(hanDefaultPieceLayout.createPieces());
        pieces.putAll(choDefaultPieceLayout.createPieces());

        GameBoard gameBoard = new GameBoard(pieces);
        gameBoard.gameStatus = GameStatus.IN_PROGRESS;
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
        this.selectedPiece = null;
    }

    public int getRowSize() {
        return 10;
    }

    public int getColSize() {
        return 9;
    }

    public Piece getPiece(PiecePosition piecePosition) {
        return pieces.get(piecePosition);
    }

    public boolean isGameOver() {
        return gameStatus != GameStatus.IN_PROGRESS;
    }

    public boolean isGameIsInProgress() {
        return !isGameOver();
    }

    public boolean isChoTurn() {
        return currentTurn == TeamType.CHO;
    }

    public boolean isHanTurn() {
        return !isChoTurn();
    }

    public void validatePieceSelection(PiecePosition piecePosition) {
        Piece piece = getPiece(piecePosition);

        if (piece == null) {
            throw new RecoverableException("선택한 위치에 말이 없습니다.");
        }

        if (piece.isDifferentTeam(currentTurn)) {
            throw new RecoverableException("상대 진영의 말을 선택할 수 없습니다.");
        }

        this.selectedPiece = piece;
    }

    public void validatePieceMove(PiecePosition willMovePosition) {
        //TODO 구현 후 주석 해제
        //this.selectedPiece.canMoveTo(willMovePosition);
    }

    public boolean haveSelectedPiece() {
        return selectedPiece != null;
    }
}
