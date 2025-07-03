package com.game.janggi.domain.piece;

import com.game.janggi.domain.team.TeamType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SaTest {
    @DisplayName("사를 생성할 수 있다")
    @Test
    void createSa() {
        // when
        Sa sa = Sa.create(TeamType.HAN);

        // then
        assertThat(sa).isNotNull();
        assertThat(sa.printPieceName()).isEqualTo("사");
    }

    @DisplayName("사는 팀 없이 생성될 수 없다.")
    @Test
    void createSaNoTeamFail() {
        // when // then
        assertThatThrownBy(() -> Sa.create(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("TeamType must not be null");
    }
}