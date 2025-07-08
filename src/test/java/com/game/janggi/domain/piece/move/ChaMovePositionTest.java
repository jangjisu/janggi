package com.game.janggi.domain.piece.move;

import com.game.janggi.domain.piece.Bung;
import com.game.janggi.domain.piece.Cha;
import com.game.janggi.domain.piece.Jol;
import com.game.janggi.domain.piece.Piece;
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

class ChaMovePositionTest {
    Map<PiecePosition, Piece> pieces = new HashMap<>();

    MovePosition movePosition = MovePosition.createChaMove();

    @AfterEach
    void tearDown() {
        pieces.clear();
    }

    @Test
    @DisplayName("기물이 없을 때 상하좌우 끝까지 이동 가능하다.")
    void move_in_all_directions_when_no_obstacle() {
        // given
        PiecePosition position = PiecePosition.create(4, 4);
        pieces.put(position, Cha.create(CHO));

        // when
        List<PiecePosition> moveAblePosition = movePosition.getMoveablePosition(pieces, position);

        // then
        assertThat(moveAblePosition).hasSize(17)
                .containsExactlyInAnyOrder(
                        PiecePosition.create(4, 0),
                        PiecePosition.create(4, 1),
                        PiecePosition.create(4, 2),
                        PiecePosition.create(4, 3),
                        PiecePosition.create(4, 5),
                        PiecePosition.create(4, 6),
                        PiecePosition.create(4, 7),
                        PiecePosition.create(4, 8),
                        PiecePosition.create(4, 9),
                        PiecePosition.create(0, 4),
                        PiecePosition.create(1, 4),
                        PiecePosition.create(2, 4),
                        PiecePosition.create(3, 4),
                        PiecePosition.create(5, 4),
                        PiecePosition.create(6, 4),
                        PiecePosition.create(7, 4),
                        PiecePosition.create(8, 4)
                );
    }


    @Test
    @DisplayName("같은 팀 기물이 앞에 있으면 그 전까지만 이동한다.")
    void stop_before_own_piece() {
        // given
        PiecePosition position = PiecePosition.create(4, 4);
        pieces.put(position, Cha.create(CHO));
        pieces.put(PiecePosition.create(4, 7), Jol.create(CHO));

        // when
        List<PiecePosition> moveAblePosition = movePosition.getMoveablePosition(pieces, position);

        // then
        assertThat(moveAblePosition).hasSize(14)
                .containsExactlyInAnyOrder(
                        PiecePosition.create(4, 0),
                        PiecePosition.create(4, 1),
                        PiecePosition.create(4, 2),
                        PiecePosition.create(4, 3),
                        PiecePosition.create(4, 5),
                        PiecePosition.create(4, 6),
                        PiecePosition.create(0, 4),
                        PiecePosition.create(1, 4),
                        PiecePosition.create(2, 4),
                        PiecePosition.create(3, 4),
                        PiecePosition.create(5, 4),
                        PiecePosition.create(6, 4),
                        PiecePosition.create(7, 4),
                        PiecePosition.create(8, 4)
                );
    }

    @Test
    @DisplayName("상대 기물이 앞에 있으면 그 자리까지 이동 가능하다.")
    void can_capture_enemy_piece() {
        // given
        PiecePosition position = PiecePosition.create(4, 4);
        pieces.put(position, Cha.create(CHO));
        pieces.put(PiecePosition.create(4, 7), Bung.create(HAN));

        // when
        List<PiecePosition> moveAblePosition = movePosition.getMoveablePosition(pieces, position);

        // then
        assertThat(moveAblePosition).hasSize(15)
                .containsExactlyInAnyOrder(
                        PiecePosition.create(4, 0),
                        PiecePosition.create(4, 1),
                        PiecePosition.create(4, 2),
                        PiecePosition.create(4, 3),
                        PiecePosition.create(4, 5),
                        PiecePosition.create(4, 6),
                        PiecePosition.create(4, 7),
                        PiecePosition.create(0, 4),
                        PiecePosition.create(1, 4),
                        PiecePosition.create(2, 4),
                        PiecePosition.create(3, 4),
                        PiecePosition.create(5, 4),
                        PiecePosition.create(6, 4),
                        PiecePosition.create(7, 4),
                        PiecePosition.create(8, 4)
                );
    }


    @Test
    @DisplayName("상하좌우가 같은 팀 기물로 막혀있으면 이동할 수 없다.")
    void no_move_when_surrounded_by_own_team() {
        // given
        PiecePosition position = PiecePosition.create(4, 4);
        pieces.put(position, Cha.create(CHO));
        pieces.put(PiecePosition.create(3, 4), Jol.create(CHO));
        pieces.put(PiecePosition.create(5, 4), Jol.create(CHO));
        pieces.put(PiecePosition.create(4, 3), Jol.create(CHO));
        pieces.put(PiecePosition.create(4, 5), Jol.create(CHO));

        // when
        List<PiecePosition> moveAblePosition = movePosition.getMoveablePosition(pieces, position);

        // then
        assertThat(moveAblePosition).isEmpty();
    }

