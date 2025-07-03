package com.game.janggi.domain.piece;

import com.game.janggi.domain.team.TeamType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ChaTest {
    @DisplayName("차를 생성할 수 있다")
    @Test
    void createCha() {
        // when
        Cha cha = Cha.create(TeamType.HAN);

        // then
        assertThat(cha).isNotNull();
        assertThat(cha.printPieceName()).isEqualTo("차");
    }
}