package com.game.janggi.domain.piece;

import com.game.janggi.domain.team.TeamType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class KingTest {
    @DisplayName("왕을 생성할 수 있다")
    @Test
    void createKing() {
        // when
        King king = King.create(TeamType.HAN);

        // then
        assertThat(king).isNotNull();
        assertThat(king.printPieceName()).isEqualTo("왕");
    }

    @DisplayName("왕은 팀 없이 생성될 수 없다.")
    @Test
    void createKingNoTeamFail() {
        // when // then
        assertThatThrownBy(() -> King.create(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("TeamType must not be null");
    }
}