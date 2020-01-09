package cyc.java.designmode.DecoratorPattern;

public class ConcreteDecorator1 extends Decorator
{

    /**
     * @param component
     */
    public ConcreteDecorator1(Component component)
    {
        super(component);
    }

    private void method1()
    {
        System.out.println("method1 修饰");
    }

    /*
     * 重写父类的operate方法
     */
    @Override
    public void operate()
    {
        this.method1();
        super.operate();
    }
}
