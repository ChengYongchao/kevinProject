/*
 * @(#)Client.java, 2019年12月18日 上午9:52:57
 *
 * Copyright (c) 2018-2019, 四川蜀天梦图数据科技有限公司.
 * All rights reserved.
 */
package cyc.java.designmode.BuilderPattern;

import cyc.java.designmode.BuilderPattern.Person.Builder;

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
