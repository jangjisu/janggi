package com.game.janggi.domain.piece.move;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MovementTest {
    @Test
    @DisplayName("empty() 메소드를 통해 생성할 경우 빈 Directions를 반환한다.")
    void empty() {
        //when
        Movement emptyMovement = Movement.empty();

        //then
        assertThat(emptyMovement).isEqualTo(Movement.create());
    }

    @Test
    @DisplayName("Directions가 비어있지 않은지 확인한다")
    void exist() {
        //given
        Movement movement = Movement.create(Direction.UP);

        //when //then
        assertThat(movement.exists()).isTrue();
    }

    @DisplayName("제일 첫번째 Direction을 반환한다.")
    @Test
    void getFirst() {
        // given
        Movement movement = Movement.create(Direction.UP, Direction.DOWN, Direction.LEFT);

        // when
        Direction firstDirection = movement.getFirst();

        // then
        assertThat(firstDirection).isEqualTo(Direction.UP);
    }

    @DisplayName("빈 Movement에 대해 getFirst를 호출하면 예외가 발생한다.")
    @Test
    void getFirstOnEmpty() {
        // given
        Movement emptyMovement = Movement.empty();

        // when // then
        assertThatThrownBy(emptyMovement::getFirst)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("No directions available");
    }

    @DisplayName("사이즈를 구할 수 있다.")
    @Test
    void getSize() {
        // given
        Movement movement = Movement.create(Direction.UP, Direction.DOWN, Direction.LEFT);

        // when // then
        assertThat(movement.getSize()).isEqualTo(3);
    }

    @DisplayName("Movement 내부 Direction이 모두 동일할 경우 통일된 Movement로 판단한다.")
    @Test
    void isUnified() {
        // given
        Movement movement = Movement.create(Direction.UP, Direction.UP, Direction.UP);

        // when
        boolean isUnified = movement.isUnified();

        // then
        assertThat(isUnified).isTrue();
    }

    @DisplayName("Movement 내부 Direction이 하나라도다를경우 통일되지 않은 Movement로 판단한다.")
    @Test
    void isNonUnified() {
        // given
        Movement nonUnifiedMovement = Movement.create(Direction.UP, Direction.DOWN);

        // when
        boolean isNonUnified = nonUnifiedMovement.isUnified();

        // then
        assertThat(isNonUnified).isFalse();
    }

    @Test
    @DisplayName("이동할 Row의 총 합을 구한다")
    void getTotalRow() {
        //given
        Movement movement = Movement.create(Direction.LEFT, Direction.UP, Direction.LEFT);

        //when //then
        assertThat(movement.getTotalRow()).isEqualTo(-2);
    }

    @Test
    @DisplayName("이동할 Col의 총 합을 구한다")
    void getTotalCol() {
        //given
        Movement movement = Movement.create(Direction.UP, Direction.UP, Direction.RIGHT);

        //when //then
        assertThat(movement.getTotalCol()).isEqualTo(2);
    }

    @Test
    @DisplayName("Directions에 파라미터로 받는 Direction 을 뒤에 추가한다")
    void append() {
        //given
        Movement movement = Movement.create(Direction.UP);
        Direction newDirection = Direction.DOWN;

        //when //then
        assertThat(movement.append(newDirection)).isEqualTo(
                Movement.create(Direction.UP, Direction.DOWN)
        );
    }

    @Test
    @DisplayName("제공받은 두개의 Directions를 합쳐서 새로운 Directions를 반환한다.")
    void concat() {
        //given
        Movement upMovement = Movement.create(Direction.UP, Direction.UP);
        Movement downMovement = Movement.create(Direction.DOWN, Direction.DOWN);

        //when
        Movement concatMovement = Movement.concat(upMovement, downMovement);

        //then
        assertThat(concatMovement).isEqualTo(
                Movement.create(Direction.UP, Direction.UP, Direction.DOWN, Direction.DOWN));
    }

    @Test
    @DisplayName("1개 Direction 을 가진 Movement에 파편 움직임들을 구하면 빈값이다.")
    void getPartialMovementsOnOne() {
        //given
        Movement movement = Movement.create(
                Direction.UP
        );

        //when
        List<Movement> middlePositions = movement.getPartialMovements();

        //then
        assertThat(middlePositions).isEmpty();
    }

    @Test
    @DisplayName("3개 Direction 을 가진 Movement에 대해 중간 포지션을 구하면 2개이다")
    void getPartialMovements() {
        //given
        Movement movement = Movement.create(
                Direction.UP, Direction.UP_RIGHT, Direction.UP_RIGHT
        );

        //when
        List<Movement> middlePositions = movement.getPartialMovements();

        //then
        assertThat(middlePositions).hasSize(2)
                .containsExactly(
                        Movement.create(Direction.UP),
                        Movement.create(Direction.UP, Direction.UP_RIGHT)
                );
    }
}