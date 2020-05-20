package com.kevin.designmode.strategypattern;

/**
 * 在这里加入功能说明
 *
 * @author chengyongchao
 * @version 1.0, 2020年1月9日
 */
public class Context
{
    private Strategy strategy;

    /**
     * 
     */
    public Context(Strategy strategy)
    {
        this.strategy = strategy;
    }

    public void execute()
    {
        this.strategy.execute();
    }
}