    @DisplayName("차는 궁성안에서는 대각선으로 이동할 수 있다.")
    @Test
    void can_move_digonal_on_Gong() {
        // given
        PiecePosition position = PiecePosition.create(4, 1);
        pieces.put(position, Cha.create(CHO));
        // when
        List<PiecePosition> moveAblePosition = movePosition.getMoveablePosition(pieces, position);

        // then
        assertThat(moveAblePosition).hasSize(21)
                .containsExactlyInAnyOrder(
                        PiecePosition.create(3, 0),
                        PiecePosition.create(3, 1),
                        PiecePosition.create(3, 2),
                        PiecePosition.create(5, 0),
                        PiecePosition.create(5, 1),
                        PiecePosition.create(5, 2),
                        PiecePosition.create(4, 0),
                        PiecePosition.create(4, 2),
                        PiecePosition.create(4, 3),
                        PiecePosition.create(4, 4),
                        PiecePosition.create(4, 5),
                        PiecePosition.create(4, 6),
                        PiecePosition.create(4, 7),
                        PiecePosition.create(4, 8),
                        PiecePosition.create(4, 9),
                        PiecePosition.create(0, 1),
                        PiecePosition.create(1, 1),
                        PiecePosition.create(2, 1),
                        PiecePosition.create(6, 1),
                        PiecePosition.create(7, 1),
                        PiecePosition.create(8, 1)
                );
    }

    @DisplayName("차는 궁성안에서는 대각선으로 이동할 수 있다.")
    @Test
    void can_move_digonal_on_Gong2() {
        // given
        PiecePosition position = PiecePosition.create(3, 0);
        pieces.put(position, Cha.create(CHO));
        // when
        List<PiecePosition> moveAblePosition = movePosition.getMoveablePosition(pieces, position);

        // then
        assertThat(moveAblePosition).hasSize(19)
                .containsExactlyInAnyOrder(
                        PiecePosition.create(0, 0),
                        PiecePosition.create(1, 0),
                        PiecePosition.create(2, 0),
                        PiecePosition.create(4, 0),
                        PiecePosition.create(5, 0),
                        PiecePosition.create(6, 0),
                        PiecePosition.create(7, 0),
                        PiecePosition.create(8, 0),
                        PiecePosition.create(3, 1),
                        PiecePosition.create(3, 2),
                        PiecePosition.create(3, 3),
                        PiecePosition.create(3, 4),
                        PiecePosition.create(3, 5),
                        PiecePosition.create(3, 6),
                        PiecePosition.create(3, 7),
                        PiecePosition.create(3, 8),
                        PiecePosition.create(3, 9),
                        PiecePosition.create(4, 1),
                        PiecePosition.create(5, 2)
                );
    }

    @DisplayName("차는 궁성안에서는 대각선으로 이동할 수 있다.")
    @Test
    void can_move_digonal_on_Gong_Meet_Opponent() {
        // given
        PiecePosition position = PiecePosition.create(3, 0);
        pieces.put(position, Cha.create(CHO));
        pieces.put(PiecePosition.create(5,2), Cha.create(HAN));
        // when
        List<PiecePosition> moveAblePosition = movePosition.getMoveablePosition(pieces, position);

        // then
        assertThat(moveAblePosition).hasSize(19)
                .containsExactlyInAnyOrder(
                        PiecePosition.create(0, 0),
                        PiecePosition.create(1, 0),
                        PiecePosition.create(2, 0),
                        PiecePosition.create(4, 0),
                        PiecePosition.create(5, 0),
                        PiecePosition.create(6, 0),
                        PiecePosition.create(7, 0),
                        PiecePosition.create(8, 0),
                        PiecePosition.create(3, 1),
                        PiecePosition.create(3, 2),
                        PiecePosition.create(3, 3),
                        PiecePosition.create(3, 4),
                        PiecePosition.create(3, 5),
                        PiecePosition.create(3, 6),
                        PiecePosition.create(3, 7),
                        PiecePosition.create(3, 8),
                        PiecePosition.create(3, 9),
                        PiecePosition.create(4, 1),
                        PiecePosition.create(5, 2)
                );
    }

    @DisplayName("차는 궁성안에서는 대각선으로 이동할 수 있다.")
    @Test
    void can_move_digonal_on_Gong_Meet_Team() {
        // given
        PiecePosition position = PiecePosition.create(3, 0);
        pieces.put(position, Cha.create(CHO));
        pieces.put(PiecePosition.create(5,2), Cha.create(CHO));
        // when
        List<PiecePosition> moveAblePosition = movePosition.getMoveablePosition(pieces, position);

        // then
        assertThat(moveAblePosition).hasSize(18)
                .containsExactlyInAnyOrder(
                        PiecePosition.create(0, 0),
                        PiecePosition.create(1, 0),
                        PiecePosition.create(2, 0),
                        PiecePosition.create(4, 0),
                        PiecePosition.create(5, 0),
                        PiecePosition.create(6, 0),
                        PiecePosition.create(7, 0),
                        PiecePosition.create(8, 0),
                        PiecePosition.create(3, 1),
                        PiecePosition.create(3, 2),
                        PiecePosition.create(3, 3),
                        PiecePosition.create(3, 4),
                        PiecePosition.create(3, 5),
                        PiecePosition.create(3, 6),
                        PiecePosition.create(3, 7),
                        PiecePosition.create(3, 8),
                        PiecePosition.create(3, 9),
                        PiecePosition.create(4, 1)
                );
    }

}