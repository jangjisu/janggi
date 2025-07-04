package com.game.janggi.domain.piece.move;

import com.game.janggi.domain.piece.Bung;
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

class JolMovePositionTest {
    Map<PiecePosition, Piece> pieces = new HashMap<>();

    MovePosition movePosition = MovePosition.createJolMove();

    @AfterEach
    void tearDown() {
        pieces.clear();
    }


    @Test
    @DisplayName("졸은 아래, 왼쪽, 오른쪽으로 이동할 수 있다.")
    void move() {
        //given
        PiecePosition position = PiecePosition.create(1, 3);
        pieces.put(position, Jol.create(CHO));

        //when
        List<PiecePosition> canMovePositions = movePosition.getMoveablePosition(pieces, position);

        //then
        assertThat(canMovePositions).hasSize(3)
                .containsExactlyInAnyOrder(
                        PiecePosition.create(1, 2),
                        PiecePosition.create(2, 3),
                        PiecePosition.create(0, 3)
                );
    }

    @Test
    @DisplayName("졸이 오른쪽에 붙어 있다면 아래와 왼쪽으로만 이동할 수 있다.")
    void moveWhenRight() {
        //given
        PiecePosition position = PiecePosition.create(8, 3);
        pieces.put(position, Jol.create(CHO));

        //when
        List<PiecePosition> canMovePositions = movePosition.getMoveablePosition(pieces, position);

        //then
        assertThat(canMovePositions).hasSize(2)
                .containsExactlyInAnyOrder(
                        PiecePosition.create(8, 2),
                        PiecePosition.create(7, 3)
                );
    }

    @Test
    @DisplayName("졸이 왼쪽에 붙어 있다면 아래와 왼쪽으로만 이동할 수 있다.")
    void moveWhenLeft() {
        //given
        PiecePosition position = PiecePosition.create(0, 1);
        pieces.put(position, Jol.create(CHO));

        //when
        List<PiecePosition> moveAblePosition = movePosition.getMoveablePosition(pieces, position);
        //then
        assertThat(moveAblePosition).hasSize(2)
                .containsExactlyInAnyOrder(
                        PiecePosition.create(0, 0),
                        PiecePosition.create(1, 1)
                );
    }

    @Test
    @DisplayName("졸이 아래쪽에 붙어 있다면 오른쪽과 왼쪽으로만 이동할 수 있다.")
    void moveWhenBottom() {
        //given
        PiecePosition position = PiecePosition.create(2, 0);
        pieces.put(position, Jol.create(CHO));

        //when
        List<PiecePosition> moveAblePosition = movePosition.getMoveablePosition(pieces, position);

        //then
        assertThat(moveAblePosition).hasSize(2)
                .containsExactlyInAnyOrder(
                        PiecePosition.create(1, 0),
                        PiecePosition.create(3, 0)
                );
    }

    @Test
    @DisplayName("졸은 궁안에 있을 경우 대각선으로 움직일 수 있다.")
    void moveDigonal() {
        //given
        PiecePosition position = PiecePosition.create(4, 1);
        pieces.put(position, Jol.create(CHO));

        //when
        List<PiecePosition> canMovePositions = movePosition.getMoveablePosition(pieces, position);

        //then
        assertThat(canMovePositions).hasSize(5)
                .containsExactlyInAnyOrder(
                        PiecePosition.create(4, 0),
                        PiecePosition.create(3, 1),
                        PiecePosition.create(5, 1),
                        PiecePosition.create(5, 0),
                        PiecePosition.create(3, 0)
                );

    }

    @Test
    @DisplayName("졸이 움직일 수 있는 위치에 상대 기물이 있더라도 모두 이동 가능하다.")
    void moveWhenEnemy() {
        //given
        PiecePosition position = PiecePosition.create(2, 5);

        pieces.put(position, Jol.create(CHO));
        pieces.put(PiecePosition.create(2, 4), Bung.create(HAN));
        pieces.put(PiecePosition.create(1, 5), Bung.create(HAN));
        pieces.put(PiecePosition.create(3, 5), Bung.create(HAN));

        //when
        List<PiecePosition> moveAblePosition = movePosition.getMoveablePosition(pieces, position);

        //then
        assertThat(moveAblePosition).hasSize(3)
                .containsExactlyInAnyOrder(
                        PiecePosition.create(1, 5),
                        PiecePosition.create(3, 5),
                        PiecePosition.create(2, 4)
                );
    }

    @Test
    @DisplayName("졸이 움직일 수 있는 위치에 같은팀 기물이 있을경우 이동 할 수 없다.")
    void moveWhenTeam() {
        //given
        PiecePosition position = PiecePosition.create(5, 5);

        pieces.put(position, Jol.create(CHO));
        pieces.put(PiecePosition.create(5, 4), Jol.create(CHO));

        //when
        List<PiecePosition> moveAblePosition = movePosition.getMoveablePosition(pieces, position);

        //then
        assertThat(moveAblePosition).hasSize(2)
                .containsExactlyInAnyOrder(
                        PiecePosition.create(4, 5),
                        PiecePosition.create(6, 5)
                );
    }

    @Test
    @DisplayName("졸이 움직일 수 있는 위치에 같은팀 기물이 모두 있을경우 움직일 수 없다.")
    void moveWhenTeamEmpty() {
        //given
        PiecePosition position = PiecePosition.create(7, 7);

        pieces.put(position, Jol.create(CHO));
        pieces.put(PiecePosition.create(7, 6), Jol.create(CHO));
        pieces.put(PiecePosition.create(6, 7), Jol.create(CHO));
        pieces.put(PiecePosition.create(8, 7), Jol.create(CHO));

        //when
        List<PiecePosition> moveAblePosition = movePosition.getMoveablePosition(pieces, position);

        //then
        assertThat(moveAblePosition).isEmpty();
    }
}