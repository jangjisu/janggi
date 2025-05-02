package com.game.janggi.domain.formation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FormationTypeTest {

    @Test
    @DisplayName("숫자를 입력하면 그에 해당하는 포메이션을 리턴한다.")
    void getFormationFromNumber () {
        //given
        int formationNumber = 1;

        //when
        FormationType formationType = FormationType.getFromInputNumber(formationNumber);

        //then
        assertThat(formationType).isEqualTo(FormationType.SANG_MA_MA_SANG);
    }

    @Test
    @DisplayName("잘못된 숫자를 입력하면 예외를 던진다.")
    void getFormationFromNumberWithInvalidInput () {
        //given
        int formationNumber = 5;

        //when //then
        assertThatThrownBy(() -> FormationType.getFromInputNumber(formationNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Invalid input number");
    }
}