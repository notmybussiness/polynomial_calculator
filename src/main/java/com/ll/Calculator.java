package com.ll;
//
class Calculator {
    public Calculator() {
    }

    public double plus(double num1, double num2) {
        return num1 + num2;
    }

    public double minus(double num1, double num2) {
        return num1 - num2;
    }

    public double multiply(double num1, double num2) {
        return num1 * num2;
    }

    public double divide(double num1, double num2) {
        return num1 / num2;
    }

    public int[] findNumber(String expression, int operatorIndex) {
        int leftIndex = operatorIndex-1;
        int rightIndex = operatorIndex + 1;

        //다음 연산자가 나올때까지 왼쪽숫자 오른쪽숫자 각각 구하기
        while (leftIndex >= 0 && (Character.isDigit(expression.charAt(leftIndex)))||
                (expression.charAt(leftIndex) =='-' && leftIndex == 0) ||
                expression.charAt(leftIndex) =='.'){
            leftIndex--;
            if(leftIndex < 0) break;
        }

       leftIndex++;

        while (rightIndex < expression.length() && Character.isDigit(expression.charAt(rightIndex))||
                expression.charAt(rightIndex) =='.'||
                (expression.charAt(rightIndex) =='-' && rightIndex == operatorIndex+1)){
            rightIndex++;
            if(rightIndex >= expression.length()) break;
        }
        //return 인수는 숫자가 시작하는 부분 / 연산자가 나오는부분 이렇게 됨
        return new int[]{leftIndex, rightIndex};
    }

    public int getPriorityOperator(String expression){
        int operatorIndex = -1;
        for (int i = 0; i < expression.length(); i++) {
            char cur = expression.charAt(i);
            if (cur == '*' || cur == '/') {
                operatorIndex = i;
                return operatorIndex;
            }
        }
        for (int i = 0; i < expression.length(); i++) {
            char cur = expression.charAt(i);
            if (cur == '+' || cur == '-') {
                operatorIndex = i;
                return operatorIndex;
            }
        }
        return operatorIndex;
    }


    public double calculateExpression(String expression) {
        //재귀 종료조건
        if (!expression.substring(1).contains("+") && !expression.substring(1).contains("-") &&
                !expression.substring(1).contains("*") && !expression.substring(1).contains("/")) return Double.parseDouble(expression);

        // 연산자 찾기 - getPriorityOperator 메소드로 분리
        int firstOperatorIndex = getPriorityOperator(expression);;

        double temp = 0;
        //곱 나눗셈 처리
        if (firstOperatorIndex != -1) {
            int[] flag = findNumber(expression, firstOperatorIndex);

            //범위 체크
            if(flag[0] >= firstOperatorIndex || flag[1] <= firstOperatorIndex){
                System.out.println(firstOperatorIndex + "and flag value is" +flag[0]+" and" + flag[1]);
                throw new IllegalArgumentException("Invalid range");
            }
            double leftNumber = Double.parseDouble(expression.substring(flag[0], firstOperatorIndex));
//            double rightNumber = Double.parseDouble(expression.substring(firstOperatorIndex + 1, flag[1]));
            double rightNumber = Double.parseDouble(expression.substring(firstOperatorIndex + 1, flag[1]));

            if (expression.charAt(firstOperatorIndex) == '*') {
                temp = multiply(leftNumber, rightNumber);
            } else if (expression.charAt(firstOperatorIndex) == '/') {
                temp = divide(leftNumber, rightNumber);
            } else if (expression.charAt(firstOperatorIndex) == '+') {
                temp = plus(leftNumber, rightNumber);
            } else if (expression.charAt(firstOperatorIndex) == '-') {
                temp = minus(leftNumber, rightNumber);
            }

            String newExpression = expression.substring(0, flag[0])
                                    + temp
                                    + expression.substring(flag[1]);;

            return calculateExpression(newExpression);
        }
        return 0;
    }
}
