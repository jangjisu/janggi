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

        List<Directions> moveAblePosition = jolMovePosition.calculateBasicMoveAbleDirections(piecePosition);

        //then
        assertThat(moveAblePosition).hasSize(3)
                .contains(Directions.create(Direction.DOWN),
                        Directions.create(Direction.LEFT),
                        Directions.create(Direction.RIGHT));
    }

    @Test
    @DisplayName("졸이 오른쪽에 붙어 있다면 뒤와 왼쪽으로만 이동할 수 있다.")
    void moveWhenRight() {
        //given
        PiecePosition piecePosition = new PiecePosition(9, 3);

        //when
        JolMovePosition jolMovePosition = new JolMovePosition();

        List<Directions> moveAblePosition = jolMovePosition.calculateBasicMoveAbleDirections(piecePosition);

        //then
        assertThat(moveAblePosition).hasSize(2)
                .contains(Directions.create(Direction.DOWN),
                        Directions.create(Direction.LEFT));
    }

    @Test
    @DisplayName("졸이 왼쪽에 붙어 있다면 뒤와 오른쪽으로만 이동할 수 있다.")
    void moveWhenLeft() {
        //given
        PiecePosition piecePosition = new PiecePosition(0, 3);

        //when
        JolMovePosition jolMovePosition = new JolMovePosition();

        List<Directions> moveAblePosition = jolMovePosition.calculateBasicMoveAbleDirections(piecePosition);
        //then
        assertThat(moveAblePosition).hasSize(2)
                .contains(Directions.create(Direction.DOWN),
                        Directions.create(Direction.RIGHT));


    }

    @Test
    @DisplayName("졸이 위쪽에 붙어 있다면 오른쪽과 왼쪽으로만 이동할 수 있다.")
    void moveWhenTop() {
        //given
        PiecePosition piecePosition = new PiecePosition(1, 0);

        //when
        JolMovePosition jolMovePosition = new JolMovePosition();


        List<Directions> moveAblePosition = jolMovePosition.calculateBasicMoveAbleDirections(piecePosition);

        //then
        assertThat(moveAblePosition).hasSize(2)
                .contains(Directions.create(Direction.RIGHT),
                        Directions.create(Direction.LEFT));

    }
}