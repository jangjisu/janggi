package com.game.janggi.domain.piece.move;

import com.game.janggi.domain.piece.position.PiecePosition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class JolMovePositionTest {

    @Test
    @DisplayName("졸은 아래, 왼쪽, 오른쪽으로 이동할 수 있다.")
    void move() {
        //given
        PiecePosition piecePosition = new PiecePosition(1, 3);

        //when
        JolMovePosition jolMovePosition = new JolMovePosition();

        List<PiecePosition> movablePosition = jolMovePosition.calculateBasicMovablePositions(piecePosition);

        //then
        assertThat(movablePosition).hasSize(3)
                .contains(new PiecePosition(1, 2), new PiecePosition(0, 3), new PiecePosition(2, 3));

    }

    @Test
    @DisplayName("졸이 오른쪽에 붙어 있다면 뒤와 왼쪽으로만 이동할 수 있다.")
    void moveWhenRight() {
        //given
        PiecePosition piecePosition = new PiecePosition(9, 3);

        //when
        JolMovePosition jolMovePosition = new JolMovePosition();

        List<PiecePosition> movablePosition = jolMovePosition.calculateBasicMovablePositions(piecePosition);

        //then
        assertThat(movablePosition).hasSize(2)
                .contains(new PiecePosition(9, 2), new PiecePosition(8, 3));

    }

    @Test
    @DisplayName("졸이 왼쪽에 붙어 있다면 뒤와 왼쪽으로만 이동할 수 있다.")
    void moveWhenLeft() {
        //given
        PiecePosition piecePosition = new PiecePosition(0, 3);

        //when
        JolMovePosition jolMovePosition = new JolMovePosition();

        List<PiecePosition> movablePosition = jolMovePosition.calculateBasicMovablePositions(piecePosition);
        //then
        assertThat(movablePosition).hasSize(2)
                .contains(new PiecePosition(0, 2), new PiecePosition(1, 3));

    }

    @Test
    @DisplayName("졸이 위쪽에 붙어 있다면 오른쪽과 왼쪽으로만 이동할 수 있다.")
    void moveWhenTop() {
        //given
        PiecePosition piecePosition = new PiecePosition(1, 0);

        //when
        JolMovePosition jolMovePosition = new JolMovePosition();


        List<PiecePosition> movablePosition = jolMovePosition.calculateBasicMovablePositions(piecePosition);

        //then
        assertThat(movablePosition).hasSize(2)
                .contains(new PiecePosition(0, 0), new PiecePosition(2, 0));

    }
}