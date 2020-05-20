package com.kevin.designmode.ProxyPattern_DynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class GamePlayIH implements InvocationHandler
{

    // 被代理者
    Class<?> cls = null;

    Object obj = null;

    public GamePlayIH(Object obj)
    {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
    {
        Object result = method.invoke(this.obj, args);
        if (method.getName().equals("login"))
        {
            System.out.println("一个臭弟弟调用了登陆方法");
        }
        return result;
    }

}
