package com.game.janggi.domain.board;

import com.game.janggi.domain.formation.FormationType;
import com.game.janggi.domain.piece.Cha;
import com.game.janggi.domain.piece.Piece;
import com.game.janggi.domain.piece.position.PiecePosition;
import com.game.janggi.domain.team.TeamType;
import com.game.janggi.exception.RecoverableException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


class GameBoardTest {
    GameBoard gameBoard;

    @BeforeEach
    void setUp() {
        gameBoard = GameBoard.initializePieces(FormationType.SANG_MA_MA_SANG, FormationType.SANG_MA_SANG_MA);
    }

    @Test
    @DisplayName("게임 보드를 생성하면 32개의 말이 생성되고, 초의 턴 상태가 되며 선택된 기물상태는 null 이 된다..")
    void initialize_pieces () {
        //when //then
        assertThat(gameBoard.getPiecesSize()).isEqualTo(32);
        assertThat(gameBoard.getCurrentTurn()).isEqualTo(TeamType.CHO);
        assertThat(gameBoard.haveSelectedPiece()).isFalse();
    }

    @DisplayName("턴을 변경하면 선택 팀이 변경되며 선택된 기물 상태는 null 이 된다.")
    @Test
    void change_turn() {
        // given // when
        gameBoard.changeTurn();

        // then
        assertThat(gameBoard.getCurrentTurn()).isEqualTo(TeamType.HAN);
        assertThat(gameBoard.haveSelectedPiece()).isFalse();
    }

    @DisplayName("해당 위치에 있는 기물을 가져온다")
    @Test
    void getPieceAt() {
        // given
        PiecePosition piecePosition = PiecePosition.create(0, 0);

        // when
        Optional<Piece> pieceCandidate = gameBoard.getPieceAt(piecePosition);

        // then
        assertThat(pieceCandidate).hasValueSatisfying(piece ->
                assertThat(piece).isInstanceOf(Cha.class));
    }

    @DisplayName("움직일 수 없는 기물을 선택하면 예외가 발생한다.")
    @Test
    void select_validate_piece() {
        // given
        PiecePosition piecePosition = PiecePosition.create(1, 9);

        // when
        gameBoard.validatePieceSelection(piecePosition);

        // then
        assertThat(gameBoard.haveSelectedPiece()).isTrue();
    }

    @DisplayName("선택한 좌표에 기물이 없다면 예외가 발생한다.")
    @Test
    void check_select_piece_null() {
        // given
        PiecePosition piecePosition = PiecePosition.create(0, 1);

        // when // then
        assertThatThrownBy(() -> gameBoard.validatePieceSelection(piecePosition))
                .isInstanceOf(RecoverableException.class)
                .hasMessageContaining("선택한 위치에 말이 없습니다.");
    }

    @DisplayName("선택한 좌표에 상대팀 기물이 있다면 예외가 발생한다.")
    @Test
    void check_select_another_team_piece() {
        // given
        PiecePosition piecePosition = PiecePosition.create(8, 0);

        // when // then
        assertThatThrownBy(() -> gameBoard.validatePieceSelection(piecePosition))
                .isInstanceOf(RecoverableException.class)
                .hasMessageContaining("상대 진영의 말을 선택할 수 없습니다.");
    }

    @DisplayName("움직일 수 없는 기물을 선택하면 예외가 발생한다.")
    @Test
    void check_select_cannot_move_piece() {
        // given
        PiecePosition piecePosition = PiecePosition.create(1, 7);

        // when // then
        assertThatThrownBy(() -> gameBoard.validatePieceSelection(piecePosition))
                .isInstanceOf(RecoverableException.class)
                .hasMessageContaining("이동할 수 없는 말입니다.");
    }

    @DisplayName("위치에 기물이 없다면 Null을 반환한다")
    @Test
    void getPieceAtEmpty() {
        // given
        PiecePosition piecePosition = PiecePosition.create(0, 1);

        // when
        Optional<Piece> pieceCandidate = gameBoard.getPieceAt(piecePosition);

        // then
        assertThat(pieceCandidate).isEmpty();
    }

    @DisplayName("이동에 성공하면 기물의 위치가 변경된다.")
    @Test
    void move_normal() {
        // given
        PiecePosition currentPosition = PiecePosition.create(0, 6);
        PiecePosition willMovePosition = PiecePosition.create(0, 5);

        gameBoard.validatePieceSelection(currentPosition);

        // when
        gameBoard.validateAndMovePiece(currentPosition, willMovePosition);

        // then
        assertThat(gameBoard.getPieceAt(currentPosition)).isEmpty();
        assertThat(gameBoard.getPieceAt(willMovePosition)).isNotNull();
    }

    @DisplayName("이동할 수 없는 위치로 이동하려고 하면 예외가 발생한다.")
    @Test
    void move_fail() {
        // given
        PiecePosition currentPosition = PiecePosition.create(0, 6);
        PiecePosition willMovePosition = PiecePosition.create(0, 7);

        gameBoard.validatePieceSelection(currentPosition);

        // when // then
        assertThatThrownBy(() -> gameBoard.validateAndMovePiece(currentPosition, willMovePosition))
                .isInstanceOf(RecoverableException.class)
                .hasMessageContaining("이동할 수 없습니다.");
    }

    @DisplayName("게임이 진행중이면 게임이 종료되지 않은 상태이다.")
    @Test
    void gameInProgress() {
        // given //when
        gameBoard.changeGameStatus();

        // then
        assertThat(gameBoard.isGameIsInProgress()).isTrue();
    }

    @DisplayName("왕이 죽으면 게임이 종료된 상태로 변경된다.")
    @Test
    void checkGameInProgress() {
        // given
        killHanKing();

        // when
        gameBoard.changeGameStatus();

        // then
        assertThat(gameBoard.isGameIsInProgress()).isFalse();
    }

    private void killHanKing() {
        PiecePosition first = PiecePosition.create(0, 6);
        PiecePosition firstMove = PiecePosition.create(1, 6);

        PiecePosition second = PiecePosition.create(0, 3);
        PiecePosition secondMove = PiecePosition.create(1, 3);

        PiecePosition third = PiecePosition.create(0, 9);
        PiecePosition thirdMove = PiecePosition.create(0, 1);

        PiecePosition fourth = PiecePosition.create(8, 0);
        PiecePosition fourthMove = PiecePosition.create(8, 1);

        PiecePosition fifth = PiecePosition.create(0, 1);
        PiecePosition fifthMove = PiecePosition.create(4, 1);

        gameBoard.validatePieceSelection(first);
        gameBoard.validateAndMovePiece(first, firstMove);
        gameBoard.changeTurn();
        gameBoard.validatePieceSelection(second);
        gameBoard.validateAndMovePiece(second, secondMove);
        gameBoard.changeTurn();
        gameBoard.validatePieceSelection(third);
        gameBoard.validateAndMovePiece(third, thirdMove);
        gameBoard.changeTurn();
        gameBoard.validatePieceSelection(fourth);
        gameBoard.validateAndMovePiece(fourth, fourthMove);
        gameBoard.changeTurn();
        gameBoard.validatePieceSelection(fifth);
        gameBoard.validateAndMovePiece(fifth, fifthMove);
    }


}