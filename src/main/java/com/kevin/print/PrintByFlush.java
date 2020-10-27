/*
 * @(#)PrintByFlush.java, 2020年9月28日 下午4:35:46
 *
 * Copyright (c) 2018-2020, 四川蜀天梦图数据科技有限公司.
 * All rights reserved.
 */
package com.kevin.print;

/**
 * 在这里加入功能说明
 *
 * @author chengyongchao
 * @version 1.0, 2020年9月28日
 */
public class PrintByFlush
{
    public static void main(String[] args)
    {
        long i = 0;
        for (i = 0; i < 100000; i++)
        {
            System.out.print(String.format("%d%s",i, backward(i)));
        }
        System.out.println(i);
    }

    private static String backward(long word)
    {
        StringBuilder backward = new StringBuilder();
        for (int i = 0, len = String.valueOf(word).length(); i < len; i++)
        {
            backward.append("\b");
        }
        return backward.toString();
    }
}
