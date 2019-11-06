package cyc.java.thread.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NewCacheThreadExecutorTest
{

    public static void main(String[] args)
    {
        ExecutorService cachedThreadExecutor = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++)
        {
            final int index = i;
            try
            {
                Thread.sleep(1000);
            }
            catch (InterruptedException e1)
            {
                e1.printStackTrace();
            }
            cachedThreadExecutor.execute(new Runnable()
            {
                @Override
                public void run()
                {
                    System.out.println(Thread.currentThread().getName() + ": index = " + index);
                }
            });
        }
        cachedThreadExecutor.shutdown();

    }

}
