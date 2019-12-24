package cyc.java.designmode.DynamicProxyModel;

public class RealSubject implements Subject
{

    @Override
    public void doSomething()
    {
        System.out.println("执行业务逻辑");
    }

}
