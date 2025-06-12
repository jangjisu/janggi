package com.game.janggi.domain.piece.move;

import com.game.janggi.domain.piece.Piece;
import com.game.janggi.domain.piece.position.PiecePosition;
import com.game.janggi.domain.team.TeamType;

import java.util.*;
import java.util.stream.Stream;

public class ChaMovePosition extends MovePosition {
    private final List<Directions> moveAbleUpDirections = List.of(
            Directions.create(Direction.UP),
            Directions.create(Direction.UP, Direction.UP),
            Directions.create(Direction.UP, Direction.UP, Direction.UP),
            Directions.create(Direction.UP, Direction.UP, Direction.UP, Direction.UP),
            Directions.create(Direction.UP, Direction.UP, Direction.UP, Direction.UP, Direction.UP),
            Directions.create(Direction.UP, Direction.UP, Direction.UP, Direction.UP, Direction.UP, Direction.UP),
            Directions.create(Direction.UP, Direction.UP, Direction.UP, Direction.UP, Direction.UP, Direction.UP, Direction.UP),
            Directions.create(Direction.UP, Direction.UP, Direction.UP, Direction.UP, Direction.UP, Direction.UP, Direction.UP, Direction.UP),
            Directions.create(Direction.UP, Direction.UP, Direction.UP, Direction.UP, Direction.UP, Direction.UP, Direction.UP, Direction.UP, Direction.UP),
            Directions.create(Direction.UP, Direction.UP, Direction.UP, Direction.UP, Direction.UP, Direction.UP, Direction.UP, Direction.UP, Direction.UP, Direction.UP)
    );

    private final List<Directions> moveAbleDownDirections = List.of(
            Directions.create(Direction.DOWN),
            Directions.create(Direction.DOWN, Direction.DOWN),
            Directions.create(Direction.DOWN, Direction.DOWN, Direction.DOWN),
            Directions.create(Direction.DOWN, Direction.DOWN, Direction.DOWN, Direction.DOWN),
            Directions.create(Direction.DOWN, Direction.DOWN, Direction.DOWN, Direction.DOWN, Direction.DOWN),
            Directions.create(Direction.DOWN, Direction.DOWN, Direction.DOWN, Direction.DOWN, Direction.DOWN, Direction.DOWN),
            Directions.create(Direction.DOWN, Direction.DOWN, Direction.DOWN, Direction.DOWN, Direction.DOWN, Direction.DOWN, Direction.DOWN),
            Directions.create(Direction.DOWN, Direction.DOWN, Direction.DOWN, Direction.DOWN, Direction.DOWN, Direction.DOWN, Direction.DOWN, Direction.DOWN, Direction.DOWN)
    );

    private final List<Directions> moveAbleLeftDirections = List.of(
            Directions.create(Direction.LEFT),
            Directions.create(Direction.LEFT,Direction.LEFT),
            Directions.create(Direction.LEFT,Direction.LEFT,Direction.LEFT),
            Directions.create(Direction.LEFT,Direction.LEFT,Direction.LEFT,Direction.LEFT),
            Directions.create(Direction.LEFT,Direction.LEFT,Direction.LEFT,Direction.LEFT,Direction.LEFT),
            Directions.create(Direction.LEFT,Direction.LEFT,Direction.LEFT,Direction.LEFT,Direction.LEFT,Direction.LEFT),
            Directions.create(Direction.LEFT,Direction.LEFT,Direction.LEFT,Direction.LEFT,Direction.LEFT,Direction.LEFT,Direction.LEFT),
            Directions.create(Direction.LEFT,Direction.LEFT,Direction.LEFT,Direction.LEFT,Direction.LEFT,Direction.LEFT,Direction.LEFT,Direction.LEFT)
    );

