package com.game.janggi.domain.piece.move;

import com.game.janggi.domain.piece.position.PiecePosition;
import com.game.janggi.domain.team.TeamType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class KingMovePositionTest {
    @Test
    @DisplayName("왕이 궁성 중앙에 있다면 궁성 어디로든 이동할 수 있다.")
    void moveWhenMiddle () {
        //given
        PiecePosition piecePosition = new PiecePosition(4, 1);

        //when
        KingMovePosition kingMovePosition = new KingMovePosition();

        List<PiecePosition> movablePosition = kingMovePosition.getMovablePosition(piecePosition, TeamType.HAN);

        //then
        assertThat(movablePosition).hasSize(8)
                .contains(
                        new PiecePosition(3, 0),
                        new PiecePosition(4, 0),
                        new PiecePosition(5, 0),
                        new PiecePosition(3, 1),
                        new PiecePosition(5, 1),
                        new PiecePosition(3, 2),
                        new PiecePosition(4, 2),
                        new PiecePosition(5, 2)
                );

    }
}