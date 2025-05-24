package com.game.janggi.domain.piece.move;

import com.game.janggi.domain.piece.Piece;
import com.game.janggi.domain.piece.position.GongPiecePosition;
import com.game.janggi.domain.piece.position.PiecePosition;
import com.game.janggi.domain.team.TeamType;

import java.util.List;
import java.util.Map;

public class KingMovePosition extends MovePosition {
    private final List<Directions> diagonalMoveAbleDirections = List.of(
            Directions.create(Direction.UP),
            Directions.create(Direction.DOWN),
            Directions.create(Direction.LEFT),
            Directions.create(Direction.RIGHT),
            Directions.create(Direction.UP_LEFT),
            Directions.create(Direction.UP_RIGHT),
            Directions.create(Direction.DOWN_LEFT),
            Directions.create(Direction.DOWN_RIGHT)
    );

    private final List<Directions> moveAbleDirections = List.of(
            Directions.create(Direction.UP),
            Directions.create(Direction.DOWN),
            Directions.create(Direction.LEFT),
            Directions.create(Direction.RIGHT)
    );


    @Override
    public List<PiecePosition> getMovablePosition(Map<PiecePosition, Piece> pieces, PiecePosition currentPosition) {
        TeamType teamType = getSelectedPieceTeamType(pieces, currentPosition);

        return calculateBasicMoveablePositions(currentPosition, teamType).stream()
                .filter(piecePosition -> isEmptyOrEnemyPiece(pieces, piecePosition, teamType))
                .toList();
    }

    protected List<PiecePosition> calculateBasicMoveablePositions(PiecePosition currentPosition, TeamType teamType) {
        return (GongPiecePosition.canMoveDiagonal(currentPosition) ? diagonalMoveAbleDirections : moveAbleDirections).stream()
                .filter(currentPosition::canMove)
                .map(direction -> PiecePosition.create(currentPosition, direction))
                .filter(piecePosition -> GongPiecePosition.isInGongPosition(piecePosition, teamType))
                .toList();
    }
}
