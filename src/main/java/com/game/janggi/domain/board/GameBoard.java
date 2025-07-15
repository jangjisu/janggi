package com.game.janggi.domain.board;

import com.game.janggi.domain.formation.FormationType;
import com.game.janggi.domain.piece.King;
import com.game.janggi.domain.piece.Piece;
import com.game.janggi.domain.piece.Pieces;
import com.game.janggi.domain.piece.layout.ChoDefaultPieceLayout;
import com.game.janggi.domain.piece.layout.HanDefaultPieceLayout;
import com.game.janggi.domain.piece.position.PiecePosition;
import com.game.janggi.domain.team.TeamType;
import com.game.janggi.exception.RecoverableException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.*;

@RequiredArgsConstructor
public class GameBoard {
    private final Map<PiecePosition, Piece> pieces;
    @Getter
    private GameStatus gameStatus;

    @Getter
    private TeamType currentTurn;

    @Getter
    private Piece selectedPiece;

    public static final int BOARD_ROW_SIZE = 9;
    public static final int BOARD_COL_SIZE = 10;

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

    private Pieces getPieces() {
        return Pieces.create(
                pieces.values().stream()
                        .filter(Objects::nonNull)
                        .toArray(Piece[]::new)
        );
    }

    public void changeTurn() {
        this.currentTurn = this.currentTurn.getOppositeTeam();
        this.selectedPiece = null;
    }

    public Optional<Piece> getPieceAt(PiecePosition piecePosition) {
        return Optional.ofNullable(pieces.get(piecePosition));
    }

    public void validatePieceSelection(PiecePosition piecePosition) {
        Optional<Piece> pieceCandidate = getPieceAt(piecePosition);

        if (pieceCandidate.isEmpty()) {
            throw new RecoverableException("선택한 위치에 말이 없습니다.");
        }

        Piece piece = pieceCandidate.get();
        if (piece.isDifferentTeam(currentTurn)) {
            throw new RecoverableException("상대 진영의 말을 선택할 수 없습니다.");
        }

        if (piece.canMove(this.pieces, piecePosition)) {
            throw new RecoverableException("이동할 수 없는 말입니다.");
        }

        this.selectedPiece = piece;

    }

    public boolean isGameIsInProgress() {
        return gameStatus == GameStatus.IN_PROGRESS;
    }

    public void validateAndMovePiece(PiecePosition selectedPiecePosition, PiecePosition willMovePosition) {
        validateMovement(selectedPiecePosition, willMovePosition);
        executeMovement(selectedPiecePosition, willMovePosition);
    }

    private void validateMovement(PiecePosition selectedPiecePosition, PiecePosition willMovePosition) {
        if (!this.selectedPiece.canMoveTo(pieces, selectedPiecePosition, willMovePosition)) {
            throw new RecoverableException("이동할 수 없습니다.");
        }
    }

    private void executeMovement(PiecePosition selectedPiecePosition, PiecePosition willMovePosition) {
        pieces.remove(selectedPiecePosition);
        pieces.put(willMovePosition, selectedPiece);
    }

    public void changeGameStatus() {
        List<Piece> kingPieces = pieces.values().stream()
                .filter(King.class::isInstance)
                .toList();

        if (kingPieces.size() == 1) {
            this.gameStatus = gameStatus.changeGameEnd(kingPieces.get(0).getTeamType());
        }
    }

    public boolean haveSelectedPiece() {
        return selectedPiece != null;
    }
}
