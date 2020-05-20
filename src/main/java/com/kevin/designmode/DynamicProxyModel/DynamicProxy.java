package com.kevin.designmode.DynamicProxyModel;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class DynamicProxy<T>
{
    @SuppressWarnings ("unchecked")
    public static <T> T newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler handler)
    {
        // 寻找JoinPoint连接点，AOP框架使用元数据定义
        if (true)
        {
            new BeforeAdvice().exec();
        }
        return (T)Proxy.newProxyInstance(loader, interfaces, handler);
    }
}
