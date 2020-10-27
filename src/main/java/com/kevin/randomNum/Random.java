
package com.kevin.randomNum;

/**
 * 在这里加入功能说明
 *
 * @author chengyongchao
 * @version 1.0, 2020年4月2日
 */
public class Random
{
    private static final int NUM = 685230;

    public static void main(String[] args)
    {

        for (int i = 0; i < 5; i++)
        {
            System.out
                    .println((int)(1 + Math.random() * (NUM - 1 + 1)) + " " + (int)(1 + Math.random() * (NUM - 1 + 1)));
        }
    }
}
