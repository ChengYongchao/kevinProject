package com.kevin.dailyTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.log4j.PropertyConfigurator;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 在这里加入功能说明
 *
 * @author chengyongchao
 * @version 1.0, 2020年10月22日
 */
public class TestLog4j
{

    @Test
    public void testLog4j()
    {
        try
        {
            PropertyConfigurator.configure(new FileInputStream("D:\\log4j.xml"));
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        
         Logger logger = LoggerFactory.getLogger(TestLog4j.class);
         logger.info("123123");
    }
}
