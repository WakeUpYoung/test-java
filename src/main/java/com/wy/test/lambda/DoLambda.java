/**
 * Classname :DoLambda
 * <p>
 * Description :
 * <p>
 * Author : Wang Yu
 **/
package com.wy.test.lambda;

public class DoLambda {
    public void add(int a, int b, Lambda lambda){
        int result = lambda.doSomething(a, b);
        System.out.println(result);
    }
}
