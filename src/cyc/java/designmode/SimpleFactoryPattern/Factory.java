/*
 * @(#)AbstractFactory.java, 2019年12月17日 上午9:51:29
 *
 * Copyright (c) 2018-2019, 四川蜀天梦图数据科技有限公司.
 * All rights reserved.
 */
package cyc.java.designmode.SimpleFactoryPattern;

import cyc.java.designmode.FactoryMethodPattern.Product;

/**
 * 在这里加入功能说明
 *
 * @author chengyongchao
 * @version 1.0, 2019年12月17日
 */
public class Factory
{
    public static <T extends Product> T createProduct(Class<T> c)
    {
        Product product = null;
        try
        {
            product = (Product)Class.forName(c.getName()).newInstance();
        }
        catch (Exception e)
        {
            System.err.println("It`s not Product");
        }
        return (T)product;
    }
}
