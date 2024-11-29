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

    public String excludeParenth(String expression){
        int[] parentheses = findInnerMostParentheses(expression);
        if(parentheses[0] == -1 && parentheses[1] == -1){
            return expression;
        }
        else{
            //괄호가 없는 ..
            String noparenthese = expression.substring(parentheses[0]+1,parentheses[1]);
            String newexpression =  expression.substring(0,parentheses[0])
                                    +calculateExpression(noparenthese)
                                    +expression.substring(parentheses[1]+1);
            return newexpression;
        }
    }

    private int[] findInnerMostParentheses(String expression) {
        int depth = 0;           // 현재 괄호의 깊이
        int maxDepth = 0;        // 지금까지 본 괄호 중 가장 깊은 깊이
        int startIndex = -1;     // 가장 안쪽 괄호의 시작 위치
        //depth 000011112222211111000 이렇게되면
        // 222222이부분 잘라서 먼저 calculateExrpession()해버림
        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == '(') {
                depth++;
                // 더 깊은 괄호를 발견하면 시작 위치를 갱신
                if (depth > maxDepth) {
                    maxDepth = depth;
                    startIndex = i;
                }
            } else if (expression.charAt(i) == ')' && depth == maxDepth) {
                // 가장 깊은 깊이의 닫는 괄호를 찾으면 즉시 반환
                return new int[]{startIndex, i};
            } else if (expression.charAt(i) == ')') {
                depth--;
            }
        }
        return new int[]{-1, -1}; // 괄호가 없는 경우
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

        while ((rightIndex < expression.length() && Character.isDigit(expression.charAt(rightIndex))) ||
                (rightIndex < expression.length() && expression.charAt(rightIndex) =='.') ||
                (rightIndex < expression.length() && expression.charAt(rightIndex) =='-' && rightIndex == operatorIndex+1)){
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
        while(expression.contains("(")){
            expression = excludeParenth(expression);
        }

        if (!expression.contains("+") &&
                !expression.contains("*") &&
                !expression.contains("/") &&
                !expression.contains("(") &&
                !expression.contains(")") &&
                // '-'는 첫 번째 위치에서만 허용
                (expression.indexOf("-") <= 0 || expression.lastIndexOf("-") == 0)) {
            return Double.parseDouble(expression);
        }

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
