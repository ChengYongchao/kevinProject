package com.kevin.designmode.strategypattern;

/**
 * 在这里加入功能说明
 *
 * @author chengyongchao
 * @version 1.0, 2020年1月9日
 */
public class ConcreteStrategy1 implements Strategy
{

    @Override
    public void execute()
    {
        System.out.println("执行策略1");
    }

}
