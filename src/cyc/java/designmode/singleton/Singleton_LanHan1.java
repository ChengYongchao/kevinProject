/*
 * @(#)Singleton_LanHan1.java, 2019年12月16日 下午3:28:47
 *
 * Copyright (c) 2018-2019, 四川蜀天梦图数据科技有限公司.
 * All rights reserved.
 */
package cyc.java.designmode.singleton;

/**
 * 线程不安全的懒汉模式
 *
 * @author chengyongchao
 * @version 1.0, 2019年12月16日
 */
public class Singleton_LanHan1
{
    private static Singleton_LanHan1 singleton = null;

    private Singleton_LanHan1()
    {}

    public static Singleton_LanHan1 getInstance()
    {
        if (singleton == null)
        {
            singleton = new Singleton_LanHan1();
        }
        return singleton;
    }
}