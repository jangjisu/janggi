package com.game.janggi.domain.piece.layout;

import com.game.janggi.domain.formation.FormationType;
import com.game.janggi.domain.piece.*;
import com.game.janggi.domain.piece.position.PiecePosition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ChoDefaultPieceLayoutTest {
    PiecePosition leftChaPosition = PiecePosition.create(0, 9);
    PiecePosition rightChaPosition = PiecePosition.create(8, 9);
    PiecePosition leftSaPosition = PiecePosition.create(3, 9);
    PiecePosition rightSaPosition = PiecePosition.create(5, 9);
    PiecePosition kingPosition = PiecePosition.create(4, 8);
    PiecePosition leftPoPosition = PiecePosition.create(1, 7);
    PiecePosition rightPoPosition = PiecePosition.create(7, 7);
    PiecePosition jol1Position = PiecePosition.create(0, 6);
    PiecePosition jol2Position = PiecePosition.create(2, 6);
    PiecePosition jol3Position = PiecePosition.create(4, 6);
    PiecePosition jol4Position = PiecePosition.create(6, 6);
    PiecePosition jol5Position = PiecePosition.create(8, 6);


    PiecePosition firstFluidPosition = PiecePosition.create(1, 9);
    PiecePosition secondFluidPosition = PiecePosition.create(2, 9);
    PiecePosition thirdFluidPosition = PiecePosition.create(6, 9);
    PiecePosition fourthFluidPosition = PiecePosition.create(7, 9);


    @DisplayName("상마마상 형식의 초나라 기물 배치 테스트")
    @Test
    void sang_ma_ma_sang_formation() {
        // given
        FormationType formationType = FormationType.SANG_MA_MA_SANG;

        // when
        ChoDefaultPieceLayout choDefaultPieceLayout = new ChoDefaultPieceLayout(formationType);

        Map<PiecePosition, Piece> pieces = choDefaultPieceLayout.createPieces();

        // then
        assertThat(pieces).hasSize(16);
        assertThat(pieces.get(leftChaPosition)).isInstanceOf(Cha.class);
        assertThat(pieces.get(rightChaPosition)).isInstanceOf(Cha.class);
        assertThat(pieces.get(leftSaPosition)).isInstanceOf(Sa.class);
        assertThat(pieces.get(rightSaPosition)).isInstanceOf(Sa.class);
        assertThat(pieces.get(kingPosition)).isInstanceOf(King.class);
        assertThat(pieces.get(leftPoPosition)).isInstanceOf(Po.class);
        assertThat(pieces.get(rightPoPosition)).isInstanceOf(Po.class);
        assertThat(pieces.get(jol1Position)).isInstanceOf(Jol.class);
        assertThat(pieces.get(jol2Position)).isInstanceOf(Jol.class);
        assertThat(pieces.get(jol3Position)).isInstanceOf(Jol.class);
        assertThat(pieces.get(jol4Position)).isInstanceOf(Jol.class);
        assertThat(pieces.get(jol5Position)).isInstanceOf(Jol.class);
        assertThat(pieces.get(firstFluidPosition)).isInstanceOf(Sang.class);
        assertThat(pieces.get(secondFluidPosition)).isInstanceOf(Ma.class);
        assertThat(pieces.get(thirdFluidPosition)).isInstanceOf(Ma.class);
        assertThat(pieces.get(fourthFluidPosition)).isInstanceOf(Sang.class);
    }

    @DisplayName("상마상마 형식의 초나라 기물 배치 테스트")
    @Test
    void sang_ma_sang_ma_formation() {
        // given
        FormationType formationType = FormationType.SANG_MA_SANG_MA;

        // when
        ChoDefaultPieceLayout choDefaultPieceLayout = new ChoDefaultPieceLayout(formationType);

        Map<PiecePosition, Piece> pieces = choDefaultPieceLayout.createPieces();

        // then
        assertThat(pieces).hasSize(16);
        assertThat(pieces.get(leftChaPosition)).isInstanceOf(Cha.class);
        assertThat(pieces.get(rightChaPosition)).isInstanceOf(Cha.class);
        assertThat(pieces.get(leftSaPosition)).isInstanceOf(Sa.class);
        assertThat(pieces.get(rightSaPosition)).isInstanceOf(Sa.class);
        assertThat(pieces.get(kingPosition)).isInstanceOf(King.class);
        assertThat(pieces.get(leftPoPosition)).isInstanceOf(Po.class);
        assertThat(pieces.get(rightPoPosition)).isInstanceOf(Po.class);
        assertThat(pieces.get(jol1Position)).isInstanceOf(Jol.class);
        assertThat(pieces.get(jol2Position)).isInstanceOf(Jol.class);
        assertThat(pieces.get(jol3Position)).isInstanceOf(Jol.class);
        assertThat(pieces.get(jol4Position)).isInstanceOf(Jol.class);
        assertThat(pieces.get(jol5Position)).isInstanceOf(Jol.class);
        assertThat(pieces.get(firstFluidPosition)).isInstanceOf(Sang.class);
        assertThat(pieces.get(secondFluidPosition)).isInstanceOf(Ma.class);
        assertThat(pieces.get(thirdFluidPosition)).isInstanceOf(Sang.class);
        assertThat(pieces.get(fourthFluidPosition)).isInstanceOf(Ma.class);
    }

    @DisplayName("마상마상 형식의 초나라 기물 배치 테스트")
    @Test
    void ma_sang_ma_sang_formation() {
        // given
        FormationType formationType = FormationType.MA_SANG_MA_SANG;

        // when
        ChoDefaultPieceLayout choDefaultPieceLayout = new ChoDefaultPieceLayout(formationType);

        Map<PiecePosition, Piece> pieces = choDefaultPieceLayout.createPieces();

        // then
        assertThat(pieces).hasSize(16);
        assertThat(pieces.get(leftChaPosition)).isInstanceOf(Cha.class);
        assertThat(pieces.get(rightChaPosition)).isInstanceOf(Cha.class);
        assertThat(pieces.get(leftSaPosition)).isInstanceOf(Sa.class);
        assertThat(pieces.get(rightSaPosition)).isInstanceOf(Sa.class);
        assertThat(pieces.get(kingPosition)).isInstanceOf(King.class);
        assertThat(pieces.get(leftPoPosition)).isInstanceOf(Po.class);
        assertThat(pieces.get(rightPoPosition)).isInstanceOf(Po.class);
        assertThat(pieces.get(jol1Position)).isInstanceOf(Jol.class);
        assertThat(pieces.get(jol2Position)).isInstanceOf(Jol.class);
        assertThat(pieces.get(jol3Position)).isInstanceOf(Jol.class);
        assertThat(pieces.get(jol4Position)).isInstanceOf(Jol.class);
        assertThat(pieces.get(jol5Position)).isInstanceOf(Jol.class);
        assertThat(pieces.get(firstFluidPosition)).isInstanceOf(Ma.class);
        assertThat(pieces.get(secondFluidPosition)).isInstanceOf(Sang.class);
        assertThat(pieces.get(thirdFluidPosition)).isInstanceOf(Ma.class);
        assertThat(pieces.get(fourthFluidPosition)).isInstanceOf(Sang.class);
    }

    @DisplayName("마상상마 형식의 초나라 기물 배치 테스트")
    @Test
    void ma_sang_sang_ma_formation() {
        // given
        FormationType formationType = FormationType.MA_SANG_SANG_MA;

        // when
        ChoDefaultPieceLayout choDefaultPieceLayout = new ChoDefaultPieceLayout(formationType);

        Map<PiecePosition, Piece> pieces = choDefaultPieceLayout.createPieces();

        // then
        assertThat(pieces).hasSize(16);
        assertThat(pieces.get(leftChaPosition)).isInstanceOf(Cha.class);
        assertThat(pieces.get(rightChaPosition)).isInstanceOf(Cha.class);
        assertThat(pieces.get(leftSaPosition)).isInstanceOf(Sa.class);
        assertThat(pieces.get(rightSaPosition)).isInstanceOf(Sa.class);
        assertThat(pieces.get(kingPosition)).isInstanceOf(King.class);
        assertThat(pieces.get(leftPoPosition)).isInstanceOf(Po.class);
        assertThat(pieces.get(rightPoPosition)).isInstanceOf(Po.class);
        assertThat(pieces.get(jol1Position)).isInstanceOf(Jol.class);
        assertThat(pieces.get(jol2Position)).isInstanceOf(Jol.class);
        assertThat(pieces.get(jol3Position)).isInstanceOf(Jol.class);
        assertThat(pieces.get(jol4Position)).isInstanceOf(Jol.class);
        assertThat(pieces.get(jol5Position)).isInstanceOf(Jol.class);
        assertThat(pieces.get(firstFluidPosition)).isInstanceOf(Ma.class);
        assertThat(pieces.get(secondFluidPosition)).isInstanceOf(Sang.class);
        assertThat(pieces.get(thirdFluidPosition)).isInstanceOf(Sang.class);
        assertThat(pieces.get(fourthFluidPosition)).isInstanceOf(Ma.class);
    }
}