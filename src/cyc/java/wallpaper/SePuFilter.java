/*
 * @(#)SePuFilter.java, 2019年11月1日 上午11:06:10
 *
 * Copyright (c) 2018-2019, 四川蜀天梦图数据科技有限公司.
 * All rights reserved.
 */
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
