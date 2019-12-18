/*
 * @(#)Singleton_LanHan3.java, 2019年12月16日 下午3:39:07
 *
 * Copyright (c) 2018-2019, 四川蜀天梦图数据科技有限公司.
 * All rights reserved.
 */
package cyc.java.designmode.singleton;

/**
 * 在这里加入功能说明
 *
 * @author chengyongchao
 * @version 1.0, 2019年12月16日
 */
public class Singleton_LanHan3
{
    private static Singleton_LanHan3 singleton;

    private Singleton_LanHan3()
    {}

    public static Singleton_LanHan3 getInstance()
    {
        if (singleton == null)
        {
            synchronized (Singleton_LanHan3.class)
            {
                if (singleton == null)
                {
                    singleton = new Singleton_LanHan3();
                }
            }
        }
        return singleton;
    }
}
