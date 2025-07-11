package com.game.janggi.domain.piece.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class GongPiecePositionTest {
    @Test
    @DisplayName("모든 궁 위치를 반환한다.")
    void getGongPosition() {
        //given //when
        List<PiecePosition> gongPositions = GongPiecePosition.getGongPositions();

        //then
        assertThat(gongPositions).hasSize(18)
                .containsExactlyInAnyOrder(
                        PiecePosition.create(3, 0),
                        PiecePosition.create(4, 0),
                        PiecePosition.create(5, 0),
                        PiecePosition.create(3, 1),
                        PiecePosition.create(4, 1),
                        PiecePosition.create(5, 1),
                        PiecePosition.create(3, 2),
                        PiecePosition.create(4, 2),
                        PiecePosition.create(5, 2),
                        PiecePosition.create(3, 9),
                        PiecePosition.create(4, 9),
                        PiecePosition.create(5, 9),
                        PiecePosition.create(3, 8),
                        PiecePosition.create(4, 8),
                        PiecePosition.create(5, 8),
                        PiecePosition.create(3, 7),
                        PiecePosition.create(4, 7),
                        PiecePosition.create(5, 7)
                );
    }

    @Test
    @DisplayName("포가 대각선으로 움직일 수 있는 포지션을 반환한다")
    void getPoCanDigonalGongPositions() {
        //given //when
        List<PiecePosition> piecePositions = GongPiecePosition.getPoCanDiagonalGongPositions();

        //then
        assertThat(piecePositions).hasSize(8)
                .containsExactlyInAnyOrder(
                        PiecePosition.create(3, 0),
                        PiecePosition.create(5, 0),
                        PiecePosition.create(3, 2),
                        PiecePosition.create(5, 2),
                        PiecePosition.create(3, 9),
                        PiecePosition.create(5, 9),
                        PiecePosition.create(3, 7),
                        PiecePosition.create(5, 7)
                );
    }

    @Test
    @DisplayName("한의 궁위치들을 반환한다")
    void getHanDiagonalGongPositions() {
        //given //when
        List<PiecePosition> gongPositions = GongPiecePosition.getHanDiagonalGongPositions();

        //then
        assertThat(gongPositions).hasSize(9)
                .containsExactlyInAnyOrder(
                        PiecePosition.create(3, 0),
                        PiecePosition.create(4, 0),
                        PiecePosition.create(5, 0),
                        PiecePosition.create(3, 1),
                        PiecePosition.create(4, 1),
                        PiecePosition.create(5, 1),
                        PiecePosition.create(3, 2),
                        PiecePosition.create(4, 2),
                        PiecePosition.create(5, 2)
                );
    }

    @Test
    @DisplayName("한 진형중 한 곳을 넣으면, 한 진형의 중간 포지션을 반환한다")
    void getHanGongCenterPosition() {
        //given //when
        PiecePosition gongCenterPosition = GongPiecePosition.getGongCenterPosition(PiecePosition.create(3, 0));

        //then
        assertThat(gongCenterPosition).isEqualTo(PiecePosition.create(4, 1));

    }

    @Test
    @DisplayName("초 진형중 한 곳을 넣으면, 초 진형의 중간 포지션을 반환한다")
    void getChoGongCenterPosition() {
        //given //when
        PiecePosition gongCenterPosition = GongPiecePosition.getGongCenterPosition(PiecePosition.create(3, 9));

        //then
        assertThat(gongCenterPosition).isEqualTo(PiecePosition.create(4, 8));

    }

    @Test
    @DisplayName("포가 이동할 대각선 위치를 반환한다.")
    void getOppositePosition () {
        //given //when
        PiecePosition oppositePosition = GongPiecePosition.getOppositeGongPosition(PiecePosition.create(3, 9));

        //then
        assertThat(oppositePosition).isEqualTo(PiecePosition.create(5, 7));

    }

    @Test
    @DisplayName("궁 밖에는 이동할 대각선 위치를 반환할 수 없다.")
    void getOppositePositionFail () {
        //given //when //then
        assertThatThrownBy(() -> GongPiecePosition.getOppositeGongPosition(PiecePosition.create(0, 0)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("해당 위치의 반대 위치가 없습니다.");

    }

    @Test
    @DisplayName("입력한 포지션이 궁 내부인지 확인한다.")
    void isInGongPosition () {
        //given //when
        boolean inGongPosition = GongPiecePosition.isInGongPosition(PiecePosition.create(4, 1));

        //then
        assertThat(inGongPosition).isTrue();
    }

    @Test
    @DisplayName("입력한 포지션이 궁 내부인지 확인한다.")
    void isInGongPositionFail () {
        //given //when
        boolean inGongPosition = GongPiecePosition.isInGongPosition(PiecePosition.create(0, 0));

        //then
        assertThat(inGongPosition).isFalse();
    }

    @Test
    @DisplayName("궁 내부이고 대각선으로 이동할 수 있는 위치인지 확인한다.")
    void canMoveDiagonal () {
        //given //when
        boolean canMoveDiagonal = GongPiecePosition.canMoveDiagonal(PiecePosition.create(4, 1));

        //then
        assertThat(canMoveDiagonal).isTrue();
    }

    @Test
    @DisplayName("궁 내부이고 대각선으로 이동할 수 있는 위치인지 확인한다.")
    void canNotMoveDiagonalInGong () {
        //given //when
        boolean canMoveDiagonal = GongPiecePosition.canMoveDiagonal(PiecePosition.create(4, 0));

        //then
        assertThat(canMoveDiagonal).isFalse();
    }

    @Test
    @DisplayName("궁 외부일경우 대각선으로 움직일 수 없다.")
    void canNotMoveDiagonal () {
        //given //when
        boolean canMoveDiagonal = GongPiecePosition.canMoveDiagonal(PiecePosition.create(0, 0));

        //then
        assertThat(canMoveDiagonal).isFalse();
    }


}