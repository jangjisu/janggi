package com.game.janggi.domain.piece.move;

import com.game.janggi.domain.piece.Piece;
import com.game.janggi.domain.piece.position.PiecePosition;

import java.util.List;
import java.util.Map;

public class MaMovePosition extends MovePosition {
    private final List<Directions> moveAbleDirections = List.of(
            Directions.create(Direction.UP, Direction.UP_LEFT),
            Directions.create(Direction.UP, Direction.UP_RIGHT),
            Directions.create(Direction.DOWN, Direction.DOWN_LEFT),
            Directions.create(Direction.DOWN, Direction.DOWN_RIGHT),
            Directions.create(Direction.LEFT, Direction.UP_LEFT),
            Directions.create(Direction.LEFT, Direction.DOWN_LEFT),
            Directions.create(Direction.RIGHT, Direction.UP_RIGHT),
            Directions.create(Direction.RIGHT, Direction.DOWN_RIGHT)
    );


    @Override
    public List<PiecePosition> getMoveablePosition(Map<PiecePosition, Piece> pieces, PiecePosition currentPosition) {
        return moveAbleDirections.stream()
                .filter(currentPosition::canMove)
                .map(direction -> PiecePosition.create(currentPosition, direction))
                .toList();
    }
}
