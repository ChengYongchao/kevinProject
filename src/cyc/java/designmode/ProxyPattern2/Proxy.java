package cyc.java.designmode.ProxyPattern2;

public class Proxy implements Subject
{
    private Subject subject;

    public Proxy()
    {
        this.subject = new RealSubject();
    }

    public Proxy(Subject subject)
    {
        this.subject = subject;
    }

    @Override
    public void request()
    {
        this.before();
        this.subject.request();
        this.after();
    }

    public void before()
    {
        // 前置操作
    }

    public void after()
    {
        // 后置操作
    }

}
