
package com.kevin.designmode.BuilderPattern;

import com.kevin.designmode.BuilderPattern.Person.Builder;

/**
 * 在这里加入功能说明
 *
 * @author chengyongchao
 * @version 1.0, 2019年12月18日
 */
public class Client
{
    public static void main(String[] args)
    {
        Person xiaoming = new Builder()
                .eyes("大眼睛")
                .nose("小鼻子")
                .mouth("大嘴巴")
                .ears("小耳朵")
                .hair("红头发")
                .build();
        System.out.println(xiaoming.toString());
    }
}
