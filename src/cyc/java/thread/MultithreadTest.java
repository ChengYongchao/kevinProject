package cyc.java.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class MultithreadTest
{
    public static void main(String[] args)
    {
        // 创建线程的两种方式
        SubThread st1 = new SubThread();
        st1.setName("thread Thread1");
        st1.start();

        Runnable sr1 = new RunnableTest();
        Thread thread1 = new Thread(sr1, "thread runnable1");
        thread1.start();

        Callable<String> cb1 = new CallableTest();
        FutureTask<String> ft1 = new FutureTask<>(cb1);
        Thread th2 = new Thread(ft1);
        th2.setName("callable1");
        th2.start();
        try
        {
            Thread.sleep(1000);
        }
        catch (InterruptedException e1)
        {
            e1.printStackTrace();
        }

        try
        {
            System.out.println(ft1.get());
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        catch (ExecutionException e)
        {
            e.printStackTrace();
        }

        try
        {
            Thread.sleep(1000);
            System.out.println("main end");
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        // 线程同步
        Good good = new Good();

        ExecutorService es = Executors.newFixedThreadPool(2);
        es.execute(() ->
        {
            while (good.getGoodsNum() < 100)
            {
                try
                {
                    Thread.sleep(10);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                System.out.println("线程1获取商品数量：" + good.getGoodsNum());
                good.setGoodsNum(5);
                System.out.println("线程1添加商品数量后还有：" + good.getGoodsNum());
            }
            System.out.println("商品数量超过 100");
        });
        es.execute(() ->
        {
            try
            {
                Thread.sleep(10);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            System.out.println("线程2获取商品数量：" + good.getGoodsNum());
            while (good.getGoodsNum() > 0)
            {
                try
                {
                    Thread.sleep(10);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }

                good.reduceGoodsNum(1);
                System.out.println("线程2减少商品数量后还剩： " + good.getGoodsNum());
            }
            System.out.println("商品数量为0");
        });
        Future<Integer> fu = es.submit(() ->
        {
            Thread.sleep(100);
            return good.getGoodsNum();
        });
        try
        {
            System.out.println("where am I：" + fu.get());
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        catch (ExecutionException e)
        {
            e.printStackTrace();
        }
    }

    public static class SubThread extends Thread
    {
        @Override
        public void run()
        {
            System.out.println("Thread" + Thread.currentThread().getName());
        }
    }

    public static class RunnableTest implements Runnable
    {
        @Override
        public void run()
        {
            System.out.println("Runable" + Thread.currentThread().getName());
        }
    }

    public static class CallableTest implements Callable<String>
    {

        @Override
        public String call() throws Exception
        {
            return "Callable " + Thread.currentThread().getName();
        }

    }

    public static class Good
    {
        private int goodsNum = 0;

        public synchronized int getGoodsNum()
        {
            return goodsNum;
        }

        public synchronized void setGoodsNum(int goodsNum)
        {
            this.goodsNum += goodsNum;
        }

        public synchronized boolean reduceGoodsNum(int goodsNum)
        {
            if (this.goodsNum > 0)
            {
                this.goodsNum -= goodsNum;
                return true;

            }
            return false;

        }

    }
}
