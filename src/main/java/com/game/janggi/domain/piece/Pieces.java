package com.game.janggi.domain.piece;

import com.game.janggi.domain.formation.FormationType;
import com.game.janggi.domain.team.TeamType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pieces {
    private List<Piece> pieces;

    private Pieces(List<Piece> pieces) {
        this.pieces = pieces;
    }

    public static Pieces create(FormationType formationType, TeamType teamtype) {
        List<Piece> pieces = new ArrayList<>();
        if (teamtype == TeamType.HAN) {
            pieces.addAll(createFixedHanPieces());
            pieces.addAll(createFluidHanPieces(formationType));
            return new Pieces(pieces);
        }

        pieces.addAll(createFixedChoPieces());
        pieces.addAll(createFluidChoPieces(formationType));
        return new Pieces(pieces);
    }

    private static List<Piece> createFixedHanPieces() {
        Cha leftCha = Cha.create(9, 0);
        Cha rightCha = Cha.create(9, 8);
        Sa leftSa = Sa.create(9, 3);
        Sa rightSa = Sa.create(9, 5);
        King king = King.create(8, 4);
        Po leftPo = Po.create(7, 1);
        Po rightPo = Po.create(7, 7);
        Bung bung1 = Bung.create(6, 0);
        Bung bung2 = Bung.create(6, 2);
        Bung bung3 = Bung.create(6, 4);
        Bung bung4 = Bung.create(6, 6);
        Bung bung5 = Bung.create(6, 8);

        return Arrays.asList(leftCha, rightCha, leftSa, rightSa, king, leftPo, rightPo, bung1, bung2, bung3, bung4, bung5);

    }

    private static List<Piece> createFluidHanPieces(FormationType formationType) {
        return createFluidPiecesOnRow(formationType, 9);
    }

    private static List<Piece> createFixedChoPieces() {
        Cha leftCha = Cha.create(0, 0);
        Cha rightCha = Cha.create(0, 8);
        Sa leftSa = Sa.create(0, 3);
        Sa rightSa = Sa.create(0, 5);
        King king = King.create(1, 4);
        Po leftPo = Po.create(2, 1);
        Po rightPo = Po.create(2, 7);
        Bung bung1 = Bung.create(3, 0);
        Bung bung2 = Bung.create(3, 2);
        Bung bung3 = Bung.create(3, 4);
        Bung bung4 = Bung.create(3, 6);
        Bung bung5 = Bung.create(3, 8);

        return Arrays.asList(leftCha, rightCha, leftSa, rightSa, king, leftPo, rightPo, bung1, bung2, bung3, bung4, bung5);
    }

    private static List<Piece> createFluidChoPieces(FormationType formationType) {
        return createFluidPiecesOnRow(formationType, 0);
    }

    private static List<Piece> createFluidPiecesOnRow(FormationType formationType, int rowIndex) {
        if (formationType == FormationType.SANG_MA_MA_SANG) {
            Sang leftSang = Sang.create(rowIndex, 1);
            Ma leftMa = Ma.create(rowIndex, 2);
            Ma rightMa = Ma.create(rowIndex, 6);
            Sang rightSang = Sang.create(rowIndex, 7);
            return Arrays.asList(leftSang, leftMa, rightSang, rightMa);
        }

        if (formationType == FormationType.SANG_MA_SANG_MA) {
            Sang leftSang = Sang.create(rowIndex, 1);
            Ma leftMa = Ma.create(rowIndex, 2);
            Sang rightSang = Sang.create(rowIndex, 6);
            Ma rightMa = Ma.create(rowIndex, 7);
            return Arrays.asList(leftSang, leftMa, rightSang, rightMa);
        }

        if (formationType == FormationType.MA_SANG_MA_SANG) {
            Ma leftMa = Ma.create(rowIndex, 1);
            Sang leftSang = Sang.create(rowIndex, 2);
            Ma rightMa = Ma.create(rowIndex, 6);
            Sang rightSang = Sang.create(rowIndex, 7);
            return Arrays.asList(leftSang, leftMa, rightSang, rightMa);
        }

        Ma leftMa = Ma.create(rowIndex, 1);
        Sang leftSang = Sang.create(rowIndex, 2);
        Sang rightSang = Sang.create(rowIndex, 6);
        Ma rightMa = Ma.create(rowIndex, 7);
        return Arrays.asList(leftSang, leftMa, rightSang, rightMa);
    }
}
