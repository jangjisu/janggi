package com.game.janggi.domain.piece.move;

import com.game.janggi.domain.piece.Piece;
import com.game.janggi.domain.piece.position.GongPiecePosition;
import com.game.janggi.domain.piece.position.PiecePosition;
import com.game.janggi.domain.team.TeamType;

import java.util.List;
import java.util.Map;

public class SaMovePosition extends MovePosition {
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
    public List<PiecePosition> getMovablePosition(Map<PiecePosition, Piece> pieces, PiecePosition currentPosition, TeamType teamType) {
        List<PiecePosition> list = (GongPiecePosition.canMoveDiagonal(currentPosition) ? diagonalMoveAbleDirections : moveAbleDirections).stream()
                .filter(currentPosition::canMove) //실제 존재할 수 있는 포지션인지 체크
                .map(direction -> PiecePosition.create(currentPosition, direction))
                .filter(piecePosition -> GongPiecePosition.isInGongPosition(piecePosition, teamType)) //이게 궁안 포지션인지 체크
                .toList();
        //list.removeIf(piecePosition -> isTherePiece(pieces, piecePosition));
        //list.removeIf(piecePosition -> isPieceOfSameTeam(pieces, piecePosition, teamType));


        return list;
    }
}
