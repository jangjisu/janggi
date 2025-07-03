package com.game.janggi.domain.piece;

import com.game.janggi.domain.team.TeamType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

    @DisplayName("차는 팀 없이 생성될 수 없다.")
    @Test
    void createChaNoTeamFail() {
        // when // then
        assertThatThrownBy(() -> Cha.create(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("TeamType must not be null");
    }
}