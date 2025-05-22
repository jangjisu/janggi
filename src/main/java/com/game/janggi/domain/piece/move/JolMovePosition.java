package com.game.janggi.domain.piece.move;

import com.game.janggi.domain.piece.position.PiecePosition;

import java.util.List;

public class JolMovePosition implements MovePosition {
    private final List<Directions> moveAbleDirections = List.of(
            Directions.create(Direction.DOWN),
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
