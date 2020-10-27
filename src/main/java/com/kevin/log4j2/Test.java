
package com.kevin.log4j2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.async.AsyncLoggerContextSelector;

/**
 * 在这里加入功能说明
 *
 * @author chengyongchao
 * @version 1.0, 2020年8月31日
 */
public class Test
{
    private static Logger requestLog = LogManager.getLogger(Test.class);

    public static void main(String[] args)
    {
        requestLog.info("{}",AsyncLoggerContextSelector.isSelected());
    }
}
