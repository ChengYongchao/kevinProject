
package com.kevin.mengtu.txttocsv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class FileLoader
{
    private static Logger logger = Logger.getLogger("FileLoader-txtToCsv");

    private static String rootDir;

    public FileLoader()
    {
        this.rootDir = System.getProperty("user.dir");
    }

    public static List<String> readFileByLine(String strFile)
    {
        List<String> res = new ArrayList<>();
        BufferedReader bufferedReader = null;
        try
        {
            File file = new File(strFile);
            if (!file.exists())
            {
                logger.warning(strFile + "文件不存在！");
                System.exit(0);
            }
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

}
