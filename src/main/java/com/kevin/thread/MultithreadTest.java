package com.kevin.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

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

    @Test
    public void testThread()
    {
        new Thread(() ->
        {
            while (true)
            {
                System.out.println("我是" + Thread.currentThread().getName() + ",我飘过了");
                try
                {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }, "thread1").start();

        new Thread(() ->
        {
            Thread.currentThread().setName("Thread2");
            while (true)
            {
                System.out.println("我是" + Thread.currentThread().getName() + ",我飘过了");
                try
                {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Test
    public void testThreadPool()
    {
        ExecutorService service = Executors.newFixedThreadPool(2);
        service.submit(() ->
        {
            // do Something
        });

        // 周期线程池
        ScheduledExecutorService service2 = Executors.newScheduledThreadPool(2);
        ScheduledFuture<?> future = service2.scheduleAtFixedRate(() ->
        {
            // do something regularly
        }, 0, 5, TimeUnit.SECONDS);// 0代表延迟0s执行，5代表每5s执行一次
        if (true)// The trigger flag you want
        {
            future.cancel(true);
            service.shutdown();
        }
    }
}
