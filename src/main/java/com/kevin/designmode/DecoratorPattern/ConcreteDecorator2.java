package com.kevin.designmode.DecoratorPattern;

public class ConcreteDecorator2 extends Decorator
{

    /**
     * @param component
     */
    public ConcreteDecorator2(Component component)
    {
        super(component);
    }

    private void method2()
    {
        System.out.println("method2 执行");
    }

    @Override
    public void operate()
    {
        super.operate();
        this.method2();
    }
}
