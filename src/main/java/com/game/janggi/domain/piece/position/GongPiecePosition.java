package com.game.janggi.domain.piece.position;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
public enum GongPiecePosition {

    HAN_01(PiecePosition.create(3, 0), PiecePosition.create(5,2), "한우하귀", true),
    HAN_02(PiecePosition.create(4, 0), null, "한하중", false),
    HAN_03(PiecePosition.create(5, 0), PiecePosition.create(3,2), "한좌하귀", true),
    HAN_04(PiecePosition.create(3, 1), null, "한우귀", false),
    HAN_05(PiecePosition.create(4, 1), null, "한중앙", true),
    HAN_06(PiecePosition.create(5, 1), null, "한좌귀", false),
    HAN_07(PiecePosition.create(3, 2), PiecePosition.create(5,0), "한우상귀", true),
    HAN_08(PiecePosition.create(4, 2), null, "한상중", false),
    HAN_09(PiecePosition.create(5, 2), PiecePosition.create(3,0), "한좌상귀", true),

    CHO_01(PiecePosition.create(3, 9), PiecePosition.create(5,7), "초좌하귀", true),
    CHO_02(PiecePosition.create(4, 9), null, "초하중", false),
    CHO_03(PiecePosition.create(5, 9), PiecePosition.create(3,7), "초우하귀", true),
    CHO_04(PiecePosition.create(3, 8), null, "초좌귀", false),
    CHO_05(PiecePosition.create(4, 8), null, "초중앙", true),
    CHO_06(PiecePosition.create(5, 8), null, "초우귀", false),
    CHO_07(PiecePosition.create(3, 7), PiecePosition.create(5,9), "초좌상귀", true),
    CHO_08(PiecePosition.create(4, 7), null, "초상중", false),
    CHO_09(PiecePosition.create(5, 7), PiecePosition.create(3,9), "초우상귀", true);

    private final PiecePosition position;
    private final PiecePosition oppositePosition;
    private final String description;
    private final boolean canMoveDiagonal;

    private static final PiecePosition HAN_CENTER = HAN_05.position;
    private static final PiecePosition CHO_CENTER = CHO_05.position;

    public static List<PiecePosition> getGongPositions() {
        return Arrays.stream(values())
                .map(it -> it.position)
                .toList();
    }

    public static List<PiecePosition> getPoCanDiagonalGongPositions() {
        return Arrays.stream(values())
                .filter(it -> it.oppositePosition != null)
                .map(it -> it.position)
                .toList();
    }

    public static List<PiecePosition> getHanDiagonalGongPositions() {
        return Arrays.stream(values())
                .filter(it -> it.description.contains("한"))
                .map(it -> it.position)
                .toList();
    }

    public static PiecePosition getGongCenterPosition(PiecePosition piecePosition) {
        if (GongPiecePosition.getHanDiagonalGongPositions().contains(piecePosition)) {
            return HAN_CENTER;
        }

        return CHO_CENTER;
    }

    public static PiecePosition getOppositeGongPosition(PiecePosition piecePosition) {
        return Arrays.stream(values())
                .filter(it -> it.position.equals(piecePosition))
                .findFirst()
                .map(it -> it.oppositePosition)
                .orElseThrow(() -> new IllegalArgumentException("해당 위치의 반대 위치가 없습니다."));
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
