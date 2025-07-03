package com.game.janggi.domain.piece;

import com.game.janggi.domain.team.TeamType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

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
}