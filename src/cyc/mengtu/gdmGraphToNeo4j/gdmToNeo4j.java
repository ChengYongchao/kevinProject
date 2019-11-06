
package cyc.mengtu.gdmGraphToNeo4j;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * 在这里加入功能说明
 *
 * @author chengyongchao
 * @version 1.0, 2019年10月14日
 */
public class gdmToNeo4j
{

    private static Logger logger = Logger.getLogger("gdmTONeo4j");

    private List<Vertex> verteics;

    private List<Edge> edges;

    private static String VFile = "";

    private static String rootDir = "";

    private static String EFile = "";

    private static String ResultFile = "";

    gdmToNeo4j()
    {
        rootDir = System.getProperty("user.dir");
        VFile = rootDir + "\\Vertex.txt";
        EFile = rootDir + "\\\\Edge.txt";
        ResultFile = rootDir + "\\\\Result.txt";
    }

    public void start()
    {
        init();
        writeFileByLine(verteics, edges);
    }

    public void init()
    {
        verteics = loadVerteics(readFileByLine(VFile));
        edges = loadEdges(readFileByLine(EFile));
    }

    public List<Vertex> loadVerteics(List<String> verteics)
    {

        List<Vertex> res = new ArrayList<Vertex>();
        verteics.stream().map((v) ->
        {
            return new Vertex(v);
        }).collect(Collectors.toCollection(() -> res));
        return res;
    }

    private List<Edge> loadEdges(List<String> edges)
    {
        List<Edge> res = new ArrayList<Edge>();
        edges.stream().map((v) ->
        {
            return new Edge(v);
        }).collect(Collectors.toCollection(() -> res));
        return res;
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

    public void writeFileByLine(List<Vertex> verteics, List<Edge> edges)
    {

        BufferedWriter bw = null;
        try
        {
            File file = new File(ResultFile);
            if (!file.exists())
            {
                file.createNewFile();
            }
            bw = new BufferedWriter(new FileWriter(file));
            bw.write("CREATE ");
            for (int index = 0; index < verteics.size(); index++)
            {
                try
                {
                    bw.write(verteics.get(index).neo4jV);
                    if (index != verteics.size() - 1)
                    {
                        bw.write(",");
                    }
                    bw.newLine();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            bw.write("CREATE ");
            for (int index = 0; index < edges.size(); index++)
            {
                try
                {
                    bw.write(edges.get(index).neo4jE);
                    if (index != edges.size() - 1)
                    {
                        bw.write(",");
                    }
                    bw.newLine();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (bw != null)
                {
                    bw.close();
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        logger.info("转换完毕");
    }

    public static void main(String[] args)
    {
        gdmToNeo4j g = new gdmToNeo4j();
        g.start();
    }
}
