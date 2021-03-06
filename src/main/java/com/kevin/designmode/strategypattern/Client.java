package com.kevin.designmode.strategypattern;

/**
 * 在这里加入功能说明
 *
 * @author chengyongchao
 * @version 1.0, 2020年1月9日
 */
public class Client
{
    public static void main(String[] args)
    {
        ConcreteStrategy1 str1 = new ConcreteStrategy1();
        ConcreteStrategy2 str2 = new ConcreteStrategy2();
        Context context1 = new Context(str1);
        context1.execute();
        Context context2 = new Context(str2);
        context2.execute();
    }
}
