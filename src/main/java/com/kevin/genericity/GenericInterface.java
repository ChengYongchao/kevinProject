/*
 * @(#)GenericityInterface.java, 2020年6月3日 上午10:34:48
 *
 * Copyright (c) 2018-2020, 四川蜀天梦图数据科技有限公司.
 * All rights reserved.
 */
package com.kevin.genericity;

/**
 * 泛型接口
 *
 * @author chengyongchao
 * @version 1.0, 2020年6月3日
 */
public interface GenericInterface<T>
{
    public T get(T t);

    /**
     * 泛型方法
     */
    public <V> V add(V v);
}
