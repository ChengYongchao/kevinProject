package cyc.java.designmode.DynamicProxyModel;

import java.lang.reflect.InvocationHandler;

public class SubjectDynamicProxy<T> extends DynamicProxy<T>
{
    public static <T> T newProxyInstance(Subject subject)
    {
        ClassLoader loader = subject.getClass().getClassLoader();
        Class<?>[] interfaces = subject.getClass().getInterfaces();
        InvocationHandler handler = new MyInvocationHandler(subject);
        return DynamicProxy.newProxyInstance(loader, interfaces, handler);
    }
}
