package com.game.janggi.domain.piece.move;

import com.game.janggi.domain.piece.Jol;
import com.game.janggi.domain.piece.Piece;
import com.game.janggi.domain.piece.position.PiecePosition;
import com.game.janggi.domain.team.TeamType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class MovementsTest {
    @Test
    @DisplayName("빈 Movements 생성할 수 있다")
    void createEmptyMovements() {
        // given // when
        Movements empty = Movements.empty();
        // then
        assertThat(empty.getValues()).isEmpty();
    }

    @Test
    @DisplayName("속한 모든 Movement 내부 Direction 방향까지 모두 동일해야 한다.")
    void unifiedMovements() {
        // given
        Movement unifiedMovement1 = Movement.create(Direction.UP, Direction.UP);
        Movement unifiedMovement2 = Movement.create(Direction.UP, Direction.UP, Direction.UP);
        Movement unifiedMovement3 = Movement.create(Direction.UP, Direction.UP, Direction.UP, Direction.UP);
        Movement unifiedMovement4 = Movement.create(Direction.UP);

        Movements movements = Movements.create(List.of(unifiedMovement1, unifiedMovement2, unifiedMovement3, unifiedMovement4));

        // when
        boolean isUnified = movements.isUnified();

        // then
        assertThat(isUnified).isTrue();
    }

    @Test
    @DisplayName("속한 모든 Movement의 방향이 하나라도 다를경우 통일이 아니다.")
    void nonInternalUnifiedMovements() {
        // given
        Movement unifiedMovement1 = Movement.create(Direction.UP, Direction.UP);
        Movement unifiedMovement2 = Movement.create(Direction.UP, Direction.DOWN);
        Movement unifiedMovement3 = Movement.create(Direction.UP, Direction.UP);
        Movement unifiedMovement4 = Movement.create(Direction.UP, Direction.UP);

        Movements movements = Movements.create(List.of(unifiedMovement1, unifiedMovement2, unifiedMovement3, unifiedMovement4));

        // when
        boolean isUnified = movements.isUnified();

        // then
        assertThat(isUnified).isFalse();
    }

    @Test
    @DisplayName("속한 모든 Movement의 방향이 통일 되 있어도 다를경우 통일이 아니다.")
    void nonSameUnifiedMovements() {
        // given
        Movement unifiedMovement1 = Movement.create(Direction.UP, Direction.UP);
        Movement unifiedMovement2 = Movement.create(Direction.DOWN, Direction.DOWN);
        Movement unifiedMovement3 = Movement.create(Direction.UP, Direction.UP);
        Movement unifiedMovement4 = Movement.create(Direction.UP, Direction.UP);

        Movements movements = Movements.create(List.of(unifiedMovement1, unifiedMovement2, unifiedMovement3, unifiedMovement4));

        // when
        boolean isUnified = movements.isUnified();

        // then
        assertThat(isUnified).isFalse();
    }

    @DisplayName("다음 기물이 있기 전 까지의 움직임들만 필터링한다.")
    @Test
    void filterUntilBlockedByPiece() {
        // given
        Movement unifiedMovement1 = Movement.create(Direction.UP);
        Movement unifiedMovement2 = Movement.create(Direction.UP, Direction.UP);
        Movement unifiedMovement3 = Movement.create(Direction.UP, Direction.UP, Direction.UP);
        Movement unifiedMovement4 = Movement.create(Direction.UP, Direction.UP, Direction.UP, Direction.UP);

        Movements movements = Movements.create(List.of(unifiedMovement1, unifiedMovement2, unifiedMovement3, unifiedMovement4));

        PiecePosition currentPosition = PiecePosition.create(0, 0);

        Map<PiecePosition, Piece> pieces = Map.of(
                PiecePosition.create(0, 0), Jol.create(TeamType.CHO),
                PiecePosition.create(0, 3), Jol.create(TeamType.CHO)
        );

        // when
        Movements filteredMovements = movements.filterUntilBlockedByPiece(pieces, currentPosition);

        // then
        assertThat(filteredMovements.getValues()).hasSize(2)
                .containsExactly(
                        Movement.create(Direction.UP),
                        Movement.create(Direction.UP, Direction.UP)
                );
    }

    @Test
    @DisplayName("새로운 Movement를 추가한다.")
    void append() {
        //given
        Movement unifiedMovement1 = Movement.create(Direction.UP);
        Movement unifiedMovement2 = Movement.create(Direction.UP, Direction.UP);
        Movement unifiedMovement3 = Movement.create(Direction.UP, Direction.UP, Direction.UP);
        Movement unifiedMovement4 = Movement.create(Direction.UP, Direction.UP, Direction.UP, Direction.UP);

        Movements movements = Movements.create(List.of(unifiedMovement1, unifiedMovement2, unifiedMovement3, unifiedMovement4));

        Movement addMovement = Movement.create(Direction.UP, Direction.UP, Direction.UP, Direction.UP, Direction.UP);
        //when
        Movements appended = movements.append(addMovement);

        //then
        assertThat(appended.getValues()).hasSize(5)
                .containsExactly(
                        Movement.create(Direction.UP),
                        Movement.create(Direction.UP, Direction.UP),
                        Movement.create(Direction.UP, Direction.UP, Direction.UP),
                        Movement.create(Direction.UP, Direction.UP, Direction.UP, Direction.UP),
                        Movement.create(Direction.UP, Direction.UP, Direction.UP, Direction.UP, Direction.UP)
                );

    }

    @Test
    @DisplayName("빈 Movement는 추가되지 않는다.")
    void appendEmpty() {
        //given
        Movement unifiedMovement1 = Movement.create(Direction.UP);
        Movement unifiedMovement2 = Movement.create(Direction.UP, Direction.UP);
        Movement unifiedMovement3 = Movement.create(Direction.UP, Direction.UP, Direction.UP);
        Movement unifiedMovement4 = Movement.create(Direction.UP, Direction.UP, Direction.UP, Direction.UP);

        Movements movements = Movements.create(List.of(unifiedMovement1, unifiedMovement2, unifiedMovement3, unifiedMovement4));

        Movement addMovement = Movement.empty();
        //when
        Movements appended = movements.append(addMovement);

        //then
        assertThat(appended.getValues()).hasSize(4)
                .containsExactly(
                        Movement.create(Direction.UP),
                        Movement.create(Direction.UP, Direction.UP),
                        Movement.create(Direction.UP, Direction.UP, Direction.UP),
                        Movement.create(Direction.UP, Direction.UP, Direction.UP, Direction.UP)
                );

    }

    @Test
    @DisplayName("같은 Movement는 추가되지 않는다.")
    void appendEqual() {
        //given
        Movement unifiedMovement1 = Movement.create(Direction.UP);
        Movement unifiedMovement2 = Movement.create(Direction.UP, Direction.UP);
        Movement unifiedMovement3 = Movement.create(Direction.UP, Direction.UP, Direction.UP);
        Movement unifiedMovement4 = Movement.create(Direction.UP, Direction.UP, Direction.UP, Direction.UP);

        Movements movements = Movements.create(List.of(unifiedMovement1, unifiedMovement2, unifiedMovement3, unifiedMovement4));

        Movement addMovement = Movement.create(Direction.UP, Direction.UP, Direction.UP, Direction.UP);
        //when
        movements.append(addMovement);

        //then
        assertThat(movements.getValues()).hasSize(4)
                .containsExactly(
                        Movement.create(Direction.UP),
                        Movement.create(Direction.UP, Direction.UP),
                        Movement.create(Direction.UP, Direction.UP, Direction.UP),
                        Movement.create(Direction.UP, Direction.UP, Direction.UP, Direction.UP)
                );

    }

    @Test
    @DisplayName("전체 Movement에 parameter 로 받는 movement를 concat 한다.")
    void concat() {
        //given
        Movement unifiedMovement1 = Movement.create(Direction.UP);
        Movement unifiedMovement2 = Movement.create(Direction.UP, Direction.UP);
        Movement unifiedMovement3 = Movement.create(Direction.UP, Direction.UP, Direction.UP);

        Movements movements = Movements.create(List.of(unifiedMovement1, unifiedMovement2, unifiedMovement3));

        //when
        Movement movement = Movement.create(Direction.DOWN_RIGHT);
        Movements concatMovement = movements.concatAll(movement);

        //then
        assertThat(concatMovement.getValues()).hasSize(3)
                .containsExactly(
                        Movement.create(Direction.UP, Direction.DOWN_RIGHT),
                        Movement.create(Direction.UP, Direction.UP, Direction.DOWN_RIGHT),
                        Movement.create(Direction.UP, Direction.UP, Direction.UP, Direction.DOWN_RIGHT)
                );

    }

    @Test
    @DisplayName("전체 Movement에 parameter 로 받는 movement가 빈값일 경우 concat 한 값은 동일하다.")
    void concatEmpty() {
        //given
        Movement unifiedMovement1 = Movement.create(Direction.UP);
        Movement unifiedMovement2 = Movement.create(Direction.UP, Direction.UP);
        Movement unifiedMovement3 = Movement.create(Direction.UP, Direction.UP, Direction.UP);

        Movements movements = Movements.create(List.of(unifiedMovement1, unifiedMovement2, unifiedMovement3));

        //when
        Movement movement = Movement.empty();
        movements.concatAll(movement);

        //then
        assertThat(movements.getValues()).hasSize(3)
                .containsExactly(
                        Movement.create(Direction.UP),
                        Movement.create(Direction.UP, Direction.UP),
                        Movement.create(Direction.UP, Direction.UP, Direction.UP)
                );
    }

    @DisplayName("Movements에 속한 Movement 중 가장 길이가 긴 Movement를 반환한다.")
    @Test
    void getMax() {
        //given
        Movement unifiedMovement1 = Movement.create(Direction.UP);
        Movement unifiedMovement2 = Movement.create(Direction.UP, Direction.UP);
        Movement unifiedMovement3 = Movement.create(Direction.UP, Direction.UP, Direction.UP);

        Movements movements = Movements.create(List.of(unifiedMovement1, unifiedMovement2, unifiedMovement3));

        // when
        Movement maxMovement = movements.getMax();

        // then
        assertThat(maxMovement).isEqualTo(unifiedMovement3);
    }

    @DisplayName("Movements에 속한 Movement 중 가장 길이가 긴 Movement를 반환한다.")
    @Test
    void getMaxOnEmpty() {
        //given
        Movements movements = Movements.empty();

        // when
        Movement maxMovement = movements.getMax();

        // then
        assertThat(maxMovement).isEqualTo(Movement.empty());
    }

    @DisplayName("Movements가 비었는지 확인한다.")
    @Test
    void isEmpty() {
        // given
        Movements movements = Movements.empty();

        // when // then
        assertThat(movements.isEmpty()).isTrue();
    }

    @DisplayName("Movements가 비어있지 않은지 확인한다.")
    @Test
    void isNotEmpty() {
        // given
        Movements movements = Movements.create(List.of(Movement.create(Direction.UP)));

        // when // then
        assertThat(movements.isEmpty()).isFalse();
    }
}