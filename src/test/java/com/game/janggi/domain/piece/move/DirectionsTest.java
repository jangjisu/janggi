package com.game.janggi.domain.piece.move;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DirectionsTest {
    @Test
    @DisplayName("1개 Direction 움직임에 대해 중간 포지션을 구하면 빈값이다.")
    void getMiddleDirectionsOnOne () {
        //given
        Directions directions = Directions.create(
                Direction.UP
        );

        //when
        List<Directions> middlePositions = directions.getMiddleDirections();

        //then
        assertThat(middlePositions).isEmpty();

    }

    @Test
    @DisplayName("3개 Direction 움직임에 대해 중간 포지션을 구하면 2개가 나와야 한다.")
    void getMiddleDirections () {
        //given
        Directions directions = Directions.create(
                Direction.UP, Direction.UP_RIGHT, Direction.UP_RIGHT
        );

        //when
        List<Directions> middlePositions = directions.getMiddleDirections();

        //then
        assertThat(middlePositions).hasSize(2)
                .containsExactly(
                        Directions.create(Direction.UP),
                        Directions.create(Direction.UP, Direction.UP_RIGHT)
                );

    }

}