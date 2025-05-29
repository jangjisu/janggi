package com.game.janggi.domain.piece.position;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public enum GongPiecePosition {

    HAN_01(PiecePosition.create(3, 0), "한우하귀", true),
    HAN_02(PiecePosition.create(4, 0), "한하중", false),
    HAN_03(PiecePosition.create(5, 0), "한좌하귀", true),
    HAN_04(PiecePosition.create(3, 1), "한우귀", false),
    HAN_05(PiecePosition.create(4, 1), "한중앙", true),
    HAN_06(PiecePosition.create(5, 1), "한좌귀", false),
    HAN_07(PiecePosition.create(3, 2), "한우상귀", true),
    HAN_08(PiecePosition.create(4, 2), "한상중", false),
    HAN_09(PiecePosition.create(5, 2), "한좌상귀", true),

    CHO_01(PiecePosition.create(3, 9), "초좌하귀", true),
    CHO_02(PiecePosition.create(4, 9), "초하중", false),
    CHO_03(PiecePosition.create(5, 9), "초우하귀", true),
    CHO_04(PiecePosition.create(3, 8), "초좌귀", false),
    CHO_05(PiecePosition.create(4, 8), "초중앙", true),
    CHO_06(PiecePosition.create(5, 8), "초우귀", false),
    CHO_07(PiecePosition.create(3, 7), "초좌상귀", true),
    CHO_08(PiecePosition.create(4, 7), "초상중", false),
    CHO_09(PiecePosition.create(5, 7), "초우상귀", true);

    private final PiecePosition position;
    private final String description;
    private final boolean canMoveDiagonal;

    public static List<PiecePosition> getGongPositions() {
        return List.of(
                HAN_01.position, HAN_02.position, HAN_03.position,
                HAN_04.position, HAN_05.position, HAN_06.position,
                HAN_07.position, HAN_08.position, HAN_09.position,
                CHO_01.position, CHO_02.position, CHO_03.position,
                CHO_04.position, CHO_05.position, CHO_06.position,
                CHO_07.position, CHO_08.position, CHO_09.position);
    }

    public static boolean isInGongPosition(PiecePosition piecePosition) {
        return getGongPositions()
                .stream()
                .anyMatch(pp -> pp.equals(piecePosition));
    }

    public static boolean canMoveDiagonal(PiecePosition piecePosition) {
        for (GongPiecePosition gongPiecePosition : GongPiecePosition.values()) {
            if (gongPiecePosition.position.equals(piecePosition)) {
                return gongPiecePosition.canMoveDiagonal;
            }
        }

        return false;
    }
}
