
package com.kevin.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * 参数： -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails
 * -XX:SurvivorRatio=8
 * 
 * @author chengyongchao
 * @version 1.0, 2020年9月10日
 */
public class HeapOOM
{
    static class OOMObject
    {}

    public static void main(String[] args)
    {
        List<OOMObject> list = new ArrayList<HeapOOM.OOMObject>();
        while (true)
        {
            list.add(new OOMObject());
        }
    }
}
