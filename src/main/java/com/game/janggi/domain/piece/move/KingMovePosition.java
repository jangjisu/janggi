package com.game.janggi.domain.piece.move;

import com.game.janggi.domain.piece.position.GongPiecePosition;
import com.game.janggi.domain.piece.position.PiecePosition;

import java.util.List;

public class KingMovePosition implements MovePosition {
    private final List<Directions> moveAbleDirections = List.of(
            Directions.create(Direction.UP),
            Directions.create(Direction.DOWN),
            Directions.create(Direction.LEFT),
            Directions.create(Direction.RIGHT),
            Directions.create(Direction.UP_LEFT),
            Directions.create(Direction.UP_RIGHT),
            Directions.create(Direction.DOWN_LEFT),
            Directions.create(Direction.DOWN_RIGHT)
    );


    @Override
    public List<PiecePosition> getMovablePosition(PiecePosition currentPosition) {
        return moveAbleDirections.stream()
                .filter(currentPosition::canMove)
                .map(direction -> PiecePosition.create(currentPosition, direction))
                .filter(GongPiecePosition::isInGongPosition)
                .toList();
    }
}
