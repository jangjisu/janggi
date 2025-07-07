package com.game.janggi.domain.piece;

import com.game.janggi.domain.team.TeamType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PoTest {
    @DisplayName("포를 생성할 수 있다")
    @Test
    void createPo() {
        // when
        Po po = Po.create(TeamType.HAN);

        // then
        assertThat(po).isNotNull();
        assertThat(po.printPieceName()).isEqualTo("포");
    }

    @DisplayName("포는 팀 없이 생성될 수 없다.")
    @Test
    void createPoNoTeamFail() {
        // when // then
        assertThatThrownBy(() -> Po.create(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("TeamType must not be null");
    }
}