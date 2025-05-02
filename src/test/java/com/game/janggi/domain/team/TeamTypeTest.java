package com.game.janggi.domain.team;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TeamTypeTest {

    @Test
    @DisplayName("선택한 팀의 반대되는 팀을 가져올 수 있다.")
    void getOppositeTeam () {
        //given
        TeamType teamType = TeamType.CHO;

        //when
        TeamType oppositeTeam = teamType.getOppositeTeam();

        //then
        assertThat(oppositeTeam).isEqualTo(TeamType.HAN);
    }
}