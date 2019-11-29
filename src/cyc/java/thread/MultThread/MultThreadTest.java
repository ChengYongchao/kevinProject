
package cyc.java.thread.MultThread;

import org.junit.Test;

/**
 * 在这里加入功能说明
 *
 * @author chengyongchao
 * @version 1.0, 2019年11月29日
 */
public class MultThreadTest
{
    @Test
    public void interruptTest()
    {
        Thread thread1 = new Thread(() ->
        {
            for (int i = 1;; i++)
            {
                if (Thread.currentThread().isInterrupted())
                {
                    System.out.println("我中断了");
                    //release resource
                    return;
                }
                System.out.println(i);
            }
        });
        thread1.start();
        System.out.println(Thread.currentThread().isInterrupted());
        try
        {
            Thread.sleep(100);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        thread1.interrupt();
    }
}
