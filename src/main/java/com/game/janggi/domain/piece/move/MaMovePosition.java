package com.game.janggi.domain.piece.move;

import com.game.janggi.domain.piece.Piece;
import com.game.janggi.domain.piece.position.PiecePosition;
import com.game.janggi.domain.team.TeamType;

import java.util.List;
import java.util.Map;

public class MaMovePosition extends MovePosition {
    private final List<Movement> moveAbleDirections = List.of(
            Movement.create(Direction.UP, Direction.UP_LEFT),
            Movement.create(Direction.UP, Direction.UP_RIGHT),
            Movement.create(Direction.DOWN, Direction.DOWN_LEFT),
            Movement.create(Direction.DOWN, Direction.DOWN_RIGHT),
            Movement.create(Direction.LEFT, Direction.UP_LEFT),
            Movement.create(Direction.LEFT, Direction.DOWN_LEFT),
            Movement.create(Direction.RIGHT, Direction.UP_RIGHT),
            Movement.create(Direction.RIGHT, Direction.DOWN_RIGHT)
    );


    @Override
    public List<PiecePosition> getMoveablePosition(Map<PiecePosition, Piece> pieces, PiecePosition currentPosition) {
        TeamType teamType = getSelectedPieceTeamType(pieces, currentPosition);

        return calculateBasicMoveAbleDirections(currentPosition).getValues().stream()
                .filter(directions -> notHaveObstacle(pieces, directions.getPartialMovements(), currentPosition))
                .map(direction -> PiecePosition.create(currentPosition, direction))
                .filter(piecePosition -> isEmptyOrEnemyPiece(pieces, piecePosition, teamType))
                .toList();
    }

    protected Movements calculateBasicMoveAbleDirections(PiecePosition currentPosition) {
        return filteredWithinBoard2(moveAbleDirections, currentPosition);
    }

}
