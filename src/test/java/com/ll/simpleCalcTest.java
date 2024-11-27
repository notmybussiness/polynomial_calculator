package com.ll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class simpleCalcTest {
    @Test
    @DisplayName("더하기 기능 2+3 =5 테스트")
    public void testPlus(){
        SimpleCalculator simpleCalculator = new SimpleCalculator();
        int result = simpleCalculator.Plus(2,3);

        assertThat(result).isEqualTo(5);

    }
}
