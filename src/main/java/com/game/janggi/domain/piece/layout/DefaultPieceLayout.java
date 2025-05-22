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
        switch (formationType) {
            case MA_SANG_MA_SANG -> {
                PiecePosition leftMaPosition = PiecePosition.create(1, colIndex);
                Ma leftMa = Ma.create(teamType);

                PiecePosition leftSangPosition = PiecePosition.create(2, colIndex);
                Sang leftSang = Sang.create(teamType);

                PiecePosition rightMaPosition = PiecePosition.create(6, colIndex);
                Ma rightMa = Ma.create(teamType);

                PiecePosition rightSangPosition = PiecePosition.create(7, colIndex);
                Sang rightSang = Sang.create(teamType);

                return Map.of(
                        leftMaPosition, leftMa,
                        leftSangPosition, leftSang,
                        rightMaPosition, rightMa,
                        rightSangPosition, rightSang
                );
            }

            case SANG_MA_SANG_MA -> {
                PiecePosition leftSangPosition = PiecePosition.create(1, colIndex);
                Sang leftSang = Sang.create(teamType);

                PiecePosition leftMaPosition = PiecePosition.create(2, colIndex);
                Ma leftMa = Ma.create(teamType);

                PiecePosition rightSangPosition = PiecePosition.create(6, colIndex);
                Sang rightSang = Sang.create(teamType);

                PiecePosition rightMaPosition = PiecePosition.create(7, colIndex);
                Ma rightMa = Ma.create(teamType);
                return Map.of(
                        leftSangPosition, leftSang,
                        leftMaPosition, leftMa,
                        rightSangPosition, rightSang,
                        rightMaPosition, rightMa
                );
            }

            case SANG_MA_MA_SANG -> {
                PiecePosition leftSangPosition = PiecePosition.create(1, colIndex);
                Sang leftSang = Sang.create(teamType);

                PiecePosition leftMaPosition = PiecePosition.create(2, colIndex);
                Ma leftMa = Ma.create(teamType);

                PiecePosition rightMaPosition = PiecePosition.create(6, colIndex);
                Ma rightMa = Ma.create(teamType);

                PiecePosition rightSangPosition = PiecePosition.create(7, colIndex);
                Sang rightSang = Sang.create(teamType);

                return Map.of(
                        leftSangPosition, leftSang,
                        leftMaPosition, leftMa,
                        rightMaPosition, rightMa,
                        rightSangPosition, rightSang
                );
            }

            case MA_SANG_SANG_MA -> {
                PiecePosition leftMaPosition = PiecePosition.create(1, colIndex);
                Ma leftMa = Ma.create(teamType);

                PiecePosition leftSangPosition = PiecePosition.create(2, colIndex);
                Sang leftSang = Sang.create(teamType);

                PiecePosition rightSangPosition = PiecePosition.create(6, colIndex);
                Sang rightSang = Sang.create(teamType);

                PiecePosition rightMaPosition = PiecePosition.create(7, colIndex);
                Ma rightMa = Ma.create(teamType);

                return Map.of(
                        leftMaPosition, leftMa,
                        leftSangPosition, leftSang,
                        rightSangPosition, rightSang,
                        rightMaPosition, rightMa
                );
            }
        }
        return Map.of();
    }
}
