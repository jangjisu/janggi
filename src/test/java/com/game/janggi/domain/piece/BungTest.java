package com.game.janggi.domain.piece;

import com.game.janggi.domain.team.TeamType;
import com.game.janggi.exception.NeedStopException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BungTest {
    @DisplayName("병은 한나라 타입으로 생성할 수 있다")
    @Test
    void createBung() {
        // when
        Bung bung = Bung.create(TeamType.HAN);

        // then
        assertThat(bung).isNotNull();
        assertThat(bung.printPieceName()).isEqualTo("병");
    }

    @DisplayName("병은 초나라 타입으로 생성될 수 없다.")
    @Test
    void createBungFail() {
        // when // then
        assertThatThrownBy(() -> Bung.create(TeamType.CHO))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Bung is Han Piece");
    }

    @DisplayName("병은 팀 없이 생성될 수 없다.")
    @Test
    void createBungNoTeamFail() {
        // when // then
        assertThatThrownBy(() -> Bung.create(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("TeamType must not be null");
    }
}