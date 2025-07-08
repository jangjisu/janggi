package com.game.janggi.domain.piece.move;

import com.game.janggi.domain.piece.Ma;
import com.game.janggi.domain.piece.Piece;
import com.game.janggi.domain.piece.Sang;
import com.game.janggi.domain.piece.position.PiecePosition;
import com.game.janggi.domain.team.TeamType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class MaMovePositionTest {
    Map<PiecePosition, Piece> pieces = new HashMap<>();

    MovePosition movePosition = MovePosition.createMaMove();

    @AfterEach
    void tearDown() {
        pieces.clear();
    }

    @Test
    @DisplayName("마는 상,하,좌,우로 한칸 대각선으로 한칸 씩 이동할 수 있다.")
    void move() {
        //given
        PiecePosition piecePosition = PiecePosition.create(4, 3);
        pieces.put(piecePosition, Ma.create(TeamType.HAN));

        //when
        List<PiecePosition> canMovePositions = movePosition.getMoveablePosition(pieces, piecePosition);

        //then
        assertThat(canMovePositions).hasSize(8)
                .containsExactlyInAnyOrder(
                        PiecePosition.create(3, 5),
                        PiecePosition.create(5, 5),
                        PiecePosition.create(3, 1),
                        PiecePosition.create(5, 1),
                        PiecePosition.create(2, 4),
                        PiecePosition.create(2, 2),
                        PiecePosition.create(6, 4),
                        PiecePosition.create(6, 2)
                );

    }

    @Test
    @DisplayName("마는 중간 길에 장애물이 있다면 이동할 수 없다.")
    void moveWhenObstacle() {
        //given
        PiecePosition piecePosition = PiecePosition.create(4, 3);
        pieces.put(piecePosition, Ma.create(TeamType.HAN));
        pieces.put(PiecePosition.create(4, 2), Ma.create(TeamType.HAN));
        pieces.put(PiecePosition.create(4, 4), Ma.create(TeamType.HAN));
        pieces.put(PiecePosition.create(3, 3), Sang.create(TeamType.CHO));
        pieces.put(PiecePosition.create(5, 3), Sang.create(TeamType.CHO));

        //when
        List<PiecePosition> canMovePositions = movePosition.getMoveablePosition(pieces, piecePosition);

        //then
        assertThat(canMovePositions).isEmpty();
    }

    @Test
    @DisplayName("마는 도착할 곳에 상대편 기물이 있어도 이동할 수 있다.")
    void moveWhenEnemy() {
        //given
        PiecePosition piecePosition = PiecePosition.create(4, 3);
        pieces.put(piecePosition, Ma.create(TeamType.HAN));
        pieces.put(PiecePosition.create(3, 5), Ma.create(TeamType.CHO));
        pieces.put(PiecePosition.create(5, 5), Ma.create(TeamType.CHO));
        pieces.put(PiecePosition.create(3, 1), Ma.create(TeamType.CHO));
        pieces.put(PiecePosition.create(5, 1), Ma.create(TeamType.CHO));
        pieces.put(PiecePosition.create(2, 4), Ma.create(TeamType.CHO));
        pieces.put(PiecePosition.create(2, 2), Ma.create(TeamType.CHO));
        pieces.put(PiecePosition.create(6, 4), Ma.create(TeamType.CHO));
        pieces.put(PiecePosition.create(6, 2), Ma.create(TeamType.CHO));


        //when
        List<PiecePosition> canMovePositions = movePosition.getMoveablePosition(pieces, piecePosition);

        //then
        assertThat(canMovePositions).hasSize(8)
                .containsExactlyInAnyOrder(
                        PiecePosition.create(3, 5),
                        PiecePosition.create(5, 5),
                        PiecePosition.create(3, 1),
                        PiecePosition.create(5, 1),
                        PiecePosition.create(2, 4),
                        PiecePosition.create(2, 2),
                        PiecePosition.create(6, 4),
                        PiecePosition.create(6, 2)
                );
    }

    @Test
    @DisplayName("마는 도착할 곳에 같은편 기물이 있으면 이동할 수 없다.")
    void moveWhenTeam() {
        //given
        PiecePosition piecePosition = PiecePosition.create(4, 3);
        pieces.put(piecePosition, Ma.create(TeamType.HAN));
        pieces.put(PiecePosition.create(3, 5), Sang.create(TeamType.HAN));
        pieces.put(PiecePosition.create(5, 5), Sang.create(TeamType.HAN));
        pieces.put(PiecePosition.create(3, 1), Sang.create(TeamType.HAN));
        pieces.put(PiecePosition.create(5, 1), Sang.create(TeamType.HAN));
        pieces.put(PiecePosition.create(2, 4), Sang.create(TeamType.HAN));
        pieces.put(PiecePosition.create(2, 2), Sang.create(TeamType.HAN));
        pieces.put(PiecePosition.create(6, 4), Sang.create(TeamType.HAN));
        pieces.put(PiecePosition.create(6, 2), Sang.create(TeamType.HAN));


        //when
        List<PiecePosition> canMovePositions = movePosition.getMoveablePosition(pieces, piecePosition);

        //then
        assertThat(canMovePositions).isEmpty();
    }

}