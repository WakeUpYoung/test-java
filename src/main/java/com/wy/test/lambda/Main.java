package com.wy.test.lambda;

import java.util.ArrayList;
import java.util.List;

public class Main {
    // 使用lambda 就是让方法的参数是一个@FunctionInterface接口
    public static void main(String[] args) {
//        testStream();
        DoLambda lambda = new DoLambda(10, 5);
//        int add = lambda.compute((x, y) -> x - y);
        lambda.testFunction(new Person("Wang", 6000.0, 20), person -> {
            person.setAge(person.getAge() + 1);
            System.out.println(person);
            return person;
        });

    }

    public static void testStream(){
        List<Person> personList = new ArrayList<>();
        Person p1 = new Person("Wang", 6000.0, 23);
        Person p2 = new Person("Zhao", 8000.0, 32);
        Person p3 = new Person("Qian", 10000.0, 35);
        personList.add(p1);
        personList.add(p2);
        personList.add(p3);

//        personList.forEach(System.out::println);
//        personList.stream().filter(p -> p.getAge() > 30).forEach(System.out::println);

        personList.stream().sorted((person1, person2) -> person2.getAge() - person1.getAge()).forEach(p -> System.out.println(p.getName()));
    }

}
