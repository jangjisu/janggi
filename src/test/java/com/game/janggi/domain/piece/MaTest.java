package com.game.janggi.domain.piece;

import com.game.janggi.domain.team.TeamType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MaTest {
    @DisplayName("마를 생성할 수 있다")
    @Test
    void createMa() {
        // when
        Ma ma = Ma.create(TeamType.HAN);

        // then
        assertThat(ma).isNotNull();
        assertThat(ma.printPieceName()).isEqualTo("마");
    }

    @DisplayName("마는 팀 없이 생성될 수 없다.")
    @Test
    void createMaNoTeamFail() {
        // when // then
        assertThatThrownBy(() -> Ma.create(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("TeamType must not be null");
    }
}