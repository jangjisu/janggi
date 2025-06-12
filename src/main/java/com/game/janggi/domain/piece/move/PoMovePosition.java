package com.game.janggi.domain.piece.move;

import com.game.janggi.domain.piece.Piece;
import com.game.janggi.domain.piece.position.PiecePosition;
import com.game.janggi.domain.team.TeamType;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
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


    @Override
    public List<PiecePosition> getMoveablePosition(Map<PiecePosition, Piece> pieces, PiecePosition currentPosition) {
        List<Directions> rightDirectionMovePosition = getMoveAbleDirections(pieces, currentPosition, moveAbleRightDirections, Direction.RIGHT);
        List<Directions> leftDirectionMovePosition = getMoveAbleDirections(pieces, currentPosition, moveAbleLeftDirections, Direction.LEFT);
        List<Directions> upDirectionMovePosition = getMoveAbleDirections(pieces, currentPosition, moveAbleUpDirections, Direction.UP);
        List<Directions> downDirectionMovePosition = getMoveAbleDirections(pieces, currentPosition, moveAbleDownDirections, Direction.DOWN);


        List<PiecePosition> list = Stream.of(rightDirectionMovePosition, leftDirectionMovePosition, upDirectionMovePosition, downDirectionMovePosition)
                .flatMap(Collection::stream)
                .map(direction -> PiecePosition.create(currentPosition, direction))
                .toList();

        System.out.println("Po Move Positions: " + list);
        return list;
    }

    private List<Directions> getMoveAbleDirections(Map<PiecePosition, Piece> pieces, PiecePosition currentPosition, List<Directions> moveAbleDirections, Direction directionType) {
        List<Directions> basicList = moveAbleDirections.stream()
                .filter(currentPosition::canMove)
                .toList();

        List<Directions> filteredList = getMoveAbleDirectionsForFirst(pieces, currentPosition, basicList);

        if (Directions.canNotMakeNextDirections(filteredList, currentPosition, directionType)) {
            return List.of();
        }

        PiecePosition nextPiecePosition = getNextPiecePosition(currentPosition, filteredList, directionType);
        Piece nextPiece = pieces.get(nextPiecePosition);

        if (Piece.isPieceCanNotJump(nextPiece)) {
            return List.of();
        }

        List<Directions> poMoveAbleDirections = moveAbleDirections.stream()
                .filter(nextPiecePosition::canMove)
                .toList();

        Directions standardDirection = Directions.getPlusOneDirections(filteredList, directionType);

        List<Directions> nextFilteredList = getMoveAbleDirectionsForFirst(pieces, nextPiecePosition, poMoveAbleDirections);

        if (Directions.canMakeNextDirections(nextFilteredList, nextPiecePosition, directionType)) {
            Directions plusOneDirection = Directions.getPlusOneDirections(nextFilteredList, directionType);

            Piece willMovePositionPiece = pieces.get(PiecePosition.create(nextPiecePosition, plusOneDirection));
            TeamType selectedPieceTeamType = getSelectedPieceTeamType(pieces, currentPosition);
            if (isPieceOfDifferentTeam(willMovePositionPiece, selectedPieceTeamType) && Piece.isPieceCanJump(willMovePositionPiece)) {
                return Stream.concat(nextFilteredList.stream(), Stream.of(plusOneDirection))
                        .map(directions -> Directions.concat(standardDirection, directions))
                        .collect(Collectors.toList());
            }
        }

        return nextFilteredList.stream()
                .map(directions -> Directions.concat(standardDirection, directions))
                .collect(Collectors.toList());
    }

    private PiecePosition getNextPiecePosition(PiecePosition currentPosition, List<Directions> directions, Direction directionType) {
        return PiecePosition.create(currentPosition, Directions.getPlusOneDirections(directions, directionType));
    }

    @Override
    protected List<Directions> calculateBasicMoveAbleDirections(PiecePosition currentPosition) {
        return Stream.of(moveAbleDownDirections, moveAbleLeftDirections, moveAbleRightDirections, moveAbleUpDirections)
                .flatMap(Collection::stream)
                .toList();
    }
}
