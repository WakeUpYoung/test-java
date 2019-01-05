package com.wy.test.collectionTest;

import java.util.ArrayList;
import java.util.List;

public class ArrayListTest {
    public static void main(String[] args) {
        List<Dog> dogs = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            Dog dog = new Dog("name", 6);
            dogs.add(dog);
//            dog = null;
            dog.setName("new name");
        }
        for (Dog dog : dogs){
            System.out.println(dog.toString());
        }
    }
}
