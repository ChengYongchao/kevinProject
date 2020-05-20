package com.kevin.designmode.DecoratorPattern;

public abstract class Decorator extends Component
{
    private Component component = null;

    /**
     * 传递被装饰者
     */
    public Decorator(Component component)
    {
        this.component = component;
    }

    /*
     * 委托给被装饰者执行
     */
    @Override
    public void operate()
    {
        this.component.operate();
    }

}
