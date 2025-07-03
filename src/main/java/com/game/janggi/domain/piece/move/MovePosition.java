package com.game.janggi.domain.piece.move;

import com.game.janggi.domain.piece.Piece;
import com.game.janggi.domain.piece.PieceType;
import com.game.janggi.domain.piece.position.PiecePosition;
import com.game.janggi.domain.team.TeamType;

import java.util.List;
import java.util.Map;

public abstract class MovePosition {
    public abstract List<PiecePosition> getMoveablePosition(Map<PiecePosition, Piece> pieces, PiecePosition currentPosition);

    private boolean isTherePiece(Map<PiecePosition, Piece> pieces, PiecePosition willMovePosition) {
        return pieces.containsKey(willMovePosition);
    }

    private boolean isPieceOfSameTeam(Piece piece, TeamType currentTurnTeamType) {
        return piece.isSameTeam(currentTurnTeamType);
    }

    protected TeamType getSelectedPieceTeamType(Map<PiecePosition, Piece> pieces, PiecePosition currentPosition) {
        return pieces.get(currentPosition).getTeamType();
    }

    protected boolean isPieceOfDifferentTeam(Piece piece, TeamType currentTurnTeamType) {
        return !isPieceOfSameTeam(piece, currentTurnTeamType);
    }

    protected boolean isThereEmpty(Map<PiecePosition, Piece> pieces, PiecePosition willMovePosition) {
        return !isTherePiece(pieces, willMovePosition);
    }

    protected boolean isEmptyOrEnemyPiece(Map<PiecePosition, Piece> pieces, PiecePosition willMovePosition, TeamType currentTurnTeamType) {
        if (isThereEmpty(pieces, willMovePosition)) {
            return true;
        }

        return isPieceOfDifferentTeam(pieces.get(willMovePosition), currentTurnTeamType);
    }

    protected boolean haveObstacle(Map<PiecePosition, Piece> pieces, List<Directions> directionsList, PiecePosition currentPosition) {
        return directionsList.stream()
                .anyMatch(directions -> isTherePiece(pieces, PiecePosition.create(currentPosition, directions)));
    }

    protected boolean notHaveObstacle(Map<PiecePosition, Piece> pieces, List<Directions> directionsList, PiecePosition currentPosition) {
        return !haveObstacle(pieces, directionsList, currentPosition);
    }

    // 정렬된 가장 작은 길이의 리스트 부터 시작해서 만든 포지션이 비어있는지 확인한다
    protected List<Directions> filterUntilBlockedByPiece(Map<PiecePosition, Piece> pieces, PiecePosition currentPosition, List<Directions> directions) {
        return Directions.sortByLengthAsc(directions).stream()
                .takeWhile(direction -> isThereEmpty(pieces, PiecePosition.create(currentPosition, direction)))
                .toList();
    }

    protected List<Directions> filteredWithinBoard(List<Directions> moveAbleDirections, PiecePosition currentPosition) {
        return moveAbleDirections.stream()
                .filter(currentPosition::canMove)
                .toList();
    }

    protected Directions getNextStepIfMovable(Map<PiecePosition, Piece> pieces, List<Directions> beforeNextPieceDirections, PiecePosition currentPosition, Direction directionType, PieceType currentPieceType, TeamType currentTeamType) {
        if (Directions.isNextAvailable(beforeNextPieceDirections, currentPosition, directionType)) {
            Directions nextPieceDirections = Directions.appendNextStep(beforeNextPieceDirections, directionType);

            Piece willMovePositionPiece = pieces.get(PiecePosition.create(currentPosition, nextPieceDirections));
            if (MoveRules.canMoveToNextPiece(currentPieceType, willMovePositionPiece, isPieceOfDifferentTeam(willMovePositionPiece, currentTeamType))) {
                return nextPieceDirections;
            }
        }

        return Directions.empty();
    }

    public static BungMovePosition createBungMove() {
        return new BungMovePosition();
    }

    public static JolMovePosition createJolMove() {
        return new JolMovePosition();
    }

    public static KingMovePosition createKingMove() {
        return new KingMovePosition();
    }

    public static SaMovePosition createSaMove() {
        return new SaMovePosition();
    }

    public static SangMovePosition createSangMove() {
        return new SangMovePosition();
    }

    public static MaMovePosition createMaMove() {
        return new MaMovePosition();
    }

    public static PoMovePosition createPoMove() {
        return new PoMovePosition();
    }

    public static ChaMovePosition createChaMove() {
        return new ChaMovePosition();
    }
}
