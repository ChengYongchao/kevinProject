
package cyc.java.wallpaper;

/**
 * 在这里加入功能说明
 *
 * @author chengyongchao
 * @version 1.0, 2019年11月1日
 * @param <T>
 */
public class SePuFilter<T> extends Filter<T>
{

    /**
     * @param address
     */
    SePuFilter(String address)
    {
        super(address);
    }

    @Override
    public T filter()
    {
        String data = readFileAll();
        System.out.println(data);

        return null;
    }

    public static void main(String[] args)
    {
        SePuFilter<String> sp = new SePuFilter<>("C:\\Users\\Administrator\\Desktop\\sepu.txt");
        sp.filter();
    }
}
