package cyc.java.designmode.DynamicProxyModel;

import java.lang.reflect.InvocationHandler;

import org.junit.Test;

public class Client
{
    @Test
    public void test1()
    {
        Subject subject = new RealSubject();
        InvocationHandler handler = new MyInvocationHandler(subject);
        Subject proxy = DynamicProxy.newProxyInstance(subject.getClass().getClassLoader(),
                subject.getClass().getInterfaces(), handler);
        proxy.doSomething();
    }

    @Test
    public void test2()
    {
        Subject subject = new RealSubject();
        Subject proxy = SubjectDynamicProxy.newProxyInstance(subject);
        proxy.doSomething();
    }
}
