package com.game.janggi.domain.piece.layout;

import com.game.janggi.domain.formation.FormationType;
import com.game.janggi.domain.piece.Ma;
import com.game.janggi.domain.piece.Piece;
import com.game.janggi.domain.piece.Sang;
import com.game.janggi.domain.team.TeamType;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public abstract class DefaultPieceLayout {
    protected final FormationType formationType;

    public List<Piece> createPieces() {
        List<Piece> pieces = new ArrayList<>();
        pieces.addAll(createFixedPieces());
        pieces.addAll(createFluidPieces());
        return pieces;
    }

    protected abstract List<Piece> createFixedPieces();

    protected abstract List<Piece> createFluidPieces();

    protected List<Piece> createFluidPiecesOnRow(FormationType formationType, int rowIndex, TeamType teamType) {
        switch (formationType) {
            case MA_SANG_MA_SANG -> {
                Ma leftMa = Ma.create(rowIndex, 1, teamType);
                Sang leftSang = Sang.create(rowIndex, 2, teamType);
                Ma rightMa = Ma.create(rowIndex, 6, teamType);
                Sang rightSang = Sang.create(rowIndex, 7, teamType);
                return List.of(leftSang, leftMa, rightSang, rightMa);
            }

            case SANG_MA_SANG_MA -> {
                Sang leftSang = Sang.create(rowIndex, 1, teamType);
                Ma leftMa = Ma.create(rowIndex, 2, teamType);
                Sang rightSang = Sang.create(rowIndex, 6, teamType);
                Ma rightMa = Ma.create(rowIndex, 7, teamType);
                return List.of(leftSang, leftMa, rightSang, rightMa);
            }

            case SANG_MA_MA_SANG -> {
                Sang leftSang = Sang.create(rowIndex, 1, teamType);
                Ma leftMa = Ma.create(rowIndex, 2, teamType);
                Ma rightMa = Ma.create(rowIndex, 6, teamType);
                Sang rightSang = Sang.create(rowIndex, 7, teamType);
                return List.of(leftSang, leftMa, rightSang, rightMa);
            }

            case MA_SANG_SANG_MA -> {
                Ma leftMa = Ma.create(rowIndex, 1, teamType);
                Sang leftSang = Sang.create(rowIndex, 2, teamType);
                Sang rightSang = Sang.create(rowIndex, 6, teamType);
                Ma rightMa = Ma.create(rowIndex, 7, teamType);
                return List.of(leftSang, leftMa, rightSang, rightMa);
            }
        }
        return List.of();
    }
}
