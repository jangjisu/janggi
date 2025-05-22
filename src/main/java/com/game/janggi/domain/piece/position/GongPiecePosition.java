package com.game.janggi.domain.piece.position;

import com.game.janggi.domain.team.TeamType;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public enum GongPiecePosition {

    HAN_01(PiecePosition.create(3, 0), TeamType.HAN, "우하귀"),
    HAN_02(PiecePosition.create(4, 0), TeamType.HAN, "하중"),
    HAN_03(PiecePosition.create(5, 0), TeamType.HAN, "좌하귀"),
    HAN_04(PiecePosition.create(3, 1), TeamType.HAN, "우귀"),
    HAN_05(PiecePosition.create(4, 1), TeamType.HAN, "중앙"),
    HAN_06(PiecePosition.create(5, 1), TeamType.HAN, "좌귀"),
    HAN_07(PiecePosition.create(3, 2), TeamType.HAN, "우상귀"),
    HAN_08(PiecePosition.create(4, 2), TeamType.HAN, "상중"),
    HAN_09(PiecePosition.create(5, 2), TeamType.HAN, "좌상귀"),

    CHO_01(PiecePosition.create(3, 9), TeamType.CHO, "좌하귀"),
    CHO_02(PiecePosition.create(4, 9), TeamType.CHO, "하중"),
    CHO_03(PiecePosition.create(5, 9), TeamType.CHO, "우하귀"),
    CHO_04(PiecePosition.create(3, 8), TeamType.CHO, "좌귀"),
    CHO_05(PiecePosition.create(4, 8), TeamType.CHO, "중앙"),
    CHO_06(PiecePosition.create(5, 8), TeamType.CHO, "우귀"),
    CHO_07(PiecePosition.create(3, 7), TeamType.CHO, "좌상귀"),
    CHO_08(PiecePosition.create(4, 7), TeamType.CHO, "상중"),
    CHO_09(PiecePosition.create(5, 7), TeamType.CHO, "우상귀");

    private final PiecePosition position;
    private final TeamType teamType;
    private final String description;

    public static List<PiecePosition> getGongPositions() {
        return List.of(HAN_01.position, HAN_02.position, HAN_03.position,
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
}
