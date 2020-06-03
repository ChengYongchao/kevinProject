
package com.kevin.dailyTest;

import org.junit.Test;

/**
 * 在这里加入功能说明
 *
 * @author chengyongchao
 * @version 1.0, 2020年5月27日
 */
public class DailyTest
{
    @Test
    public void testDouble()
    {
        Double v = 0.0d;
        change(v);
        System.out.println(v);
        // 执行change后能改变v的值

    }

    @Test
    public void testMyDouble()
    {
        MyDoulbe v = new MyDoulbe(0.0d);
        changeMyDouble(v);
        System.out.println(v.v);

    }

    public void changeMyDouble(MyDoulbe v)
    {
        v = new MyDoulbe(2.0d);
    }

    class MyDoulbe
    {
        double v;

        /**
         * 
         */
        MyDoulbe(double v)
        {
            this.v = v;
        }
    }
    
    

    public void change(Double v)
    {
        v = new Double(2.0d);
    }

}
