
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
    @SuppressWarnings ("unchecked")
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
