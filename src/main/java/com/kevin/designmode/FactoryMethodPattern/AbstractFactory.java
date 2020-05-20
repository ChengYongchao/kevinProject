
package com.kevin.designmode.FactoryMethodPattern;

/**
 * 在这里加入功能说明
 *
 * @author chengyongchao
 * @version 1.0, 2019年12月17日
 */
public abstract class AbstractFactory
{
    public abstract <T extends Product> T createProduct(Class<T> c);
}
