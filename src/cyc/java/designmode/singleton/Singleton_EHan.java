/*
 * @(#)EHan.java, 2019年12月16日 下午3:22:58
 *
 * Copyright (c) 2018-2019, 四川蜀天梦图数据科技有限公司.
 * All rights reserved.
 */
package cyc.java.designmode.singleton;

/**
 * 饿汉模式
 *
 * @author chengyongchao
 * @version 1.0, 2019年12月16日
 */
public class Singleton_EHan
{
    private static final Singleton_EHan singleton = new Singleton_EHan();

    private Singleton_EHan()
    {}

    public static Singleton_EHan getInstance()
    {
        return singleton;
    }
}
