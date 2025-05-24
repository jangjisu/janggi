package com.game.janggi.domain.piece.move;

import com.game.janggi.domain.piece.Piece;
import com.game.janggi.domain.piece.position.PiecePosition;
import com.game.janggi.domain.team.TeamType;

import java.util.List;
import java.util.Map;

public class JolMovePosition extends MovePosition {
    private final List<Directions> moveAbleDirections = List.of(
            Directions.create(Direction.DOWN),
            Directions.create(Direction.LEFT),
            Directions.create(Direction.RIGHT)
    );


    @Override
    public List<PiecePosition> getMovablePosition(Map<PiecePosition, Piece> pieces, PiecePosition currentPosition) {
        TeamType teamType = getSelectedPieceTeamType(pieces, currentPosition);
        return moveAbleDirections.stream()
                .filter(currentPosition::canMove)
                .map(direction -> PiecePosition.create(currentPosition, direction))
                .filter(piecePosition -> isEmptyOrEnemyPiece(pieces, piecePosition, teamType))
                .toList();
    }
}
