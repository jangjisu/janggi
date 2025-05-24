package com.game.janggi.domain.piece.move;

import com.game.janggi.domain.piece.Piece;
import com.game.janggi.domain.piece.position.PiecePosition;

import java.util.List;
import java.util.Map;

public class PoMovePosition extends MovePosition {
    private final List<Directions> moveAbleDirections = List.of(
            Directions.create(Direction.UP, Direction.UP),
            Directions.create(Direction.UP, Direction.UP, Direction.UP),
            Directions.create(Direction.UP, Direction.UP, Direction.UP, Direction.UP),
            Directions.create(Direction.UP, Direction.UP, Direction.UP, Direction.UP, Direction.UP),
            Directions.create(Direction.UP, Direction.UP, Direction.UP, Direction.UP, Direction.UP, Direction.UP),
            Directions.create(Direction.UP, Direction.UP, Direction.UP, Direction.UP, Direction.UP, Direction.UP, Direction.UP),
            Directions.create(Direction.UP, Direction.UP, Direction.UP, Direction.UP, Direction.UP, Direction.UP, Direction.UP, Direction.UP),
            Directions.create(Direction.UP, Direction.UP, Direction.UP, Direction.UP, Direction.UP, Direction.UP, Direction.UP, Direction.UP, Direction.UP),
            Directions.create(Direction.UP, Direction.UP, Direction.UP, Direction.UP, Direction.UP, Direction.UP, Direction.UP, Direction.UP, Direction.UP, Direction.UP),
            Directions.create(Direction.DOWN, Direction.DOWN),
            Directions.create(Direction.DOWN, Direction.DOWN, Direction.DOWN),
            Directions.create(Direction.DOWN, Direction.DOWN, Direction.DOWN, Direction.DOWN),
            Directions.create(Direction.DOWN, Direction.DOWN, Direction.DOWN, Direction.DOWN, Direction.DOWN),
            Directions.create(Direction.DOWN, Direction.DOWN, Direction.DOWN, Direction.DOWN, Direction.DOWN, Direction.DOWN),
            Directions.create(Direction.DOWN, Direction.DOWN, Direction.DOWN, Direction.DOWN, Direction.DOWN, Direction.DOWN, Direction.DOWN),
            Directions.create(Direction.DOWN, Direction.DOWN, Direction.DOWN, Direction.DOWN, Direction.DOWN, Direction.DOWN, Direction.DOWN, Direction.DOWN, Direction.DOWN),
            Directions.create(Direction.LEFT, Direction.LEFT),
            Directions.create(Direction.LEFT, Direction.LEFT, Direction.LEFT),
            Directions.create(Direction.LEFT, Direction.LEFT, Direction.LEFT, Direction.LEFT),
            Directions.create(Direction.LEFT, Direction.LEFT, Direction.LEFT, Direction.LEFT, Direction.LEFT),
            Directions.create(Direction.LEFT, Direction.LEFT, Direction.LEFT, Direction.LEFT, Direction.LEFT, Direction.LEFT),
            Directions.create(Direction.LEFT, Direction.LEFT, Direction.LEFT, Direction.LEFT, Direction.LEFT, Direction.LEFT, Direction.LEFT),
            Directions.create(Direction.LEFT, Direction.LEFT, Direction.LEFT, Direction.LEFT, Direction.LEFT, Direction.LEFT, Direction.LEFT, Direction.LEFT),
            Directions.create(Direction.RIGHT, Direction.RIGHT),
            Directions.create(Direction.RIGHT, Direction.RIGHT, Direction.RIGHT),
            Directions.create(Direction.RIGHT, Direction.RIGHT, Direction.RIGHT, Direction.RIGHT),
            Directions.create(Direction.RIGHT, Direction.RIGHT, Direction.RIGHT, Direction.RIGHT, Direction.RIGHT),
            Directions.create(Direction.RIGHT, Direction.RIGHT, Direction.RIGHT, Direction.RIGHT, Direction.RIGHT, Direction.RIGHT),
            Directions.create(Direction.RIGHT, Direction.RIGHT, Direction.RIGHT, Direction.RIGHT, Direction.RIGHT, Direction.RIGHT, Direction.RIGHT),
            Directions.create(Direction.RIGHT, Direction.RIGHT, Direction.RIGHT, Direction.RIGHT, Direction.RIGHT, Direction.RIGHT, Direction.RIGHT, Direction.RIGHT)
    );

    @Override
    public List<PiecePosition> getMovablePosition(Map<PiecePosition, Piece> pieces, PiecePosition currentPosition) {
        return moveAbleDirections.stream()
                .filter(currentPosition::canMove)
                .map(direction -> PiecePosition.create(currentPosition, direction))
                .toList();
    }
}
