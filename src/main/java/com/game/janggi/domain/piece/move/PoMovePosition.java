package com.game.janggi.domain.piece.move;

import com.game.janggi.domain.piece.Piece;
import com.game.janggi.domain.piece.PieceType;
import com.game.janggi.domain.piece.position.PiecePosition;

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
        List<PiecePosition> list = Stream.of(
                        collectMovableInDirection(pieces, currentPosition, moveAbleRightDirections, Direction.RIGHT),
                        collectMovableInDirection(pieces, currentPosition, moveAbleLeftDirections, Direction.LEFT),
                        collectMovableInDirection(pieces, currentPosition, moveAbleUpDirections, Direction.UP),
                        collectMovableInDirection(pieces, currentPosition, moveAbleDownDirections, Direction.DOWN)
                )
                .flatMap(Collection::stream)
                .map(direction -> PiecePosition.create(currentPosition, direction))
                .toList();
        System.out.println("PoMovePosition.getMoveablePosition: " + list);
        return list;
    }

    private List<Directions> collectMovableInDirection(Map<PiecePosition, Piece> pieces, PiecePosition currentPosition, List<Directions> moveAbleDirections, Direction directionType) {
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
                        Stream.of(getNextStepIfMovable(pieces, beforeNextPieceDirections, currentPosition, directionType, PieceType.PHO))
                )
                .map(directions -> Directions.concat(standardDirection, directions))
        .toList();
    }

    @Override
    protected List<Directions> calculateBasicMoveAbleDirections(PiecePosition currentPosition) {
        return filteredWithinBoard(allMoveAbleDirections, currentPosition);
    }
}
