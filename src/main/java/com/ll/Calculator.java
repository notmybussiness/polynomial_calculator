package com.ll;

class Calculator {
    public Calculator() {
    }

    public double plus(double num1, double num2){
        return num1 + num2;
    }

    public double minus(double num1, double num2){
        return num1 - num2;
    }

    public double multiply(double num1, double num2){
        return num1 * num2;
    }

    public double divide(double num1, double num2){
        return num1 / num2;
    }

    public double calculateExpression(String expression){
        if(expression.contains("+")){
            String[] query = expression.split("\\+");
            return plus(Double.parseDouble(query[0]), Double.parseDouble(query[1]));
        }
        else if(expression.contains("-")){
            String[] query = expression.split("-");
            return minus(Double.parseDouble(query[0]), Double.parseDouble(query[1]));
        }
        if(expression.contains("*")){
            String[] query = expression.split("*");
            return multiply(Double.parseDouble(query[0]), Double.parseDouble(query[1]));
//            return -0.54;
        }
        if(expression.contains("\\/")){
            String[] query = expression.split("/");
            return divide(Double.parseDouble(query[0]), Double.parseDouble(query[1]));
        }
        return 0;
    }
}