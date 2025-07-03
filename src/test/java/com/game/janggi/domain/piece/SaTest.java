package com.game.janggi.domain.piece;

import com.game.janggi.domain.team.TeamType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

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
}