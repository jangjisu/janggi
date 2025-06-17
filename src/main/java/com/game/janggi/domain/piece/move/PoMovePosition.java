package com.game.janggi.domain.piece.move;

import com.game.janggi.domain.piece.Piece;
import com.game.janggi.domain.piece.PieceType;
import com.game.janggi.domain.piece.position.GongPiecePosition;
import com.game.janggi.domain.piece.position.PiecePosition;
import com.game.janggi.domain.team.TeamType;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class PoMovePosition extends MovePosition {
    private final List<Directions> moveAbleUpDirections = List.of(
            Directions.create(Direction.UP),
            Directions.create(Direction.UP, Direction.UP),
            Directions.create(Direction.UP, Direction.UP, Direction.UP),
            Directions.create(Direction.UP, Direction.UP, Direction.UP, Direction.UP),
            Directions.create(Direction.UP, Direction.UP, Direction.UP, Direction.UP, Direction.UP),
            Directions.create(Direction.UP, Direction.UP, Direction.UP, Direction.UP, Direction.UP, Direction.UP),
            Directions.create(Direction.UP, Direction.UP, Direction.UP, Direction.UP, Direction.UP, Direction.UP, Direction.UP),
            Directions.create(Direction.UP, Direction.UP, Direction.UP, Direction.UP, Direction.UP, Direction.UP, Direction.UP, Direction.UP),
            Directions.create(Direction.UP, Direction.UP, Direction.UP, Direction.UP, Direction.UP, Direction.UP, Direction.UP, Direction.UP, Direction.UP)
    );

    private final List<Directions> moveAbleDownDirections = List.of(
            Directions.create(Direction.DOWN),
            Directions.create(Direction.DOWN, Direction.DOWN),
            Directions.create(Direction.DOWN, Direction.DOWN, Direction.DOWN),
            Directions.create(Direction.DOWN, Direction.DOWN, Direction.DOWN, Direction.DOWN),
            Directions.create(Direction.DOWN, Direction.DOWN, Direction.DOWN, Direction.DOWN, Direction.DOWN),
            Directions.create(Direction.DOWN, Direction.DOWN, Direction.DOWN, Direction.DOWN, Direction.DOWN, Direction.DOWN),
            Directions.create(Direction.DOWN, Direction.DOWN, Direction.DOWN, Direction.DOWN, Direction.DOWN, Direction.DOWN, Direction.DOWN),
            Directions.create(Direction.DOWN, Direction.DOWN, Direction.DOWN, Direction.DOWN, Direction.DOWN, Direction.DOWN, Direction.DOWN, Direction.DOWN),
            Directions.create(Direction.DOWN, Direction.DOWN, Direction.DOWN, Direction.DOWN, Direction.DOWN, Direction.DOWN, Direction.DOWN, Direction.DOWN, Direction.DOWN)
    );

    private final List<Directions> moveAbleLeftDirections = List.of(
            Directions.create(Direction.LEFT),
            Directions.create(Direction.LEFT, Direction.LEFT),
            Directions.create(Direction.LEFT, Direction.LEFT, Direction.LEFT),
            Directions.create(Direction.LEFT, Direction.LEFT, Direction.LEFT, Direction.LEFT),
            Directions.create(Direction.LEFT, Direction.LEFT, Direction.LEFT, Direction.LEFT, Direction.LEFT),
            Directions.create(Direction.LEFT, Direction.LEFT, Direction.LEFT, Direction.LEFT, Direction.LEFT, Direction.LEFT),
            Directions.create(Direction.LEFT, Direction.LEFT, Direction.LEFT, Direction.LEFT, Direction.LEFT, Direction.LEFT, Direction.LEFT),
            Directions.create(Direction.LEFT, Direction.LEFT, Direction.LEFT, Direction.LEFT, Direction.LEFT, Direction.LEFT, Direction.LEFT, Direction.LEFT)
    );

    private final List<Directions> moveAbleRightDirections = List.of(
            Directions.create(Direction.RIGHT),
            Directions.create(Direction.RIGHT, Direction.RIGHT),
            Directions.create(Direction.RIGHT, Direction.RIGHT, Direction.RIGHT),
            Directions.create(Direction.RIGHT, Direction.RIGHT, Direction.RIGHT, Direction.RIGHT),
            Directions.create(Direction.RIGHT, Direction.RIGHT, Direction.RIGHT, Direction.RIGHT, Direction.RIGHT),
            Directions.create(Direction.RIGHT, Direction.RIGHT, Direction.RIGHT, Direction.RIGHT, Direction.RIGHT, Direction.RIGHT),
            Directions.create(Direction.RIGHT, Direction.RIGHT, Direction.RIGHT, Direction.RIGHT, Direction.RIGHT, Direction.RIGHT, Direction.RIGHT),
            Directions.create(Direction.RIGHT, Direction.RIGHT, Direction.RIGHT, Direction.RIGHT, Direction.RIGHT, Direction.RIGHT, Direction.RIGHT, Direction.RIGHT)
    );

    private final List<Directions> allMoveAbleDirections =
            Stream.of(moveAbleDownDirections, moveAbleLeftDirections, moveAbleRightDirections, moveAbleUpDirections)
                    .flatMap(Collection::stream)
                    .toList();


    @Override
    public List<PiecePosition> getMoveablePosition(Map<PiecePosition, Piece> pieces, PiecePosition currentPosition) {
        TeamType currentTeamType = pieces.get(currentPosition).getTeamType();

        List<PiecePosition> normalMovePositions = Stream.of(
                        collectMovableInDirection(pieces, currentPosition, moveAbleRightDirections, Direction.RIGHT, currentTeamType),
                        collectMovableInDirection(pieces, currentPosition, moveAbleLeftDirections, Direction.LEFT, currentTeamType),
                        collectMovableInDirection(pieces, currentPosition, moveAbleUpDirections, Direction.UP, currentTeamType),
                        collectMovableInDirection(pieces, currentPosition, moveAbleDownDirections, Direction.DOWN, currentTeamType)
                )
                .flatMap(Collection::stream)
                .map(direction -> PiecePosition.create(currentPosition, direction))
                .toList();

        List<PiecePosition> diagonalMovePositions = getDiagonalPiecePosition(pieces, currentPosition, currentTeamType);
        return Stream.concat(normalMovePositions.stream(), diagonalMovePositions.stream()).toList();

    }

    // 포가 대각선으로 이동할 수 경우 체크한다.
    // 1. 포의 위치가 대각선으로 이동할 수 있는 위치인지 확인한다.
    // 2. 궁성 중앙
    private List<PiecePosition> getDiagonalPiecePosition(Map<PiecePosition, Piece> pieces, PiecePosition currentPosition, TeamType currentTeamType) {
        if (GongPiecePosition.getPoCanDiagonalGongPositions().contains(currentPosition)) {

            if (isGongCenterEmptyOrNotJumpable(pieces, currentPosition)) {
                return List.of();
            }

            return isOppositePositionisEmptyOrCanMove(pieces, currentPosition, currentTeamType) ? List.of(GongPiecePosition.getOppositeGongPosition(currentPosition)) : List.of();
        }

        return List.of();
    }

    private boolean isGongCenterEmptyOrNotJumpable(Map<PiecePosition, Piece> pieces, PiecePosition currentPosition) {
        Piece centerPiece = pieces.get(GongPiecePosition.getGongCenterPosition(currentPosition));
        return centerPiece == null || MoveRules.canNotBeJumpedOver(centerPiece);
    }

    private boolean isOppositePositionisEmptyOrCanMove(Map<PiecePosition, Piece> pieces, PiecePosition currentPosition, TeamType currentTeamType) {
        PiecePosition oppositeGongPosition = GongPiecePosition.getOppositeGongPosition(currentPosition);
        Piece oppositePiece = pieces.get(oppositeGongPosition);

        return oppositePiece == null || MoveRules.canMoveToNextPiece(PieceType.PHO, oppositePiece, isPieceOfDifferentTeam(oppositePiece, currentTeamType));
    }

    // 한 방향으로 이동 가능한 포지션을 구한다.
    // 1. 생성 가능한 포지션을 필터한다 (boardBoundDirections).
    // 2. 생성 가능한 포지션 중에서 이동해 기물이 있는 곳 까지 구한다 (beforeNextPieceDirections)
    // 3. 보드가 끝나서 필터된 것인지 확인한다. (isNextNotAvailable)
    // 4. 다음 기물이 뛰어넘을 수 있는 기물인지 확인한다 (canBeJumpedOver)
    // 5. 다음 기물로부터 1/2 번을 수행한다 (nextFilteredList)
    // 6. 다음에 다음 기물이 이동가능한지 확인해 추가한다 (getNextStepIfMovable)
    private List<Directions> collectMovableInDirection(Map<PiecePosition, Piece> pieces, PiecePosition currentPosition, List<Directions> moveAbleDirections, Direction directionType, TeamType currentTeamType) {
        List<Directions> boardBoundDirections = filteredWithinBoard(moveAbleDirections, currentPosition);

        List<Directions> beforeNextPieceDirections = filterUntilBlockedByPiece(pieces, currentPosition, boardBoundDirections);

        if (Directions.isNextNotAvailable(beforeNextPieceDirections, currentPosition, directionType)) {
            return List.of();
        }

        PiecePosition nextPiecePosition = PiecePosition.create(currentPosition, Directions.appendNextStep(beforeNextPieceDirections, directionType));
        Piece nextPiece = pieces.get(nextPiecePosition);

        if (MoveRules.canNotBeJumpedOver(nextPiece)) {
            return List.of();
        }

        List<Directions> poMoveAbleDirections = moveAbleDirections.stream()
                .filter(nextPiecePosition::canMove)
                .toList();

        List<Directions> nextFilteredList = filterUntilBlockedByPiece(pieces, nextPiecePosition, poMoveAbleDirections);
        Directions standardDirection = Directions.appendNextStep(beforeNextPieceDirections, directionType);

        return Stream.concat(
                        nextFilteredList.stream(),
                        Stream.of(getNextStepIfMovable(pieces, nextFilteredList, nextPiecePosition, directionType, PieceType.PHO, currentTeamType))
                )
                .filter(Directions::isNotEmpty)
                .map(directions -> Directions.concat(standardDirection, directions))
                .toList();
    }

    @Override
    protected List<Directions> calculateBasicMoveAbleDirections(PiecePosition currentPosition) {
        return filteredWithinBoard(allMoveAbleDirections, currentPosition);
    }
}
