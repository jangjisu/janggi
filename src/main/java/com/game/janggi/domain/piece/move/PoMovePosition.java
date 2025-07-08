package com.game.janggi.domain.piece.move;

import com.game.janggi.domain.piece.Piece;
import com.game.janggi.domain.piece.PieceType;
import com.game.janggi.domain.piece.position.GongPiecePosition;
import com.game.janggi.domain.piece.position.PiecePosition;
import com.game.janggi.domain.team.TeamType;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PoMovePosition extends MovePosition {
    private final Movements upMovements = Movements.create(
            IntStream.rangeClosed(1, 9)
                    .mapToObj(steps -> Movement.create(Collections.nCopies(steps, Direction.UP)))
                    .toList());

    private final Movements downMovements = Movements.create(
            IntStream.rangeClosed(1, 9)
                    .mapToObj(steps -> Movement.create(Collections.nCopies(steps, Direction.DOWN)))
                    .toList());

    private final Movements leftMovements = Movements.create(
            IntStream.rangeClosed(1, 8)
                    .mapToObj(steps -> Movement.create(Collections.nCopies(steps, Direction.LEFT)))
                    .toList());

    private final Movements rightMovements = Movements.create(
            IntStream.rangeClosed(1, 8)
                    .mapToObj(steps -> Movement.create(Collections.nCopies(steps, Direction.RIGHT)))
                    .toList());


    @Override
    public List<PiecePosition> getMoveablePosition(Map<PiecePosition, Piece> pieces, PiecePosition currentPosition) {
        TeamType currentTeamType = pieces.get(currentPosition).getTeamType();

        List<PiecePosition> normalMovePositions = Stream.of(
                        collectMovableInDirection(pieces, currentPosition, rightMovements, Direction.RIGHT, currentTeamType).getValues(),
                        collectMovableInDirection(pieces, currentPosition, leftMovements, Direction.LEFT, currentTeamType).getValues(),
                        collectMovableInDirection(pieces, currentPosition, upMovements, Direction.UP, currentTeamType).getValues(),
                        collectMovableInDirection(pieces, currentPosition, downMovements, Direction.DOWN, currentTeamType).getValues()
                )
                .flatMap(Collection::stream)
                .map(direction -> PiecePosition.create(currentPosition, direction))
                .toList();

        List<PiecePosition> diagonalMovePositions = getDiagonalPiecePosition(pieces, currentPosition, currentTeamType);
        return Stream.concat(normalMovePositions.stream(), diagonalMovePositions.stream()).toList();

    }

    // 포가 대각선으로 이동할 수 경우 체크한다.
    // 1. 포의 위치가 대각선으로 이동할 수 있는 위치인지 확인한다.
    // 2. 궁성 중앙에 기물이 있고, 포가 뛰어넘을 수 있는 기물인지 확인한다.
    // 3. 대각선 위치로 이동할 수 있는지 확인한다.
    private List<PiecePosition> getDiagonalPiecePosition(Map<PiecePosition, Piece> pieces, PiecePosition currentPosition, TeamType currentTeamType) {
        if (GongPiecePosition.getPoCanDiagonalGongPositions().contains(currentPosition)) {

            if (isGongCenterEmptyOrNotJumpable(pieces, currentPosition)) {
                return List.of();
            }

            PiecePosition oppositeGongPosition = GongPiecePosition.getOppositeGongPosition(currentPosition);

            return isOppositePositionCanMove(pieces, oppositeGongPosition, currentTeamType) ? List.of(oppositeGongPosition) : List.of();
        }

        return List.of();
    }

    private boolean isGongCenterEmptyOrNotJumpable(Map<PiecePosition, Piece> pieces, PiecePosition currentPosition) {
        Piece centerPiece = pieces.get(GongPiecePosition.getGongCenterPosition(currentPosition));
        return centerPiece == null || MoveRules.canNotBeJumpedOver(centerPiece);
    }

    private boolean isOppositePositionCanMove(Map<PiecePosition, Piece> pieces, PiecePosition oppositeGongPosition, TeamType currentTeamType) {
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
    private Movements collectMovableInDirection(Map<PiecePosition, Piece> pieces, PiecePosition currentPosition, Movements moveAbleDirections, Direction directionType, TeamType currentTeamType) {
        Movements boardBoundDirections = filteredWithinBoard(moveAbleDirections, currentPosition);

        Movements beforeNextPieceDirections = filterUntilBlockedByPiece(pieces, currentPosition, boardBoundDirections);

        if (Movement.isNextNotAvailable(beforeNextPieceDirections.getValues(), currentPosition, directionType)) {
            return Movements.empty();
        }

        PiecePosition nextPiecePosition = PiecePosition.create(currentPosition, Movement.appendSameToMaxDirection(beforeNextPieceDirections.getValues(), directionType));
        Piece nextPiece = pieces.get(nextPiecePosition);

        if (MoveRules.canNotBeJumpedOver(nextPiece)) {
            return Movements.empty();
        }

        Movements poMoveAbleDirections = Movements.create(moveAbleDirections.getValues().stream()
                .filter(nextPiecePosition::canMove)
                .toList());

        Movements nextFilteredList = filterUntilBlockedByPiece(pieces, nextPiecePosition, poMoveAbleDirections);
        Movement standardDirection = Movement.appendSameToMaxDirection(beforeNextPieceDirections.getValues(), directionType);

        nextFilteredList.concatAll(standardDirection);

        Movement nextStepIfMovable = getNextStepIfMovable(pieces, nextFilteredList, nextPiecePosition, directionType, PieceType.PHO, currentTeamType);

        nextFilteredList.append(nextStepIfMovable);

        return nextFilteredList;
    }
}
