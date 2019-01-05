package com.wy.test.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKProxy implements InvocationHandler {

    private Object targetObj;

    public Object newProxy(Object targetObj){
        this.targetObj = targetObj;
        // 返回代理对象
        return Proxy.newProxyInstance(targetObj.getClass().getClassLoader(),targetObj.getClass().getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        doBefore();
        Object ret = null;
        ret = method.invoke(targetObj, args);
        doAfterReturning();
        return ret;
    }

    private void doBefore(){
        System.out.println("JDK do Before");
    }

    private void doAfterReturning(){
        System.out.println("JDK do after");
    }
}
