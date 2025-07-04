package com.game.janggi.domain.piece.move;

import com.game.janggi.domain.piece.Piece;
import com.game.janggi.domain.piece.position.PiecePosition;
import com.game.janggi.domain.team.TeamType;

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
        TeamType teamType = getSelectedPieceTeamType(pieces, currentPosition);

        return calculateBasicMoveAbleDirections(currentPosition).stream()
                .filter(directions -> notHaveObstacle(pieces, directions.getMiddleDirections(), currentPosition))
                .map(direction -> PiecePosition.create(currentPosition, direction))
                .filter(piecePosition -> isEmptyOrEnemyPiece(pieces, piecePosition, teamType))
                .toList();
    }

    protected List<Directions> calculateBasicMoveAbleDirections(PiecePosition currentPosition) {
        return filteredWithinBoard(moveAbleDirections, currentPosition);
    }

}
