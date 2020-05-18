/*
 * @(#)ThreadFactory.java, 2020年5月15日 上午10:43:38
 *
 * Copyright (c) 2018-2020, 四川蜀天梦图数据科技有限公司.
 * All rights reserved.
 */
package cyc.java.codeformula.lifecycle;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 在这里加入功能说明
 *
 * @author chengyongchao
 * @version 1.0, 2020年5月15日
 */
public class BaseThreadFactory implements ThreadFactory
{

    private ThreadGroup threadGroup;
    private String prefix;
    private String pattern;
    private AtomicLong threadCounter;

    public BaseThreadFactory(ThreadGroup threadGroup, String prefix, String pattern)
    {
        this.threadGroup = (threadGroup == null ? Thread.currentThread().getThreadGroup() : threadGroup);
        this.prefix = (prefix == null ? "thread-" : prefix);
        this.pattern = (pattern == null ? "-d%" : pattern);
        threadCounter = new AtomicLong(0);
    }

    @Override
    public Thread newThread(Runnable r)
    {
        return new Thread(threadGroup, r, String.format(prefix + pattern, Long.valueOf(threadCounter.incrementAndGet())));
    }
}
