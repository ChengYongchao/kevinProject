/*
 * @(#)SubGenericClass.java, 2020年6月3日 下午2:42:54
 *
 * Copyright (c) 2018-2020, 四川蜀天梦图数据科技有限公司.
 * All rights reserved.
 */
package com.kevin.genericity;

/**
 * 在这里加入功能说明
 *
 * @author chengyongchao
 * @version 1.0, 2020年6月3日
 */
public class SubGenericClass extends GenericClass<String>
{
    @Override
    public String add(String t)
    {
        return t;
    }

    public <U> void get(GenericClass<U> t, int i)
    {
        t.get();
    }

    public void get(GenericClass<?> t)
    {
        t.get();
    }
}
