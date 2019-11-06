/*
 * @(#)Filter.java, 2019年11月1日 上午10:38:01
 *
 * Copyright (c) 2018-2019, 四川蜀天梦图数据科技有限公司.
 * All rights reserved.
 */
package cyc.java.wallpaper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * 在这里加入功能说明
 *
 * @author chengyongchao
 * @version 1.0, 2019年11月1日
 */
public abstract class Filter<T>
{
    private static Logger logger = Logger.getLogger("Filter_kevincyc");

    private File file;

    Filter(String address)
    {
        file = new File(address);
        if (!file.exists())
        {
            logger.warning(address + "文件不存在！");
            System.exit(0);
        }
    }

    public String readFileAll()
    {
        List<String> data = readFileByLine();
        StringBuffer res = new StringBuffer();
        for (String d : data)
        {
            res.append(d);
        }
        return res.toString();
    }

    public List<String> readFileByLine()
    {
        List<String> res = new ArrayList<>();
        BufferedReader bufferedReader = null;
        try
        {
            bufferedReader = new BufferedReader(new FileReader(file));
            String strLine = null;
            int lineCount = 1;

            while (null != (strLine = bufferedReader.readLine()))
            {
                logger.info("第[" + lineCount + "]行数据:[" + strLine + "]");
                res.add(strLine);
                lineCount++;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (bufferedReader != null)
            {
                try
                {
                    bufferedReader.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }

        }
        return res;
    }

    public abstract T filter();

}
