package com.game.janggi.domain.piece;

import com.game.janggi.domain.team.TeamType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SangTest {
    @DisplayName("상을 생성할 수 있다")
    @Test
    void createSang() {
        // when
        Sang sang = Sang.create(TeamType.HAN);

        // then
        assertThat(sang).isNotNull();
        assertThat(sang.printPieceName()).isEqualTo("상");
    }
}