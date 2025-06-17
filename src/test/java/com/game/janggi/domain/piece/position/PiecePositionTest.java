package com.game.janggi.domain.piece.position;

import com.game.janggi.exception.RecoverableException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PiecePositionTest {
    @Test
    @DisplayName("위치가 같다면 장기말 포지션은 동등하다.")
    void piecePositionRange() {
        //given
        PiecePosition piecePosition = PiecePosition.create(2, 3);

        //when //then
        assertThat(piecePosition).isEqualTo(new PiecePosition(2, 3));
    }

    @Test
    @DisplayName("장기말은 음수 포지션에는 존재할 수 없다")
    void positionCannotOnMinusIndex() {
        //when //then
        assertThatThrownBy(() -> PiecePosition.create(-2, 3))
                .isInstanceOf(RecoverableException.class)
                .hasMessageContaining("Invalid position");
    }

    @Test
    @DisplayName("장기말은 row 0~8, col 0~9 포지션 밖에는 존재할 수 없다")
    void positionCannotOnOutIndex() {
        //when //then
        assertThatThrownBy(() -> PiecePosition.create(11, 3))
                .isInstanceOf(RecoverableException.class)
                .hasMessageContaining("Invalid position");
    }

}