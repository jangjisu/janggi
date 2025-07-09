package com.game.janggi.domain.team;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TeamTypeTest {

    @Test
    @DisplayName("초의 반대팀은 한이다.")
    void getOppositeTeamOnCho() {
        //given
        TeamType teamType = TeamType.CHO;

        //when
        TeamType oppositeTeam = teamType.getOppositeTeam();

        //then
        assertThat(oppositeTeam).isEqualTo(TeamType.HAN);
    }

    @Test
    @DisplayName("한의 반대팀은 초이다.")
    void getOppositeTeamOnHan() {
        //given
        TeamType teamType = TeamType.HAN;

        //when
        TeamType oppositeTeam = teamType.getOppositeTeam();

        //then
        assertThat(oppositeTeam).isEqualTo(TeamType.CHO);
    }
}