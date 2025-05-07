package com.game.janggi.domain.board;

import com.game.janggi.domain.formation.FormationType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class GameBoardTest {
    @Test
    @DisplayName("게임 보드를 생성하면 32개의 말이 생성되고, 초의 턴 상태가 되며 선택된 기물상태는 null 이 된다..")
    void initializePieces () {
        //given
        GameBoard gameBoard = GameBoard.initializePieces(FormationType.SANG_MA_MA_SANG, FormationType.SANG_MA_MA_SANG);

        //when //then
        assertThat(gameBoard.getPiecesSize()).isEqualTo(32);
        assertThat(gameBoard.isChoTurn()).isTrue();
        assertThat(gameBoard.haveSelectedPiece()).isFalse();
    }

    @DisplayName("턴을 변경하면 선택 팀이 변경되며 선택된 기물 상태는 null 이 된다.")
    @Test
    void changeTurn() {
        // given
        GameBoard gameBoard = GameBoard.initializePieces(FormationType.SANG_MA_MA_SANG, FormationType.SANG_MA_MA_SANG);

        // when
        gameBoard.changeTurn();

        // then
        assertThat(gameBoard.isHanTurn()).isTrue();
        assertThat(gameBoard.haveSelectedPiece()).isFalse();
    }


}