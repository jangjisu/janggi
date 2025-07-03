package com.game.janggi.domain.piece.move;

import com.game.janggi.domain.piece.Bung;
import com.game.janggi.domain.piece.Jol;
import com.game.janggi.domain.piece.Piece;
import com.game.janggi.domain.piece.position.PiecePosition;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.game.janggi.domain.team.TeamType.CHO;
import static com.game.janggi.domain.team.TeamType.HAN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@TestInstance(PER_CLASS)
class BungMovePositionTest {
    Map<PiecePosition, Piece> pieces = new HashMap<>();

    MovePosition movePosition;

    @BeforeAll
    void setUp() {
        movePosition = MovePosition.createBungMove();

        pieces.put(new PiecePosition(1, 3), Bung.create(HAN));

        pieces.put(new PiecePosition(8, 3), Bung.create(HAN));

        pieces.put(new PiecePosition(0, 0), Bung.create(HAN));

        pieces.put(new PiecePosition(1, 9), Bung.create(HAN));

        pieces.put(new PiecePosition(2, 5), Bung.create(HAN));
        pieces.put(new PiecePosition(2, 6), Jol.create(CHO));
        pieces.put(new PiecePosition(1, 5), Jol.create(CHO));
        pieces.put(new PiecePosition(3, 5), Jol.create(CHO));

        pieces.put(new PiecePosition(5, 5), Bung.create(HAN));
        pieces.put(new PiecePosition(5, 6), Bung.create(HAN));


        pieces.put(new PiecePosition(7, 7), Bung.create(HAN));
        pieces.put(new PiecePosition(7, 8), Bung.create(HAN));
        pieces.put(new PiecePosition(6, 7), Bung.create(HAN));
        pieces.put(new PiecePosition(8, 7), Bung.create(HAN));
    }


    @Test
    @DisplayName("병은 앞, 왼쪽, 오른쪽으로 이동할 수 있다.")
    void move() {
        //given
        PiecePosition piecePosition = new PiecePosition(1, 3);

        //when
        List<PiecePosition> canMovePositions = movePosition.getMoveablePosition(pieces, piecePosition);

        //then
        assertThat(canMovePositions).hasSize(3)
                .contains(
                    PiecePosition.create(1,4),
                    PiecePosition.create(2,3),
                    PiecePosition.create(0,3)
                );
    }

    @Test
    @DisplayName("병이 오른쪽에 붙어 있다면 앞과 왼쪽으로만 이동할 수 있다.")
    void moveWhenRight() {
        //given
        PiecePosition piecePosition = new PiecePosition(8, 3);

        //when
        List<PiecePosition> canMovePositions = movePosition.getMoveablePosition(pieces, piecePosition);

        //then
        assertThat(canMovePositions).hasSize(2)
                .contains(
                    PiecePosition.create(8,4),
                    PiecePosition.create(7,3)
                );
    }

    @Test
    @DisplayName("병이 왼쪽에 붙어 있다면 앞과 왼쪽으로만 이동할 수 있다.")
    void moveWhenLeft() {
        //given
        PiecePosition piecePosition = new PiecePosition(0, 0);

        //when
        List<PiecePosition> moveAblePosition = movePosition.getMoveablePosition(pieces, piecePosition);
        //then
        assertThat(moveAblePosition).hasSize(2)
                .contains(
                    PiecePosition.create(0,1),
                    PiecePosition.create(1,0)
                );
    }

    @Test
    @DisplayName("병이 아래쪽에 붙어 있다면 오른쪽과 왼쪽으로만 이동할 수 있다.")
    void moveWhenTop() {
        //given
        PiecePosition piecePosition = new PiecePosition(1, 9);

        //when
        List<PiecePosition> moveAblePosition = movePosition.getMoveablePosition(pieces, piecePosition);

        //then
        assertThat(moveAblePosition).hasSize(2)
                .contains(
                        PiecePosition.create(0,9),
                        PiecePosition.create(2,9)
                );
    }

    @Test
    @DisplayName("병이 움직일 수 있는 위치에 상대 기물이 있을경우 모두 이동 가능하다.")
    void moveWhenEnemy() {
        //given
        PiecePosition piecePosition = new PiecePosition(2, 5);

        //when
        List<PiecePosition> moveAblePosition = movePosition.getMoveablePosition(pieces, piecePosition);

        //then
        assertThat(moveAblePosition).hasSize(3)
                .contains(
                        PiecePosition.create(1,5),
                        PiecePosition.create(3,5),
                        PiecePosition.create(2,6)
                );
    }

    @Test
    @DisplayName("병이 움직일 수 있는 위치에 같은팀 기물이 있을경우 모두 이동 가능하다.")
    void moveWhenTeam() {
        //given
        PiecePosition piecePosition = new PiecePosition(5, 5);

        //when
        List<PiecePosition> moveAblePosition = movePosition.getMoveablePosition(pieces, piecePosition);

        //then
        assertThat(moveAblePosition).hasSize(2)
                .contains(
                        PiecePosition.create(4,5),
                        PiecePosition.create(6,5)
                );
    }

    @Test
    @DisplayName("병이 움직일 수 있는 위치에 같은팀 기물이 모두 있을경우 움직일 수 있는 위치가 없어진다.")
    void moveWhenTeamEmpty() {
        //given
        PiecePosition piecePosition = new PiecePosition(7,7);

        //when
        List<PiecePosition> moveAblePosition = movePosition.getMoveablePosition(pieces, piecePosition);

        //then
        assertThat(moveAblePosition).isEmpty();
    }
}