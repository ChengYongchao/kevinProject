package com.kevin.MultThread;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;


public class CompletionServiceTest {
    protected CompletionService<Boolean> completionService;
    private ExecutorService workerPool;
    private int workers = 4;
    private Logger logger = LoggerFactory.getLogger(CompletionServiceTest.class);
    public CompletionServiceTest()
    {
        this.workerPool = Executors.newFixedThreadPool(workers);
        this.completionService = new ExecutorCompletionService<>(workerPool);
    }

    public void start()
    {
        for (int i = 0; i < workers; i++)
        {
            this.completionService.submit(()->{
                logger.info("do");
                return true;
            });
        }
    }
    public void isDone() throws InterruptedException, ExecutionException {
        for(int i = 0; i < workers; i++)
        {
            this.completionService.take().get();
        }
    }

    @Test
    public void test1() throws ExecutionException, InterruptedException {
        CompletionServiceTest test = new CompletionServiceTest();
        test.start();
        test.isDone();
        test.start();
        test.isDone();
    }
}
