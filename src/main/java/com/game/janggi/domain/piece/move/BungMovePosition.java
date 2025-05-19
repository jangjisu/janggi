package com.game.janggi.domain.piece.move;

import com.game.janggi.domain.piece.position.PiecePosition;

import java.util.List;

public class BungMovePosition implements MovePosition {
    private final List<Directions> moveAbleDirections = List.of(
            Directions.create(Direction.UP),
            Directions.create(Direction.LEFT),
            Directions.create(Direction.RIGHT)
    );


    @Override
    public List<PiecePosition> getMovablePosition(PiecePosition currentPosition) {
        return moveAbleDirections.stream()
                .filter(currentPosition::canMove)
                .map(direction -> PiecePosition.create(currentPosition, direction))
                .toList();
    }
}
