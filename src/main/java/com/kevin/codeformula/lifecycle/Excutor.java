
package com.kevin.codeformula.lifecycle;

import java.lang.ref.WeakReference;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * 当面对这样一个需求，需要对一个流程进行分阶段处理：before、execute、afterSuccess、afterfailure
 * etc,你可以使用该公式 公式用到的设计模式：建造者模式
 *
 * @author chengyongchao
 * @version 1.0, 2020年5月15日
 */
public class Excutor<T>
{
    private final ScheduledExecutorService scheduledExecutorService;

    private final ExecutorService executorService;

    private final Consumer<T> beforeExecute;

    private final Consumer<T> afterSuccess;

    private final Consumer<T> afterTimeout;

    private final BiConsumer<T, Throwable> afterFailure;

    private Excutor(final Builder<T> builder)
    {
        this.scheduledExecutorService = builder.scheduledExecutorService;
        this.executorService = builder.executorService;
        this.beforeExecute = builder.beforeExecute;
        this.afterSuccess = builder.afterSuccess;
        this.afterTimeout = builder.afterTimeout;
        this.afterFailure = builder.afterFailure;
    }

    public CompletableFuture<Object> execute(T o)
    {
        final CompletableFuture<Object> evaluationFuture = new CompletableFuture<>();
        final FutureTask<Void> evalFuture = new FutureTask<>(() ->
        {
            try
            {
                // 前置处理
                beforeExecute.accept(o);
                // 运行
                final Object result = null;
                // 成功执行后，后置处理，自定义
                afterSuccess.accept(o);

                evaluationFuture.complete(result);
            }
            catch (Exception e)
            {
                final Throwable root = e.getCause();
                afterFailure.accept(o, root);
            }
            return null;
        });

        final WeakReference<CompletableFuture<Object>> evaluationFutureRef = new WeakReference<>(evaluationFuture);
        final Future<?> executionFuture = executorService.submit(evalFuture);
        

        return null;
    }

    public static class Builder<T>
    {
        private ExecutorService executorService = null;

        private ScheduledExecutorService scheduledExecutorService = null;

        private Consumer<T> beforeExecute = (b) ->
        {};

        private Consumer<T> afterSuccess = (b) ->
        {};

        private Consumer<T> afterTimeout = (b) ->
        {};

        private BiConsumer<T, Throwable> afterFailure = (b, e) ->
        {};

        private Builder()
        {}

        public Builder<T> beforeExecute(final Consumer<T> beforeExecute)
        {
            this.beforeExecute = beforeExecute;
            return this;
        }

        public Builder<T> afterSuccess(final Consumer<T> afterSuccess)
        {
            this.afterSuccess = afterSuccess;
            return this;
        }

        public Builder<T> afterTimeout(final Consumer<T> afterTimeout)
        {
            this.afterTimeout = afterTimeout;
            return this;
        }

        public Builder<T> afterFailure(final BiConsumer<T, Throwable> afterFailure)
        {
            this.afterFailure = afterFailure;
            return this;
        }

        public Excutor<T> create()
        {
            final BaseThreadFactory threadFactory = new BaseThreadFactory(null, "thread-", null);

            final AtomicBoolean poolCreatedByBuilder = new AtomicBoolean();
            final AtomicBoolean suppliedExecutor = new AtomicBoolean(true);
            final AtomicBoolean suppliedScheduledExecutor = new AtomicBoolean(true);

            final ExecutorService es = Optional.ofNullable(executorService).orElseGet(() ->
            {
                poolCreatedByBuilder.set(true);
                suppliedExecutor.set(false);
                return Executors.newScheduledThreadPool(4, threadFactory);
            });
            executorService = es;

            final ScheduledExecutorService ses = Optional.ofNullable(scheduledExecutorService).orElseGet(() ->
            {
                // if the pool is created by the builder and we need another
                // just re-use it, otherwise create
                // a new one of those guys
                suppliedScheduledExecutor.set(false);
                return (poolCreatedByBuilder.get()) ? (ScheduledExecutorService)es
                        : Executors.newScheduledThreadPool(4, threadFactory);
            });
            scheduledExecutorService = ses;

            return new Excutor<T>(this);
        }
    }

}