    private final List<Directions> moveAbleRightDirections = List.of(
            Directions.create(Direction.RIGHT),
            Directions.create(Direction.RIGHT,Direction.RIGHT),
            Directions.create(Direction.RIGHT,Direction.RIGHT,Direction.RIGHT),
            Directions.create(Direction.RIGHT,Direction.RIGHT,Direction.RIGHT,Direction.RIGHT),
            Directions.create(Direction.RIGHT,Direction.RIGHT,Direction.RIGHT,Direction.RIGHT,Direction.RIGHT),
            Directions.create(Direction.RIGHT,Direction.RIGHT,Direction.RIGHT,Direction.RIGHT,Direction.RIGHT,Direction.RIGHT),
            Directions.create(Direction.RIGHT,Direction.RIGHT,Direction.RIGHT,Direction.RIGHT,Direction.RIGHT,Direction.RIGHT,Direction.RIGHT),
            Directions.create(Direction.RIGHT,Direction.RIGHT,Direction.RIGHT,Direction.RIGHT,Direction.RIGHT,Direction.RIGHT,Direction.RIGHT,Direction.RIGHT)
    );

    private final List<Directions> allMoveAbleDirections =
            Stream.of(moveAbleDownDirections, moveAbleLeftDirections, moveAbleRightDirections, moveAbleUpDirections)
            .flatMap(Collection::stream)
            .toList();


    @Override
    public List<PiecePosition> getMoveablePosition(Map<PiecePosition, Piece> pieces, PiecePosition currentPosition) {
        List<Directions> rightDirectionMovePosition = getOneDirectionMovePosition(pieces, currentPosition, moveAbleRightDirections, Direction.RIGHT);
        List<Directions> leftDirectionMovePosition = getOneDirectionMovePosition(pieces, currentPosition, moveAbleLeftDirections, Direction.LEFT);
        List<Directions> upDirectionMovePosition = getOneDirectionMovePosition(pieces, currentPosition, moveAbleUpDirections, Direction.UP);
        List<Directions> downDirectionMovePosition = getOneDirectionMovePosition(pieces, currentPosition, moveAbleDownDirections, Direction.DOWN);

        return Stream.of(rightDirectionMovePosition, leftDirectionMovePosition, upDirectionMovePosition, downDirectionMovePosition)
                .flatMap(Collection::stream)
                .map(direction -> PiecePosition.create(currentPosition, direction))
                .toList();
    }

    @Override
    protected List<Directions> calculateBasicMoveAbleDirections(PiecePosition currentPosition) {
        return allMoveAbleDirections.stream()
                .filter(currentPosition::canMove)
                .toList();
    }

    // 한 방향으로 이동 가능한 포지션을 구한다.
    // 1. UP, DOWN, LEFT, RIGHT 중 하나의 이동 방향을 parameter로 받아서 생성 가능한 포지션을 필터한다.
    // 2. 생성 가능한 포지션 중에서 이동해 기물이 있는 곳 까지 구한다 (getMoveAbleDirectionsForFirst)
    // 3. getMoveAbleDirectionsForFirst 을 통해 구한 포지션 다음 위치에 기물이 있는지 확인해 상대편 기물이라면 그 위치까지 이동 가능범위에 추가한다.
    private List<Directions> getOneDirectionMovePosition(Map<PiecePosition, Piece> pieces, PiecePosition currentPosition, List<Directions> moveAbleDirections, Direction directionType) {
        List<Directions> list = moveAbleDirections.stream()
                .filter(currentPosition::canMove)
                .toList();

        list = getMoveAbleDirectionsForFirst(pieces, currentPosition, list);


        if (Directions.canMakeNextDirections(list, currentPosition, directionType)) {
            Directions plusOneDirection = Directions.getPlusOneDirections(list, directionType);

            Piece willMovePositionPiece = pieces.get(PiecePosition.create(currentPosition, plusOneDirection));
            TeamType selectedPieceTeamType = getSelectedPieceTeamType(pieces, currentPosition);
            if (isPieceOfDifferentTeam(willMovePositionPiece, selectedPieceTeamType)) {
                return Stream.concat(list.stream(), Stream.of(plusOneDirection))
                        .toList();
            }
        }

        return list;
    }

}
