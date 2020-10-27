
package com.kevin.jvm;

/**
 * VM Args: -Xss128k
 *
 * @author chengyongchao
 * @version 1.0, 2020年9月10日
 */
public class JavaVMStackSOF
{
    private int stackLength = 1;

    public void stackLeak()
    {
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args)
    {
        JavaVMStackSOF sof = new JavaVMStackSOF();
        try
        {
            sof.stackLeak();
        }
        catch (Throwable e)
        {
            System.out.println("stack length: " + sof.stackLength);
            throw e;
        }
    }
}
