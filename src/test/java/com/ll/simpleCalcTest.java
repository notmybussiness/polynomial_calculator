package com.ll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

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
    @DisplayName("다항식 입력에 대해서 사칙연산 봐보자")
    void t6(){
        double rs = simpleCalculator.calculateExpression("2+3");
        assertThat(rs).isEqualTo(5);
    }
    @Test
    @DisplayName("괄호없는 다항식 뺄셈,곱셈 테스트")
    void t7(){
        double rs = simpleCalculator.calculateExpression("10-9");
        assertThat(rs).isEqualTo(1);

    }
    @Test
    @DisplayName("괄호없는 뺄셈")
    void t8(){
        double rs = simpleCalculator.calculateExpression("5-6");
        assertThat(rs).isEqualTo(-1);

    }
    @Test
    @DisplayName("괄호없는 곱셈")//소숫점은 어쩔수없이 오차가 생기기 때문에 테스트시에 isCloseTo(expect, within(오차범위));
    void t9(){
        double rs = simpleCalculator.calculateExpression("5*0.05");
        //소숫점은 어쩔수없이 오차가 생기기 때문에
        // 테스트시에 isCloseTo(expect, within(오차범위));
        assertThat(rs).isCloseTo(0.25, within(0.0001));
        rs = simpleCalculator.calculateExpression("0.2*0.05");
        assertThat(rs).isCloseTo(0.01, within(0.00001));
    }

    @Test
    @DisplayName("괄호없는 나눗셈")
    void t10(){
        double rs = simpleCalculator.calculateExpression("5/6");
        //무한소수
        assertThat(rs).isCloseTo(0.833,within(0.001));
        rs = simpleCalculator.calculateExpression("111/3");
        assertThat(rs).isCloseTo(37.0, within(0.1));
        rs = simpleCalculator.calculateExpression("5/0.00001");
        assertThat(rs).isCloseTo(500000.0, within(0.1));
    }

    @Test
    @DisplayName("연산자가 2개 이상 1+2*3")
    void t11(){
        double rs = simpleCalculator.calculateExpression("1+2*3");
        assertThat(rs).isEqualTo(7);
    }
}
