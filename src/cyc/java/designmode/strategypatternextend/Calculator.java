/*
 * @(#)Calculator.java, 2020年1月9日 上午10:18:34
 *
 * Copyright (c) 2018-2020, 四川蜀天梦图数据科技有限公司.
 * All rights reserved.
 */
package cyc.java.designmode.strategypatternextend;

/**
 * 策略枚举： 枚举类里写方法？ 好骚气啊，什么原理？
 *
 * @author chengyongchao
 * @version 1.0, 2020年1月9日
 */
public enum Calculator
{
    ADD("+")
    {
        public int execute(int a, int b)
        {
            return a + b;
        };
    },
    SUB("-")
    {
        public int execute(int a, int b)
        {
            return a - b;
        }
    };

    String value = "";

    private Calculator(String _value)
    {
        this.value = _value;
    }

    public String getValue()
    {
        return this.value;
    }

    public abstract int execute(int a, int b);

}
