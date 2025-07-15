package com.game.janggi.domain.board;

import com.game.janggi.domain.team.TeamType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GameStatusTest {
    @DisplayName("한이 이기면 HAN_WIN 반환한다.")
    @Test
    void hanWin() {
        // given // when
        GameStatus result = GameStatus.IN_PROGRESS.changeGameEnd(TeamType.HAN);

        // then
        assertThat(result).isEqualTo(GameStatus.HAN_WIN);
    }

    @DisplayName("초가 이기면 HAN_WIN 반환한다.")
    @Test
    void choWin() {
        // given // when
        GameStatus result = GameStatus.IN_PROGRESS.changeGameEnd(TeamType.CHO);

        // then
        assertThat(result).isEqualTo(GameStatus.CHO_WIN);
    }
}