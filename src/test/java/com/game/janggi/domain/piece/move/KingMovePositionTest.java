package com.game.janggi.domain.piece.move;

import com.game.janggi.domain.piece.Bung;
import com.game.janggi.domain.piece.Jol;
import com.game.janggi.domain.piece.King;
import com.game.janggi.domain.piece.Piece;
import com.game.janggi.domain.piece.position.PiecePosition;
import com.game.janggi.domain.team.TeamType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class KingMovePositionTest {
    Map<PiecePosition, Piece> pieces = new HashMap<>();

    MovePosition movePosition = MovePosition.createKingMove();

    @AfterEach
    void tearDown() {
        pieces.clear();
    }

    @Test
    @DisplayName("왕은 궁성내부 상하좌우로 이동할 수 있다.")
    void move() {
        //given
        PiecePosition position = PiecePosition.create(4, 0);
        pieces.put(position, King.create(TeamType.HAN));

        //when
        List<PiecePosition> moveAblePosition = movePosition.getMoveablePosition(pieces, position);

        //then
        assertThat(moveAblePosition).hasSize(3)
                .containsExactlyInAnyOrder(
                        PiecePosition.create(5, 0),
                        PiecePosition.create(3, 0),
                        PiecePosition.create(4, 1)
                );
    }

    @Test
    @DisplayName("왕이 궁성 중앙에 있다면 대각선으로도 이동할 수 있다.")
    void moveDigonal() {
        //given
        PiecePosition position = PiecePosition.create(4, 1);
        pieces.put(position, King.create(TeamType.HAN));

        //when
        List<PiecePosition> moveAblePosition = movePosition.getMoveablePosition(pieces, position);

        //then
        assertThat(moveAblePosition).hasSize(8)
                .containsExactlyInAnyOrder(
                        PiecePosition.create(4, 0),
                        PiecePosition.create(4, 2),
                        PiecePosition.create(3, 1),
                        PiecePosition.create(5, 1),
                        PiecePosition.create(3, 0),
                        PiecePosition.create(3, 2),
                        PiecePosition.create(5, 0),
                        PiecePosition.create(5, 2)
                );

    }

    @Test
    @DisplayName("왕이 움직일 위치에 상대 기물이 있더라도 이동 가능하다.")
    void moveWhenEnemy() {
        //given
        PiecePosition position = PiecePosition.create(4, 1);
        pieces.put(position, King.create(TeamType.HAN));
        pieces.put(PiecePosition.create(4, 0), Jol.create(TeamType.CHO));
        pieces.put(PiecePosition.create(4, 2), Jol.create(TeamType.CHO));
        pieces.put(PiecePosition.create(3, 1), Jol.create(TeamType.CHO));
        pieces.put(PiecePosition.create(5, 1), Jol.create(TeamType.CHO));
        pieces.put(PiecePosition.create(3, 0), Jol.create(TeamType.CHO));
        pieces.put(PiecePosition.create(3, 2), Jol.create(TeamType.CHO));
        pieces.put(PiecePosition.create(5, 0), Jol.create(TeamType.CHO));
        pieces.put(PiecePosition.create(5, 2), Jol.create(TeamType.CHO));

        //when
        List<PiecePosition> moveAblePosition = movePosition.getMoveablePosition(pieces, position);

        //then
        assertThat(moveAblePosition).hasSize(8)
                .containsExactlyInAnyOrder(
                        PiecePosition.create(4, 0),
                        PiecePosition.create(4, 2),
                        PiecePosition.create(3, 1),
                        PiecePosition.create(5, 1),
                        PiecePosition.create(3, 0),
                        PiecePosition.create(3, 2),
                        PiecePosition.create(5, 0),
                        PiecePosition.create(5, 2)
                );

    }

    @Test
    @DisplayName("왕이 움직일 위치에 같은팀 기물이 있을경우 이동 할 수 없다.")
    void moveWhenTeam() {
        //given
        PiecePosition position = PiecePosition.create(4, 1);
        pieces.put(position, King.create(TeamType.HAN));
        pieces.put(PiecePosition.create(4, 0), Bung.create(TeamType.HAN));
        pieces.put(PiecePosition.create(4, 2), Bung.create(TeamType.HAN));
        pieces.put(PiecePosition.create(3, 1), Bung.create(TeamType.HAN));
        pieces.put(PiecePosition.create(5, 1), Bung.create(TeamType.HAN));
        pieces.put(PiecePosition.create(3, 0), Bung.create(TeamType.HAN));
        pieces.put(PiecePosition.create(3, 2), Bung.create(TeamType.HAN));
        pieces.put(PiecePosition.create(5, 0), Bung.create(TeamType.HAN));
        pieces.put(PiecePosition.create(5, 2), Bung.create(TeamType.HAN));

        //when
        List<PiecePosition> moveAblePosition = movePosition.getMoveablePosition(pieces, position);

        //then
        assertThat(moveAblePosition).isEmpty();
    }

    @Test
    @DisplayName("왕이 궁 외부에 있을경우 이동 할 수 없다.")
    void moveWhenNotInGong() {
        //given
        PiecePosition position = PiecePosition.create(7, 7);
        pieces.put(position, King.create(TeamType.HAN));

        //when
        List<PiecePosition> moveAblePosition = movePosition.getMoveablePosition(pieces, position);

        //then
        assertThat(moveAblePosition).isEmpty();

    }
}