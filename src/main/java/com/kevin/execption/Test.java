
package com.kevin.execption;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 在这里加入功能说明
 *
 * @author chengyongchao
 * @version 1.0, 2020年4月20日
 */
public class Test
{

    public static void main(String[] args) throws IOException
    {
        System.out.println("method:" + method());
    }

    public static int method()
    {
        int a = 1;
        int[] b = {};
        try
        {
            b[2] = 0;
            return ++a;
        }
        catch (NullPointerException e)
        {
            // deal exception
            System.out.println("NullPointerException" + a);
        }
        catch (RuntimeException e2)
        {
            // deal exception
            System.out.println("RuntimeException" + a);
        }
        finally
        {
            // release resource
            System.out.println("finally" + a);
        }
        return a;
    }

    public void method2()
    {
        try (
            BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(""))
        )
        {

        }
        catch (IOException e1)
        {
            e1.printStackTrace();
        }

    }
}
