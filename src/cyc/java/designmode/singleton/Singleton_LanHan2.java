
package cyc.java.designmode.singleton;

/**
 * 在这里加入功能说明
 *
 * @author chengyongchao
 * @version 1.0, 2019年12月16日
 */
public class Singleton_LanHan2
{
    private static Singleton_LanHan2 singleton = null;

    private Singleton_LanHan2()
    {}

    public static synchronized Singleton_LanHan2 getInstance()
    {
        if (singleton == null)
        {
            singleton = new Singleton_LanHan2();
        }
        return singleton;
    }
}
