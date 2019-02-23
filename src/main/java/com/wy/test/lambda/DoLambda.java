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
}
