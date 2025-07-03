package com.game.janggi.domain.piece;

import com.game.janggi.domain.team.TeamType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

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
}