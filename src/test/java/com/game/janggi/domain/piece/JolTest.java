package com.game.janggi.domain.piece;

import com.game.janggi.domain.team.TeamType;
import com.game.janggi.exception.NeedStopException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class JolTest {
    @DisplayName("졸은 초나라 타입으로 생성할 수 있다")
    @Test
    void createJol() {
        // when
        Jol jol = Jol.create(TeamType.CHO);

        // then
        assertThat(jol).isNotNull();
        assertThat(jol.printPieceName()).isEqualTo("졸");
    }

    @DisplayName("졸은 한나라 타입으로 생성될 수 없다.")
    @Test
    void createJolFail() {
        // when // then
        assertThatThrownBy(() -> Jol.create(TeamType.HAN))
                .isInstanceOf(NeedStopException.class)
                .hasMessageContaining("졸은 초나라의 기물입니다");
    }
}