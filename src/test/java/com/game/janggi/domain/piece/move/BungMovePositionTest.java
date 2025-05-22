package com.game.janggi.domain.piece.move;

import com.game.janggi.domain.piece.position.PiecePosition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class BungMovePositionTest {

    @Test
    @DisplayName("병이 오른쪽에 붙어 있다면 앞과 왼쪽으로만 이동할 수 있다.")
    void test () {
        //given
        PiecePosition piecePosition = new PiecePosition(9, 3);

        //when
        BungMovePosition bungMovePosition = new BungMovePosition();

        List<PiecePosition> movablePosition = bungMovePosition.getMovablePosition(piecePosition);

        //then
        assertThat(movablePosition).hasSize(2)
                .contains(new PiecePosition(9, 4), new PiecePosition(8, 3));

    }
}