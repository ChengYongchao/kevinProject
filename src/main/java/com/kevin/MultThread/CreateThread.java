/*
 * @(#)CreateThread.java, 2020年7月8日 下午2:37:14
 *
 * Copyright (c) 2018-2020, 四川蜀天梦图数据科技有限公司.
 * All rights reserved.
 */
package com.kevin.MultThread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import org.junit.Test;

/**
 * 在这里加入功能说明
 *
 * @author chengyongchao
 * @version 1.0, 2020年7月8日
 */
public class CreateThread
{
    /**
     * 创建一个Runable传入Thread构造函数创建线程
     */
    @Test
    public void testCreateThreadByRunable()
    {
        Thread thread = new Thread(() ->
        {
            System.out.println(" run...");
        });
        thread.setName("create Thread by Runable");
        thread.start();

        // 实现接口Runable创建类，再通过Thread实现线程
        // 实际上也是传了Runable到构造函数中来创建线程
        Thread thread2 = new Thread(new Thread2());
        thread2.setName("create Thread by implements Runable");
        thread2.start();
    }

    /**
     * 继承Thread重写run方法覆盖掉run方法内的 if (target != null) { target.run(); }判断
     */
    @Test
    public void testCreateThreadByOverWriteFuncRun()
    {
        Thread1 thread = new Thread1();
        thread.setName("create Thread by overwrite Function run ");
        thread.start();
    }

    /**
     * 使用Callable创建线程,FutureTask实现了Runable接口，所以本质上也是通过runable创建线程
     */
    @Test
    public void testCreateThreadByCallable()
    {
        FutureTask<String> future = new FutureTask<>(() ->
        {
            return "create Thread by Callable";
        });

        Thread thread = new Thread(future);
        thread.start();
        String result = null;

        try
        {
            result = future.get();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        catch (ExecutionException e)
        {
            e.printStackTrace();
        }
        System.out.println(result);
    }

    /**
     * 通过线程池创建线程 在这里加入功能说明
     *
     * @author chengyongchao
     * @version 1.0, 2020年7月8日
     */
    @Test
    public void testCreateThreadByExecutorService()
    {
        ExecutorService service = Executors.newFixedThreadPool(2);
        service.execute(()->{
            System.err.println(Thread.currentThread().getName() + " create Thread by ExecutorService");
        });
        
    }

    public class Thread1 extends Thread
    {
        public void run()
        {
            System.out.println(this.getName() + " run...");
        }
    }

    public class Thread2 implements Runnable
    {

        @Override
        public void run()
        {
            System.out.println("run...");
        }

    }
}
