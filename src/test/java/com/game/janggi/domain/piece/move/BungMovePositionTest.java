package com.game.janggi.domain.piece.move;

import com.game.janggi.domain.piece.position.PiecePosition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class BungMovePositionTest {

    @Test
    @DisplayName("병은 앞, 왼쪽, 오른쪽으로 이동할 수 있다.")
    void move() {
        //given
        PiecePosition piecePosition = new PiecePosition(1, 3);

        //when
        BungMovePosition bungMovePosition = new BungMovePosition();

        List<PiecePosition> movablePosition = bungMovePosition.calculateBasicMovablePositions(piecePosition);

        //then
        assertThat(movablePosition).hasSize(3)
                .contains(new PiecePosition(1, 4), new PiecePosition(0, 3), new PiecePosition(2, 3));

    }

    @Test
    @DisplayName("병이 오른쪽에 붙어 있다면 앞과 왼쪽으로만 이동할 수 있다.")
    void moveWhenRight() {
        //given
        PiecePosition piecePosition = new PiecePosition(9, 3);

        //when
        BungMovePosition bungMovePosition = new BungMovePosition();

        List<PiecePosition> movablePosition = bungMovePosition.calculateBasicMovablePositions(piecePosition);

        //then
        assertThat(movablePosition).hasSize(2)
                .contains(new PiecePosition(9, 4), new PiecePosition(8, 3));

    }

    @Test
    @DisplayName("병이 왼쪽에 붙어 있다면 앞과 왼쪽으로만 이동할 수 있다.")
    void moveWhenLeft() {
        //given
        PiecePosition piecePosition = new PiecePosition(0, 3);

        //when
        BungMovePosition bungMovePosition = new BungMovePosition();

        List<PiecePosition> movablePosition = bungMovePosition.calculateBasicMovablePositions(piecePosition);
        //then
        assertThat(movablePosition).hasSize(2)
                .contains(new PiecePosition(0, 4), new PiecePosition(1, 3));

    }

    @Test
    @DisplayName("병이 아래쪽에 붙어 있다면 오른쪽과 왼쪽으로만 이동할 수 있다.")
    void moveWhenTop() {
        //given
        PiecePosition piecePosition = new PiecePosition(1, 8);

        //when
        BungMovePosition bungMovePosition = new BungMovePosition();


        List<PiecePosition> movablePosition = bungMovePosition.calculateBasicMovablePositions(piecePosition);

        //then
        assertThat(movablePosition).hasSize(2)
                .contains(new PiecePosition(0, 8), new PiecePosition(2, 8));

    }
}