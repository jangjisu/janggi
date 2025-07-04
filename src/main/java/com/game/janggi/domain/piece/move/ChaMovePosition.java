package com.game.janggi.domain.piece.move;

import com.game.janggi.domain.piece.Piece;
import com.game.janggi.domain.piece.PieceType;
import com.game.janggi.domain.piece.position.PiecePosition;
import com.game.janggi.domain.team.TeamType;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ChaMovePosition extends MovePosition {
    private final List<Directions> moveAbleUpDirections = IntStream.rangeClosed(1, 9)
            .mapToObj(steps -> Directions.create(Collections.nCopies(steps, Direction.UP)))
            .toList();

    private final List<Directions> moveAbleDownDirections = IntStream.rangeClosed(1, 9)
            .mapToObj(steps -> Directions.create(Collections.nCopies(steps, Direction.DOWN)))
            .toList();

    private final List<Directions> moveAbleLeftDirections = IntStream.rangeClosed(1, 8)
            .mapToObj(steps -> Directions.create(Collections.nCopies(steps, Direction.LEFT)))
            .toList();

    private final List<Directions> moveAbleRightDirections = IntStream.rangeClosed(1, 8)
            .mapToObj(steps -> Directions.create(Collections.nCopies(steps, Direction.RIGHT)))
            .toList();

    private final List<Directions> allMoveAbleDirections =
            Stream.of(moveAbleDownDirections, moveAbleLeftDirections, moveAbleRightDirections, moveAbleUpDirections)
                    .flatMap(Collection::stream)
                    .toList();


    @Override
    public List<PiecePosition> getMoveablePosition(Map<PiecePosition, Piece> pieces, PiecePosition currentPosition) {
        TeamType currentTeamType = pieces.get(currentPosition).getTeamType();

        return Stream.of(
                        collectMovableInDirection(pieces, currentPosition, moveAbleRightDirections, Direction.RIGHT, currentTeamType),
                        collectMovableInDirection(pieces, currentPosition, moveAbleLeftDirections, Direction.LEFT, currentTeamType),
                        collectMovableInDirection(pieces, currentPosition, moveAbleUpDirections, Direction.UP, currentTeamType),
                        collectMovableInDirection(pieces, currentPosition, moveAbleDownDirections, Direction.DOWN, currentTeamType)
                )
                .flatMap(Collection::stream)
                .map(direction -> PiecePosition.create(currentPosition, direction))
                .toList();
    }

    protected List<Directions> calculateBasicMoveAbleDirections(PiecePosition currentPosition) {
        return filteredWithinBoard(allMoveAbleDirections, currentPosition);
    }

    // 한 방향으로 이동 가능한 포지션을 구한다.
    // 1. 생성 가능한 포지션을 필터한다 (boardBoundDirections).
    // 2. 생성 가능한 포지션 중에서 이동해 기물이 있는 곳 까지 구한다 (beforeNextPieceDirections)
    // 3. beforeNextPieceDirections 을 통해 구한 포지션 다음 위치에 기물이 있는지 확인(getNextStepIfMovable)해 상대편 기물이라면 그 위치까지 이동 가능범위에 추가한다.
    private List<Directions> collectMovableInDirection(Map<PiecePosition, Piece> pieces, PiecePosition currentPosition, List<Directions> moveAbleDirections, Direction directionType, TeamType currentTeamType) {
        List<Directions> boardBoundDirections = filteredWithinBoard(moveAbleDirections, currentPosition);

        List<Directions> beforeNextPieceDirections = filterUntilBlockedByPiece(pieces, currentPosition, boardBoundDirections);
        return Stream.concat(
                        beforeNextPieceDirections.stream(),
                        Stream.of(getNextStepIfMovable(pieces, beforeNextPieceDirections, currentPosition, directionType, PieceType.CHA, currentTeamType))
                )
                .filter(Directions::isNotEmpty)
                .toList();
    }

}
