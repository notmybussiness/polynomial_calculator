package com.ll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class simpleCalcTest {
    Calculator simpleCalculator = new Calculator();
    @Test
    @DisplayName("더하기 기능 2+3 =5 테스트")
    public void t1(){
//        SimpleCalculator simpleCalculator = new SimpleCalculator();
        double result = simpleCalculator.plus(2,3);

        assertThat(result).isEqualTo(5);
    }
    @Test
    @DisplayName("더하기 기능 여러번 테스트")
    void t2(){
//        SimpleCalculator simpleCalculator = new SimpleCalculator();
        double rs = simpleCalculator.plus(1,2);
        assertThat(rs).isEqualTo(3);
        rs = simpleCalculator.plus(5,187);
        assertThat(rs).isEqualTo(192);
        rs = simpleCalculator.plus(392,88);
        assertThat(rs).isEqualTo(480);
    }

    @Test
    @DisplayName("빼기 기능 5-9 = -4")
    void t3(){
        double rs = simpleCalculator.minus(5,9);
        assertThat(rs).isEqualTo(-4);
    }

    @Test
    @DisplayName("곱하기 기능 5*8 = 40")
    void t4(){
        double rs = simpleCalculator.multiply(5,8);
        assertThat(rs).isEqualTo(40);
    }
    @Test
    @DisplayName("나눗셈 기능 5/5 = 1, 그리고 그외에 안나눠지는것들도")
    void t5(){
        double rs = simpleCalculator.divide(6,3);
        assertThat(rs).isEqualTo(2);
        rs = simpleCalculator.divide(7,2);
        assertThat(rs).isEqualTo(3.5);

        rs = simpleCalculator.divide(8,9);
        assertThat(rs).isBetween(0.8888,0.9);
    }

    @Test
    @DisplayName("곱셈")
    void t6(){
        double rs = simpleCalculator.calculateExpression("2*3");
        assertThat(rs).isEqualTo(6);
    }

    @Test
    @DisplayName("나눗셈")
    void t7(){
        double rs = simpleCalculator.calculateExpression("8/2");
        assertThat(rs).isEqualTo(4);
    }

    @Test
    @DisplayName("다항식 곱 나눗셈")
    void t8(){
        double rs = simpleCalculator.calculateExpression("2*3/2");
        assertThat(rs).isEqualTo(3);
    }


}
