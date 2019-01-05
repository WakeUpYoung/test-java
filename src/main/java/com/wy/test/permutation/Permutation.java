package com.wy.test.permutation;

import java.util.ArrayList;
import java.util.List;

public class Permutation {

    public static void main(String[] args) {
//        char[] a = {'a','b','c'};
//        p(a,0);
    }


        public static void p(char[] array, int index){
            char temp;
            if(index==array.length){
                System.out.println(array);
                return;
            }
            if(array.length==0||index<0||index>array.length){
                return;
            }
            for(int j=index;j<array.length;j++){
                temp=array[j];
                array[j]=array[index];
                array[index]=temp;
                p(array, index+1);
                temp=array[j];
                array[j]=array[index];
                array[index]=temp;
            }

        }


}
