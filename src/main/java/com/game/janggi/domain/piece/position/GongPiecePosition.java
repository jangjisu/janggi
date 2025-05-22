package com.game.janggi.domain.piece.position;

import com.game.janggi.domain.team.TeamType;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public enum GongPiecePosition {

    HAN_01(PiecePosition.create(3, 0), TeamType.HAN, "우하귀", true),
    HAN_02(PiecePosition.create(4, 0), TeamType.HAN, "하중", false),
    HAN_03(PiecePosition.create(5, 0), TeamType.HAN, "좌하귀", true),
    HAN_04(PiecePosition.create(3, 1), TeamType.HAN, "우귀", false),
    HAN_05(PiecePosition.create(4, 1), TeamType.HAN, "중앙", true),
    HAN_06(PiecePosition.create(5, 1), TeamType.HAN, "좌귀", false),
    HAN_07(PiecePosition.create(3, 2), TeamType.HAN, "우상귀", true),
    HAN_08(PiecePosition.create(4, 2), TeamType.HAN, "상중", false),
    HAN_09(PiecePosition.create(5, 2), TeamType.HAN, "좌상귀", true),

    CHO_01(PiecePosition.create(3, 9), TeamType.CHO, "좌하귀", true),
    CHO_02(PiecePosition.create(4, 9), TeamType.CHO, "하중", false),
    CHO_03(PiecePosition.create(5, 9), TeamType.CHO, "우하귀", true),
    CHO_04(PiecePosition.create(3, 8), TeamType.CHO, "좌귀", false),
    CHO_05(PiecePosition.create(4, 8), TeamType.CHO, "중앙", true),
    CHO_06(PiecePosition.create(5, 8), TeamType.CHO, "우귀", false),
    CHO_07(PiecePosition.create(3, 7), TeamType.CHO, "좌상귀", true),
    CHO_08(PiecePosition.create(4, 7), TeamType.CHO, "상중", false),
    CHO_09(PiecePosition.create(5, 7), TeamType.CHO, "우상귀", true);

    private final PiecePosition position;
    private final TeamType teamType;
    private final String description;
    private final boolean canMoveDiagonal;

    public static List<PiecePosition> getGongPositions(TeamType teamType) {
        if (teamType == TeamType.HAN) {
            return List.of(
                    HAN_01.position, HAN_02.position, HAN_03.position,
                    HAN_04.position, HAN_05.position, HAN_06.position,
                    HAN_07.position, HAN_08.position, HAN_09.position);
        }

        return List.of(
                CHO_01.position, CHO_02.position, CHO_03.position,
                CHO_04.position, CHO_05.position, CHO_06.position,
                CHO_07.position, CHO_08.position, CHO_09.position);
    }

    public static boolean isInGongPosition(PiecePosition piecePosition, TeamType teamType) {
        return getGongPositions(teamType)
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
