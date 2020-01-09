/*
 * @(#)ConcreteStrategy1.java, 2020年1月9日 上午10:08:45
 *
 * Copyright (c) 2018-2020, 四川蜀天梦图数据科技有限公司.
 * All rights reserved.
 */
package cyc.java.designmode.strategypattern;

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
