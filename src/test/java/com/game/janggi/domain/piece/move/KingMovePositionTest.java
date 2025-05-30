package com.game.janggi.domain.piece.move;

import com.game.janggi.domain.piece.position.PiecePosition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class KingMovePositionTest {
    @Test
    @DisplayName("왕이 궁성 중앙에 있다면 궁성 어디로든 이동할 수 있다.")
    void moveWhenMiddle() {
        //given
        PiecePosition piecePosition = new PiecePosition(4, 1);

        //when
        KingMovePosition kingMovePosition = new KingMovePosition();

        List<Directions> moveAblePosition = kingMovePosition.calculateBasicMoveAbleDirections(piecePosition);

        //then
        assertThat(moveAblePosition).hasSize(8)
                .contains(
                        Directions.create(Direction.UP),
                        Directions.create(Direction.DOWN),
                        Directions.create(Direction.LEFT),
                        Directions.create(Direction.RIGHT),
                        Directions.create(Direction.UP_LEFT),
                        Directions.create(Direction.UP_RIGHT),
                        Directions.create(Direction.DOWN_LEFT),
                        Directions.create(Direction.DOWN_RIGHT)
                );

    }
}