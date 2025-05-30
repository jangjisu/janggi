package com.game.janggi.domain.piece.move;

import com.game.janggi.domain.piece.position.PiecePosition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SangMovePositionTest {
    @Test
    @DisplayName("상은 상,하,좌,우로 한칸 대각선으로 두칸 씩 이동할 수 있다.")
    void move () {
        //given
        PiecePosition piecePosition = new PiecePosition(4, 3);

        //when
        SangMovePosition sangMovePosition = new SangMovePosition();

        List<Directions> moveAblePosition = sangMovePosition.calculateBasicMoveAbleDirections(piecePosition);

        //then
        assertThat(moveAblePosition).hasSize(8)
                .contains(
                        Directions.create(Direction.UP, Direction.UP_LEFT, Direction.UP_LEFT),
                        Directions.create(Direction.UP, Direction.UP_RIGHT, Direction.UP_RIGHT),
                        Directions.create(Direction.DOWN, Direction.DOWN_LEFT, Direction.DOWN_LEFT),
                        Directions.create(Direction.DOWN, Direction.DOWN_RIGHT, Direction.DOWN_RIGHT),
                        Directions.create(Direction.LEFT, Direction.UP_LEFT, Direction.UP_LEFT),
                        Directions.create(Direction.LEFT, Direction.DOWN_LEFT, Direction.DOWN_LEFT),
                        Directions.create(Direction.RIGHT, Direction.UP_RIGHT, Direction.UP_RIGHT),
                        Directions.create(Direction.RIGHT, Direction.DOWN_RIGHT, Direction.DOWN_RIGHT)
                );
    }
}