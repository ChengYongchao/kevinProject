
package com.kevin.designmode.SimpleFactoryPattern;

import com.kevin.designmode.FactoryMethodPattern.Product;
import com.kevin.designmode.FactoryMethodPattern.product1;

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
        Product product = Factory.createProduct(product1.class);
        product.method2();
    }
}
