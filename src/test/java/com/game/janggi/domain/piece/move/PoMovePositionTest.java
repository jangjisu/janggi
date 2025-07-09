package com.game.janggi.domain.piece.move;

import com.game.janggi.domain.piece.Cha;
import com.game.janggi.domain.piece.Piece;
import com.game.janggi.domain.piece.Po;
import com.game.janggi.domain.piece.position.PiecePosition;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.game.janggi.domain.team.TeamType.CHO;
import static com.game.janggi.domain.team.TeamType.HAN;
import static org.assertj.core.api.Assertions.assertThat;

class PoMovePositionTest {
    Map<PiecePosition, Piece> pieces = new HashMap<>();

    MovePosition movePosition = MovePosition.createPoMove();

    @AfterEach
    void tearDown() {
        pieces.clear();
    }

    @Test
    @DisplayName("포는 중간에 기물이 없으면 움직일 수 없다")
    void po_cannot_move_without_jump_piece() {
        // given
        PiecePosition position = PiecePosition.create(0, 0);
        pieces.put(position, Po.create(CHO));

        // when
        List<PiecePosition> moveAblePosition = movePosition.getMoveablePosition(pieces, position);

        // then
        assertThat(moveAblePosition).isEmpty();
    }

    @Test
    @DisplayName("포 바로 앞에 기물이 있으면 끝까지 이동할 수 있다")
    void po_can_move_with_immediate_jump_piece() {
        // given
        PiecePosition position = PiecePosition.create(0, 0);
        pieces.put(position, Po.create(CHO));
        pieces.put(PiecePosition.create(1, 0), Cha.create(HAN));

        // when
        List<PiecePosition> moveAblePosition = movePosition.getMoveablePosition(pieces, position);

        // then
        assertThat(moveAblePosition).hasSize(7)
                .containsExactlyInAnyOrder(
                        PiecePosition.create(2, 0),
                        PiecePosition.create(3, 0),
                        PiecePosition.create(4, 0),
                        PiecePosition.create(5, 0),
                        PiecePosition.create(6, 0),
                        PiecePosition.create(7, 0),
                        PiecePosition.create(8, 0)
                );
    }

    @Test
    @DisplayName("뛰어넘을 기물이 한 칸 떨어져 있어도 끝까지 이동할 수 있다")
    void po_can_move_with_offset_jump_piece() {
        // given
        PiecePosition position = PiecePosition.create(0, 0);
        pieces.put(position, Po.create(CHO));
        pieces.put(PiecePosition.create(2, 0), Cha.create(HAN));

        // when
        List<PiecePosition> moveAblePosition = movePosition.getMoveablePosition(pieces, position);

        // then
        assertThat(moveAblePosition).hasSize(6)
                .containsExactlyInAnyOrder(
                        PiecePosition.create(3, 0),
                        PiecePosition.create(4, 0),
                        PiecePosition.create(5, 0),
                        PiecePosition.create(6, 0),
                        PiecePosition.create(7, 0),
                        PiecePosition.create(8, 0)
                );
    }

    @Test
    @DisplayName("앞에 같은 팀의 포가 있으면 포는 움직일 수 없다")
    void po_cannot_jump_over_po_same_team() {
        // given
        PiecePosition position = PiecePosition.create(0, 0);
        pieces.put(position, Po.create(CHO));
        pieces.put(PiecePosition.create(2, 0), Po.create(CHO));

        // when
        List<PiecePosition> moveAblePosition = movePosition.getMoveablePosition(pieces, position);

        // then
        assertThat(moveAblePosition).isEmpty();
    }

    @Test
    @DisplayName("앞에 상대 팀의 포가 있어도 포는 움직일 수 없다")
    void po_cannot_jump_over_po_enemy_team() {
        // given
        PiecePosition position = PiecePosition.create(0, 0);
        pieces.put(position, Po.create(CHO));
        pieces.put(PiecePosition.create(2, 0), Po.create(HAN));

        // when
        List<PiecePosition> moveAblePosition = movePosition.getMoveablePosition(pieces, position);

        // then
        assertThat(moveAblePosition).isEmpty();
    }

    @Test
    @DisplayName("뛰어넘은 후 포가 막고 있으면 그 전까지만 이동할 수 있다")
    void po_stops_before_blocking_po() {
        // given
        PiecePosition position = PiecePosition.create(0, 0);
        pieces.put(position, Po.create(CHO));
        pieces.put(PiecePosition.create(1, 0), Cha.create(CHO));
        pieces.put(PiecePosition.create(3, 0), Po.create(HAN));

        // when
        List<PiecePosition> moveAblePosition = movePosition.getMoveablePosition(pieces, position);

        // then
        assertThat(moveAblePosition).hasSize(1);

    }

