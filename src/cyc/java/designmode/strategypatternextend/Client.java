/*

 * @(#)Client.java, 2020年1月9日 上午10:26:34
 *
 * Copyright (c) 2018-2020, 四川蜀天梦图数据科技有限公司.
 * All rights reserved.
 */
package cyc.java.designmode.strategypatternextend;

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
        int res1 = Calculator.ADD.execute(1, 2);
        int res2 = Calculator.SUB.execute(1, 2);
        System.out.println(res1 + " " + res2);
    }
}
