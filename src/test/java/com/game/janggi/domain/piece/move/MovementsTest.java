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
    void nonUnifiedMovements() {
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
}