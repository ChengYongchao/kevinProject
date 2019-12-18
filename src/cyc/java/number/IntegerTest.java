/*
 * @(#)IntegerTest.java, 2019年12月5日 下午2:46:49
 *
 * Copyright (c) 2018-2019, 四川蜀天梦图数据科技有限公司.
 * All rights reserved.
 */
package cyc.java.number;

import org.junit.Test;

/**
 * 在这里加入功能说明
 *
 * @author chengyongchao
 * @version 1.0, 2019年12月5日
 */
public class IntegerTest
{
    @Test
    public void testMin()
    {
        int min = 0x80000000;
        int minusMax = 0xFFFFFFFF;
        int max = 0x7fffffff;
        System.out.println(min);
        System.out.println(minusMax);
        System.out.println(max);
    }

}
