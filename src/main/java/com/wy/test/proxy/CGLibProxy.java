package com.wy.test.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CGLibProxy implements MethodInterceptor {

    private Enhancer enhancer = new Enhancer();

    public Object getProxy(Class c){
        enhancer.setSuperclass(c);
        enhancer.setCallback(this);
        return enhancer.create();
    }
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        doBefore();
        methodProxy.invoke(o, objects);
        doAfterReturning();
        return null;
    }

    public void doBefore(){
        System.out.println("CGLib: do Before");
    }

    public void doAfterReturning(){
        System.out.println("CGLib: after returning");
    }
}
