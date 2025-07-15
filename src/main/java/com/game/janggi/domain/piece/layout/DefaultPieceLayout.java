package com.game.janggi.domain.piece.layout;

import com.game.janggi.domain.formation.FormationType;
import com.game.janggi.domain.piece.Ma;
import com.game.janggi.domain.piece.Piece;
import com.game.janggi.domain.piece.Sang;
import com.game.janggi.domain.piece.position.PiecePosition;
import com.game.janggi.domain.team.TeamType;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
public abstract class DefaultPieceLayout {
    protected final FormationType formationType;

    public Map<PiecePosition, Piece> createPieces() {
        Map<PiecePosition, Piece> fixedPieces = createFixedPieces();
        Map<PiecePosition, Piece> fluidPieces = createFluidPieces();

        return Stream.concat(fixedPieces.entrySet().stream(), fluidPieces.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    protected abstract Map<PiecePosition, Piece> createFixedPieces();

    protected abstract Map<PiecePosition, Piece> createFluidPieces();

    protected Map<PiecePosition, Piece> createFluidPiecesOnRow(FormationType formationType, int colIndex, TeamType teamType) {
        return switch (formationType) {
            case MA_SANG_MA_SANG ->
                    create(colIndex, Ma.create(teamType), Sang.create(teamType), Ma.create(teamType), Sang.create(teamType));
            case SANG_MA_SANG_MA ->
                    create(colIndex, Sang.create(teamType), Ma.create(teamType), Sang.create(teamType), Ma.create(teamType));
            case SANG_MA_MA_SANG ->
                    create(colIndex, Sang.create(teamType), Ma.create(teamType), Ma.create(teamType), Sang.create(teamType));
            case MA_SANG_SANG_MA ->
                    create(colIndex, Ma.create(teamType), Sang.create(teamType), Sang.create(teamType), Ma.create(teamType));
        };
    }

    private Map<PiecePosition, Piece> create(int colIndex, Piece firstPiece, Piece secondPiece, Piece thirdPiece, Piece fourthPiece) {
        PiecePosition leftSangPosition = PiecePosition.create(1, colIndex);

        PiecePosition leftMaPosition = PiecePosition.create(2, colIndex);

        PiecePosition rightSangPosition = PiecePosition.create(6, colIndex);

        PiecePosition rightMaPosition = PiecePosition.create(7, colIndex);
        return Map.of(
                leftSangPosition, firstPiece,
                leftMaPosition, secondPiece,
                rightSangPosition, thirdPiece,
                rightMaPosition, fourthPiece
        );
    }
}
