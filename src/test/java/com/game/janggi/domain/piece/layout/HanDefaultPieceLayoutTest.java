package com.game.janggi.domain.piece.layout;

import com.game.janggi.domain.formation.FormationType;
import com.game.janggi.domain.piece.*;
import com.game.janggi.domain.piece.position.PiecePosition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class HanDefaultPieceLayoutTest {
    PiecePosition leftChaPosition = new PiecePosition(0, 0);
    PiecePosition rightChaPosition = new PiecePosition(8, 0);
    PiecePosition leftSaPosition = new PiecePosition(3, 0);
    PiecePosition rightSaPosition = new PiecePosition(5, 0);
    PiecePosition kingPosition = new PiecePosition(4, 1);
    PiecePosition leftPoPosition = new PiecePosition(1, 2);
    PiecePosition rightPoPosition = new PiecePosition(7, 2);
    PiecePosition bung1Position = new PiecePosition(0, 3);
    PiecePosition bung2Position = new PiecePosition(2, 3);
    PiecePosition bung3Position = new PiecePosition(4, 3);
    PiecePosition bung4Position = new PiecePosition(6, 3);
    PiecePosition bung5Position = new PiecePosition(8, 3);

    PiecePosition firstFluidPosition = new PiecePosition(1, 0);
    PiecePosition secondFluidPosition = new PiecePosition(2, 0);
    PiecePosition thirdFluidPosition = new PiecePosition(6, 0);
    PiecePosition fourthFluidPosition = new PiecePosition(7, 0);

    @DisplayName("상마마상 형식의 한나라 기물 배치 테스트")
    @Test
    void sang_ma_ma_sang_formation() {
        // given
        FormationType formationType = FormationType.SANG_MA_MA_SANG;

        // when
        HanDefaultPieceLayout hanDefaultPieceLayout = new HanDefaultPieceLayout(formationType);

        Map<PiecePosition, Piece> pieces = hanDefaultPieceLayout.createPieces();

        // then
        assertThat(pieces).hasSize(16);
        assertThat(pieces.get(leftChaPosition)).isInstanceOf(Cha.class);
        assertThat(pieces.get(rightChaPosition)).isInstanceOf(Cha.class);
        assertThat(pieces.get(leftSaPosition)).isInstanceOf(Sa.class);
        assertThat(pieces.get(rightSaPosition)).isInstanceOf(Sa.class);
        assertThat(pieces.get(kingPosition)).isInstanceOf(King.class);
        assertThat(pieces.get(leftPoPosition)).isInstanceOf(Po.class);
        assertThat(pieces.get(rightPoPosition)).isInstanceOf(Po.class);
        assertThat(pieces.get(bung1Position)).isInstanceOf(Bung.class);
        assertThat(pieces.get(bung2Position)).isInstanceOf(Bung.class);
        assertThat(pieces.get(bung3Position)).isInstanceOf(Bung.class);
        assertThat(pieces.get(bung4Position)).isInstanceOf(Bung.class);
        assertThat(pieces.get(bung5Position)).isInstanceOf(Bung.class);
        assertThat(pieces.get(firstFluidPosition)).isInstanceOf(Sang.class);
        assertThat(pieces.get(secondFluidPosition)).isInstanceOf(Ma.class);
        assertThat(pieces.get(thirdFluidPosition)).isInstanceOf(Ma.class);
        assertThat(pieces.get(fourthFluidPosition)).isInstanceOf(Sang.class);
    }

    @DisplayName("상마상마 형식의 한나라 기물 배치 테스트")
    @Test
    void sang_ma_sang_ma_formation() {
        // given
        FormationType formationType = FormationType.SANG_MA_SANG_MA;

        // when
        HanDefaultPieceLayout hanDefaultPieceLayout = new HanDefaultPieceLayout(formationType);

        Map<PiecePosition, Piece> pieces = hanDefaultPieceLayout.createPieces();

        // then
        assertThat(pieces).hasSize(16);
        assertThat(pieces.get(leftChaPosition)).isInstanceOf(Cha.class);
        assertThat(pieces.get(rightChaPosition)).isInstanceOf(Cha.class);
        assertThat(pieces.get(leftSaPosition)).isInstanceOf(Sa.class);
        assertThat(pieces.get(rightSaPosition)).isInstanceOf(Sa.class);
        assertThat(pieces.get(kingPosition)).isInstanceOf(King.class);
        assertThat(pieces.get(leftPoPosition)).isInstanceOf(Po.class);
        assertThat(pieces.get(rightPoPosition)).isInstanceOf(Po.class);
        assertThat(pieces.get(bung1Position)).isInstanceOf(Bung.class);
        assertThat(pieces.get(bung2Position)).isInstanceOf(Bung.class);
        assertThat(pieces.get(bung3Position)).isInstanceOf(Bung.class);
        assertThat(pieces.get(bung4Position)).isInstanceOf(Bung.class);
        assertThat(pieces.get(bung5Position)).isInstanceOf(Bung.class);
        assertThat(pieces.get(firstFluidPosition)).isInstanceOf(Sang.class);
        assertThat(pieces.get(secondFluidPosition)).isInstanceOf(Ma.class);
        assertThat(pieces.get(thirdFluidPosition)).isInstanceOf(Sang.class);
        assertThat(pieces.get(fourthFluidPosition)).isInstanceOf(Ma.class);
    }

    @DisplayName("마상마상 형식의 한나라 기물 배치 테스트")
    @Test
    void ma_sang_ma_sang_formation() {
        // given
        FormationType formationType = FormationType.MA_SANG_MA_SANG;

        // when
        HanDefaultPieceLayout hanDefaultPieceLayout = new HanDefaultPieceLayout(formationType);

        Map<PiecePosition, Piece> pieces = hanDefaultPieceLayout.createPieces();

        // then
        assertThat(pieces).hasSize(16);
        assertThat(pieces.get(leftChaPosition)).isInstanceOf(Cha.class);
        assertThat(pieces.get(rightChaPosition)).isInstanceOf(Cha.class);
        assertThat(pieces.get(leftSaPosition)).isInstanceOf(Sa.class);
        assertThat(pieces.get(rightSaPosition)).isInstanceOf(Sa.class);
        assertThat(pieces.get(kingPosition)).isInstanceOf(King.class);
        assertThat(pieces.get(leftPoPosition)).isInstanceOf(Po.class);
        assertThat(pieces.get(rightPoPosition)).isInstanceOf(Po.class);
        assertThat(pieces.get(bung1Position)).isInstanceOf(Bung.class);
        assertThat(pieces.get(bung2Position)).isInstanceOf(Bung.class);
        assertThat(pieces.get(bung3Position)).isInstanceOf(Bung.class);
        assertThat(pieces.get(bung4Position)).isInstanceOf(Bung.class);
        assertThat(pieces.get(bung5Position)).isInstanceOf(Bung.class);
        assertThat(pieces.get(firstFluidPosition)).isInstanceOf(Ma.class);
        assertThat(pieces.get(secondFluidPosition)).isInstanceOf(Sang.class);
        assertThat(pieces.get(thirdFluidPosition)).isInstanceOf(Ma.class);
        assertThat(pieces.get(fourthFluidPosition)).isInstanceOf(Sang.class);
    }

    @DisplayName("마상상마 형식의 한나라 기물 배치 테스트")
    @Test
    void ma_sang_sang_ma_formation() {
        // given
        FormationType formationType = FormationType.MA_SANG_SANG_MA;

        // when
        HanDefaultPieceLayout hanDefaultPieceLayout = new HanDefaultPieceLayout(formationType);

        Map<PiecePosition, Piece> pieces = hanDefaultPieceLayout.createPieces();

        // then
        assertThat(pieces).hasSize(16);
        assertThat(pieces.get(leftChaPosition)).isInstanceOf(Cha.class);
        assertThat(pieces.get(rightChaPosition)).isInstanceOf(Cha.class);
        assertThat(pieces.get(leftSaPosition)).isInstanceOf(Sa.class);
        assertThat(pieces.get(rightSaPosition)).isInstanceOf(Sa.class);
        assertThat(pieces.get(kingPosition)).isInstanceOf(King.class);
        assertThat(pieces.get(leftPoPosition)).isInstanceOf(Po.class);
        assertThat(pieces.get(rightPoPosition)).isInstanceOf(Po.class);
        assertThat(pieces.get(bung1Position)).isInstanceOf(Bung.class);
        assertThat(pieces.get(bung2Position)).isInstanceOf(Bung.class);
        assertThat(pieces.get(bung3Position)).isInstanceOf(Bung.class);
        assertThat(pieces.get(bung4Position)).isInstanceOf(Bung.class);
        assertThat(pieces.get(bung5Position)).isInstanceOf(Bung.class);
        assertThat(pieces.get(firstFluidPosition)).isInstanceOf(Ma.class);
        assertThat(pieces.get(secondFluidPosition)).isInstanceOf(Sang.class);
        assertThat(pieces.get(thirdFluidPosition)).isInstanceOf(Sang.class);
        assertThat(pieces.get(fourthFluidPosition)).isInstanceOf(Ma.class);
    }
}