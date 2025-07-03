package com.game.janggi.domain.piece;

import com.game.janggi.domain.team.TeamType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class KingTest {
    @DisplayName("왕을 생성할 수 있다")
    @Test
    void createKing() {
        // when
        King king = King.create(TeamType.HAN);

        // then
        assertThat(king).isNotNull();
        assertThat(king.printPieceName()).isEqualTo("왕");
    }
}