package cyc.java.codeformula.lifecycle;

import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

import javax.script.Bindings;

public class LifeCycle<T>
{
    private final Optional<Consumer<T>> beforeExecute;

    private final Optional<Function<Object, Object>> transformResult;

    private final Optional<Consumer<Object>> withResult;

    private final Optional<Consumer<T>> afterSuccess;

    private final Optional<Consumer<T>> afterTimeout;

    private final Optional<BiConsumer<T, Throwable>> afterFailure;

    private LifeCycle(final Builder<T> builder)
    {
        beforeExecute = Optional.ofNullable(builder.beforeExecute);
        transformResult = Optional.ofNullable(builder.transformResult);
        withResult = Optional.ofNullable(builder.withResult);
        afterSuccess = Optional.ofNullable(builder.afterSuccess);
        afterTimeout = Optional.ofNullable(builder.afterTimeout);
        afterFailure = Optional.ofNullable(builder.afterFailure);

    }

    public Optional<Consumer<T>> getBeforeExecute()
    {
        return beforeExecute;
    }

    public Optional<Function<Object, Object>> getTransformResult()
    {
        return transformResult;
    }

    public Optional<Consumer<Object>> getWithResult()
    {
        return withResult;
    }

    public Optional<Consumer<T>> getAfterSuccess()
    {
        return afterSuccess;
    }

    public Optional<Consumer<T>> getAfterTimeout()
    {
        return afterTimeout;
    }

    public Optional<BiConsumer<T, Throwable>> getAfterFailure()
    {
        return afterFailure;
    }

    public static Builder<Bindings> build()
    {
        return new Builder<Bindings>();
    }

    public static class Builder<T>
    {
        private Consumer<T> beforeExecute = null;

        private Function<Object, Object> transformResult = null;

        private Consumer<Object> withResult = null;

        private Consumer<T> afterSuccess = null;

        private Consumer<T> afterTimeout = null;

        private BiConsumer<T, Throwable> afterFailure = null;

        public Builder<T> beforeExecute(final Consumer<T> beforeExecute)
        {
            this.beforeExecute = beforeExecute;
            return this;
        }

        public Builder<T> transformResult(final Function<Object, Object> transformResult)
        {
            this.transformResult = transformResult;
            return this;
        }

        public Builder<T> withResult(final Consumer<Object> withResult)
        {
            this.withResult = withResult;
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

        public LifeCycle<T> create()
        {
            return new LifeCycle<T>(this);
        }
    }
}
