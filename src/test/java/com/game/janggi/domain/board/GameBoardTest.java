package com.game.janggi.domain.board;

import com.game.janggi.domain.formation.FormationType;
import com.game.janggi.domain.team.TeamType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class GameBoardTest {
    @Test
    @DisplayName("게임 보드를 생성하면 32개의 말이 생성되고, 초의 턴 상태가 된다.")
    void test () {
        //given
        GameBoard gameBoard = GameBoard.initializePieces(FormationType.SANG_MA_MA_SANG, FormationType.SANG_MA_MA_SANG);

        //when //then
        assertThat(gameBoard.getPiecesSize()).isEqualTo(32);
        assertThat(gameBoard.getCurrentTurn()).isEqualTo(TeamType.CHO);
    }



}