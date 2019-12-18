/*
 * @(#)Client.java, 2019年12月17日 上午9:57:31
 *
 * Copyright (c) 2018-2019, 四川蜀天梦图数据科技有限公司.
 * All rights reserved.
 */
package cyc.java.designmode.FactoryMethodPattern;

/**
 * 在这里加入功能说明
 *
 * @author chengyongchao
 * @version 1.0, 2019年12月17日
 */
public class Client
{
    public static void main(String[] args)
    {
        Factory factory = new Factory();
        Product product = factory.createProduct(product1.class);
        product.method2();
    }
}