    @Test
    @DisplayName("뛰어넘은 후 상대 기물이 있으면 그 칸까지 이동할 수 있다")
    void po_can_capture_enemy_after_jump() {
        // given
        PiecePosition position = PiecePosition.create(0, 0);
        pieces.put(position, Po.create(CHO));
        pieces.put(PiecePosition.create(1, 0), Cha.create(CHO));
        pieces.put(PiecePosition.create(3, 0), Cha.create(HAN));

        // when
        List<PiecePosition> moveAblePosition = movePosition.getMoveablePosition(pieces, position);

        // then
        assertThat(moveAblePosition).hasSize(2)
                .containsExactlyInAnyOrder(
                        PiecePosition.create(2, 0),
                        PiecePosition.create(3, 0)
                );
    }

    @Test
    @DisplayName("뛰어넘은 후 같은 팀 기물이 있으면 그 전까지만 이동 가능")
    void po_blocked_by_own_team_after_jump() {
        // given
        PiecePosition position = PiecePosition.create(0, 0);
        pieces.put(position, Po.create(CHO));
        pieces.put(PiecePosition.create(1, 0), Cha.create(CHO));
        pieces.put(PiecePosition.create(3, 0), Cha.create(CHO));

        // when
        List<PiecePosition> moveAblePosition = movePosition.getMoveablePosition(pieces, position);

        // then
        assertThat(moveAblePosition).hasSize(1)
                .containsExactlyInAnyOrder(
                        PiecePosition.create(2, 0)
                );
    }

    @Test
    @DisplayName("포가 궁성 안에서 중앙에 기물이 있을 때 대각선으로 이동 가능")
    void po_diagonal_move_valid_from_gong() {
        // given
        PiecePosition position = PiecePosition.create(3, 7);
        pieces.put(position, Po.create(CHO));
        pieces.put(PiecePosition.create(4, 8), Cha.create(HAN));

        // when
        List<PiecePosition> moveAblePosition = movePosition.getMoveablePosition(pieces, position);

        // then
        assertThat(moveAblePosition).hasSize(1)
                .containsExactlyInAnyOrder(
                        PiecePosition.create(5, 9)
                );
    }

    @Test
    @DisplayName("포가 궁성 안에서 중앙에 기물이 있을 때 대각선으로 이동 가능")
    void po_diagonal_move_valid_from_gong_cha() {
        // given
        PiecePosition position = PiecePosition.create(3, 7);
        pieces.put(position, Po.create(CHO));
        pieces.put(PiecePosition.create(4, 8), Cha.create(HAN));
        pieces.put(PiecePosition.create(5, 9), Cha.create(HAN));

        // when
        List<PiecePosition> moveAblePosition = movePosition.getMoveablePosition(pieces, position);

        // then
        assertThat(moveAblePosition).hasSize(1)
                .containsExactlyInAnyOrder(
                        PiecePosition.create(5, 9)
                );
    }

    @Test
    @DisplayName("포가 궁성 안에서 중앙에 기물이 있을 고 대각선기물이 팀이면 이동 불가")
    void po_diagonal_move_valid_from_gong_team() {
        // given
        PiecePosition position = PiecePosition.create(3, 7);
        pieces.put(position, Po.create(CHO));
        pieces.put(PiecePosition.create(4, 8), Cha.create(HAN));
        pieces.put(PiecePosition.create(5, 9), Cha.create(CHO));

        // when
        List<PiecePosition> moveAblePosition = movePosition.getMoveablePosition(pieces, position);

        // then
        assertThat(moveAblePosition).isEmpty();
    }

    @Test
    @DisplayName("포가 궁성 안에서 중앙에 기물이 있을 때 반대편에 포가 있을경우 대각선으로 불가")
    void po_diagonal_move_valid_from_gong_po() {
        // given
        PiecePosition position = PiecePosition.create(3, 7);
        pieces.put(position, Po.create(CHO));
        pieces.put(PiecePosition.create(4, 8), Cha.create(HAN));
        pieces.put(PiecePosition.create(5, 9), Po.create(HAN));

        // when
        List<PiecePosition> moveAblePosition = movePosition.getMoveablePosition(pieces, position);

        // then
        assertThat(moveAblePosition).isEmpty();
    }

    @Test
    @DisplayName("포가 궁성에 있고 중앙기물이 있을 때 반대편 기물이 포라면 대각선 이동 불가능")
    void po_diagonal_move_not_allowed_center_piece_po() {
        // given
        PiecePosition position = PiecePosition.create(3, 7);
        pieces.put(position, Po.create(HAN));
        pieces.put(PiecePosition.create(4, 8), Po.create(CHO));

        // when
        List<PiecePosition> moveAblePosition = movePosition.getMoveablePosition(pieces, position);

        // then
        assertThat(moveAblePosition).isEmpty();
    }

    @Test
    @DisplayName("궁성에서 중앙에 기물이 없으면 대각선 이동 불가능")
    void po_diagonal_move_not_allowed_without_center_piece() {
        // given
        PiecePosition position = PiecePosition.create(3, 7);
        pieces.put(position, Po.create(CHO));

        // when
        List<PiecePosition> moveAblePosition = movePosition.getMoveablePosition(pieces, position);

        // then
        assertThat(moveAblePosition).isEmpty();
    }
}