package com.wy.test.lambda;

import java.util.function.Function;

public class DoLambda {
    private int a;
    private int b;

    public DoLambda(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public int compute(Lambda lambda){
        return lambda.doSomething(a, b);
    }

    public Person testFunction(Person person, Function<Person, Person> function){
        return function.apply(person);
    }


    public static void old() {
        DoLambda doLambda = new DoLambda(1, 2);
        doLambda.compute(new Lambda() {
            @Override
            public int doSomething(int a, int b) {
                return a + b;
            }
        });
    }

    public static void newFun() {
        DoLambda doLambda = new DoLambda(1, 2);
        doLambda.compute((a, b) -> 1 + 2);
    }

    public static void main(String[] args) {
        old();
    }
}
