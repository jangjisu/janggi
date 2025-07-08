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

public class ChaMovePosition extends MovePosition {
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

    private final Movements diagonalDownLeftMovements = Movements.create(
            IntStream.rangeClosed(1, 2)
                    .mapToObj(steps -> Movement.create(Collections.nCopies(steps, Direction.DOWN_LEFT)))
                    .toList());

    private final Movements diagonalDownRightMovements = Movements.create(
            IntStream.rangeClosed(1, 2)
                    .mapToObj(steps -> Movement.create(Collections.nCopies(steps, Direction.DOWN_RIGHT)))
                    .toList());

    private final Movements diagonalUpLeftMovements = Movements.create(
            IntStream.rangeClosed(1, 2)
                    .mapToObj(steps -> Movement.create(Collections.nCopies(steps, Direction.UP_LEFT)))
                    .toList());

    private final Movements diagonalUpRightMovements = Movements.create(
            IntStream.rangeClosed(1, 2)
                    .mapToObj(steps -> Movement.create(Collections.nCopies(steps, Direction.UP_RIGHT)))
                    .toList());

    @Override
    public List<PiecePosition> getMoveablePosition(Map<PiecePosition, Piece> pieces, PiecePosition currentPosition) {
        TeamType currentTeamType = pieces.get(currentPosition).getTeamType();

        return Stream.of(
                        GongPiecePosition.canMoveDiagonal(currentPosition)
                                ? collectMovableInDigonalDirection(pieces, currentPosition, diagonalDownLeftMovements, Direction.DOWN_LEFT, currentTeamType).getValues()
                                : Movements.empty().getValues(),
                        GongPiecePosition.canMoveDiagonal(currentPosition)
                                ? collectMovableInDigonalDirection(pieces, currentPosition, diagonalDownRightMovements, Direction.DOWN_RIGHT, currentTeamType).getValues()
                                : Movements.empty().getValues(),
                        GongPiecePosition.canMoveDiagonal(currentPosition)
                                ? collectMovableInDigonalDirection(pieces, currentPosition, diagonalUpLeftMovements, Direction.UP_LEFT, currentTeamType).getValues()
                                : Movements.empty().getValues(),
                        GongPiecePosition.canMoveDiagonal(currentPosition)
                                ? collectMovableInDigonalDirection(pieces, currentPosition, diagonalUpRightMovements, Direction.UP_RIGHT, currentTeamType).getValues()
                                : Movements.empty().getValues(),
                        collectMovableInDirection(pieces, currentPosition, rightMovements, Direction.RIGHT, currentTeamType).getValues(),
                        collectMovableInDirection(pieces, currentPosition, leftMovements, Direction.LEFT, currentTeamType).getValues(),
                        collectMovableInDirection(pieces, currentPosition, upMovements, Direction.UP, currentTeamType).getValues(),
                        collectMovableInDirection(pieces, currentPosition, downMovements, Direction.DOWN, currentTeamType).getValues()
                )
                .flatMap(Collection::stream)
                .map(direction -> PiecePosition.create(currentPosition, direction))
                .toList();
    }

    // 한 방향으로 이동 가능한 포지션을 구한다.
    // 1. 생성 가능한 포지션을 필터한다 (boardBoundDirections).
    // 2. 생성 가능한 포지션 중에서 이동해 기물이 있는 곳 까지 구한다 (beforeNextPieceDirections)
    // 3. beforeNextPieceDirections 을 통해 구한 포지션 다음 위치에 기물이 있는지 확인(getNextStepIfMovable)해 상대편 기물이라면 그 위치까지 이동 가능범위에 추가한다.
    private Movements collectMovableInDirection(Map<PiecePosition, Piece> pieces, PiecePosition currentPosition, Movements moveAbleDirections, Direction directionType, TeamType currentTeamType) {
        Movements boardBoundDirections = filteredWithinBoard(moveAbleDirections, currentPosition);

        Movements beforeNextPieceDirections = filterUntilBlockedByPiece(pieces, currentPosition, boardBoundDirections);

        Movement nextStepIfMovable = getNextStepIfMovable(pieces, beforeNextPieceDirections, currentPosition, directionType, PieceType.CHA, currentTeamType);

        if (nextStepIfMovable != null && nextStepIfMovable.haveAnyDirection()) {
            return beforeNextPieceDirections.append(nextStepIfMovable);
        }

        return beforeNextPieceDirections;
    }

    private Movements collectMovableInDigonalDirection(Map<PiecePosition, Piece> pieces, PiecePosition currentPosition, Movements moveAbleDirections, Direction directionType, TeamType currentTeamType) {
        Movements boardBoundDirections = filteredWithinGong(moveAbleDirections, currentPosition);

        Movements beforeNextPieceDirections = filterUntilBlockedByPiece(pieces, currentPosition, boardBoundDirections);

        Movement nextStepIfMovable = getNextStepIfMovableAndInGong(pieces, beforeNextPieceDirections, currentPosition, directionType, PieceType.CHA, currentTeamType);

        if (nextStepIfMovable != null && nextStepIfMovable.haveAnyDirection()) {
            return beforeNextPieceDirections.append(nextStepIfMovable);
        }

        return beforeNextPieceDirections;
    }
}
