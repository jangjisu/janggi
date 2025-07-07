package com.game.janggi.domain.piece.move;

import com.game.janggi.domain.piece.position.PiecePosition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SaMovePositionTest {
    @Test
    @DisplayName("사가 궁성 중앙에 있다면 궁성 어디로든 이동할 수 있다.")
    void moveWhenMiddle() {
        //given
        PiecePosition piecePosition = new PiecePosition(4, 1);

        //when
        SaMovePosition saMovePositionTest = new SaMovePosition();

        Movements moveAblePosition = saMovePositionTest.calculateBasicMoveAbleDirections(piecePosition);

        //then
        assertThat(moveAblePosition.getValues()).hasSize(8)
                .contains(
                        Movement.create(Direction.UP),
                        Movement.create(Direction.DOWN),
                        Movement.create(Direction.LEFT),
                        Movement.create(Direction.RIGHT),
                        Movement.create(Direction.UP_LEFT),
                        Movement.create(Direction.UP_RIGHT),
                        Movement.create(Direction.DOWN_LEFT),
                        Movement.create(Direction.DOWN_RIGHT)
                );

    }

    @Test
    @DisplayName("사가 궁성 우측에 있다면 위, 아래, 왼쪽을 움직일 수 있다.")
    void moveWhenRightMiddle() {
        //given
        PiecePosition piecePosition = new PiecePosition(5, 1);

        //when
        SaMovePosition saMovePositionTest = new SaMovePosition();

        Movements moveAblePosition = saMovePositionTest.calculateBasicMoveAbleDirections(piecePosition);

        //then
        assertThat(moveAblePosition.getValues()).hasSize(3)
                .contains(
                        Movement.create(Direction.UP),
                        Movement.create(Direction.DOWN),
                        Movement.create(Direction.LEFT)
                );

    }
}