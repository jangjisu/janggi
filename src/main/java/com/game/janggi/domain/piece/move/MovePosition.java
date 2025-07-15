package com.game.janggi.domain.piece.move;

import com.game.janggi.domain.piece.Piece;
import com.game.janggi.domain.piece.PieceType;
import com.game.janggi.domain.piece.position.GongPiecePosition;
import com.game.janggi.domain.piece.position.PiecePosition;
import com.game.janggi.domain.team.TeamType;

import java.util.List;
import java.util.Map;

public abstract class MovePosition {
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

    protected boolean haveObstacle(Map<PiecePosition, Piece> pieces, List<Movement> movementList, PiecePosition currentPosition) {
        return movementList.stream()
                .anyMatch(directions -> isTherePiece(pieces, PiecePosition.create(currentPosition, directions)));
    }

    protected boolean notHaveObstacle(Map<PiecePosition, Piece> pieces, List<Movement> movementList, PiecePosition currentPosition) {
        return !haveObstacle(pieces, movementList, currentPosition);
    }

    // 정렬된 가장 작은 길이의 리스트 부터 시작해서 만든 포지션이 비어있는지 확인한다
    protected Movements filterUntilBlockedByPiece(Map<PiecePosition, Piece> pieces, PiecePosition currentPosition, Movements movements) {
        return Movements.create
                (movements.sortByLengthAsc().getValues().stream()
                        .takeWhile(movement -> isThereEmpty(pieces, PiecePosition.create(currentPosition, movement)))
                        .toList());
    }

    protected Movements filteredWithinBoard(Movements movements, PiecePosition currentPosition) {
        return Movements.create(movements.getValues().stream()
                .filter(currentPosition::canMove)
                .toList());
    }

    protected Movements filteredWithinGong(Movements movements, PiecePosition currentPosition) {
        return Movements.create(movements.getValues().stream()
                .filter((currentPosition::canMove))
                .filter(movement -> GongPiecePosition.isInGongPosition(PiecePosition.create(currentPosition, movement)))
                .toList());
    }

    protected Movement getNextStepIfMovable(Map<PiecePosition, Piece> pieces, Movements beforeNextPieceDirections, PiecePosition currentPosition, Direction directionType, PieceType currentPieceType, TeamType currentTeamType) {
        if (Movement.isNextAvailable(beforeNextPieceDirections, currentPosition, directionType)) {
            Movement nextPieceMovement = Movement.appendSameToMaxDirection(beforeNextPieceDirections, directionType);

            Piece willMovePositionPiece = pieces.get(PiecePosition.create(currentPosition, nextPieceMovement));
            if (MoveRules.canMoveToNextPiece(currentPieceType, willMovePositionPiece, isPieceOfDifferentTeam(willMovePositionPiece, currentTeamType))) {
                return nextPieceMovement;
            }
        }

        return Movement.empty();
    }

    protected Movement getNextStepIfMovableAndInGong(Map<PiecePosition, Piece> pieces, Movements beforeNextPieceDirections, PiecePosition currentPosition, Direction directionType, PieceType currentPieceType, TeamType currentTeamType) {
        if (Movement.isNextAvailableAndInGong(beforeNextPieceDirections, currentPosition, directionType)) {
            Movement nextPieceMovement = Movement.appendSameToMaxDirection(beforeNextPieceDirections, directionType);

            Piece willMovePositionPiece = pieces.get(PiecePosition.create(currentPosition, nextPieceMovement));
            if (MoveRules.canMoveToNextPiece(currentPieceType, willMovePositionPiece, isPieceOfDifferentTeam(willMovePositionPiece, currentTeamType))) {
                return nextPieceMovement;
            }
        }

        return Movement.empty();
    }
}
