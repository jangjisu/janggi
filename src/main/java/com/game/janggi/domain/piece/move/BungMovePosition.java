package com.game.janggi.domain.piece.move;

import com.game.janggi.domain.piece.Piece;
import com.game.janggi.domain.piece.position.GongPiecePosition;
import com.game.janggi.domain.piece.position.PiecePosition;
import com.game.janggi.domain.team.TeamType;

import java.util.List;
import java.util.Map;

public class BungMovePosition extends MovePosition {
    private final Movements movements = Movements.create(
            List.of(
                    Movement.create(Direction.UP),
                    Movement.create(Direction.LEFT),
                    Movement.create(Direction.RIGHT)
            ));

    private final Movements diagonalMovements = Movements.create(
            List.of(
                    Movement.create(Direction.UP),
                    Movement.create(Direction.LEFT),
                    Movement.create(Direction.RIGHT),
                    Movement.create(Direction.UP_LEFT),
                    Movement.create(Direction.UP_RIGHT)
            ));


    @Override
    public List<PiecePosition> getMoveablePosition(Map<PiecePosition, Piece> pieces, PiecePosition currentPosition) {
        TeamType teamType = getSelectedPieceTeamType(pieces, currentPosition);
        return calculateBasicMoveAbleDirections(currentPosition).getValues().stream()
                .map(direction -> PiecePosition.create(currentPosition, direction))
                .filter(piecePosition -> isEmptyOrEnemyPiece(pieces, piecePosition, teamType))
                .toList();
    }

    private Movements calculateBasicMoveAbleDirections(PiecePosition currentPosition) {
        return filteredWithinBoard(
                GongPiecePosition.canMoveDiagonal(currentPosition)
                        ? diagonalMovements : movements, currentPosition);
    }
}
