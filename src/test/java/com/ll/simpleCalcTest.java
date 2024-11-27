package com.ll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class simpleCalcTest {
    SimpleCalculator simpleCalculator = new SimpleCalculator();
    @Test
    @DisplayName("더하기 기능 2+3 =5 테스트")
    public void t1(){
//        SimpleCalculator simpleCalculator = new SimpleCalculator();
        int result = simpleCalculator.Plus(2,3);

        assertThat(result).isEqualTo(5);
    }
    @Test
    @DisplayName("더하기 기능 여러번 테스트")
    void t2(){
//        SimpleCalculator simpleCalculator = new SimpleCalculator();
        int rs = simpleCalculator.Plus(1,2);
        assertThat(rs).isEqualTo(3);
        rs = simpleCalculator.Plus(5,187);
        assertThat(rs).isEqualTo(192);
        rs = simpleCalculator.Plus(392,88);
        assertThat(rs).isEqualTo(480);
    }

    @Test
    @DisplayName("곱하기 기능 5*8 = 40")
    void t3(){
        int rs = simpleCalculator.Multiply(5,8);
        assertThat(rs).isEqualTo(40);
    }
}
