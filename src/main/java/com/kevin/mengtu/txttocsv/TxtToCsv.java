package com.kevin.mengtu.txttocsv;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

public class TxtToCsv
{
    private static Logger logger = Logger.getLogger("TxtToCsv");

    private BigFileReader bigFileReader;

    private Set<Long> vertexIds;

    private Map<Long, Set<Long>> edges;

    private String baseDir;

    public void init(String fileDir, int vertexNum, int edgeNum)
    {
        BigFileReader.Builder builder = new BigFileReader.Builder(fileDir, new IHandle()
        {

            @Override
            public void handle(String line)
            {
                if (line == null || "".equals(line))
                {

                }
                else
                {
                    // 跳过注释
                    if (line.indexOf('#') == -1)
                    {
                        int index = line.indexOf('\t');
                        Long startId = Long.valueOf(line.substring(0, index));
                        Long endId = Long.valueOf(line.substring(index, line.length()).replaceAll("\t", ""));

                        // 导点
                        vertexIds.add(startId);
                        vertexIds.add(endId);
                        // 导边
                        if (edges.get(startId) == null)
                        {
                            edges.put(startId, ConcurrentHashMap.newKeySet());
                        }
                        edges.get(startId).add(endId);
                    }
                }

            }
        });
        builder.withTreahdSize(10).withCharset("gbk").withBufferSize(1024 * 1024);
        this.bigFileReader = builder.build();

        this.vertexIds = ConcurrentHashMap.newKeySet(vertexNum);
        this.edges = new ConcurrentHashMap<Long, Set<Long>>(edgeNum);
        this.baseDir = System.getProperty("user.dir");
    }

    public void execute()
    {
        this.loadTxt();
        this.toCsv();
    }

    private void loadTxt()
    {
        this.bigFileReader.start();

        bigFileReader.shutdown();

        logger.info("数据抽取完成：");
        logger.info("点数量：" + vertexIds.size());
        int count = edges.entrySet().stream().map(s ->
        {
            return s.getValue().size();
        }).reduce(0, (a, b) ->
        {
            return a + b;
        });
        logger.info("边数量： " + count);
    }

    private void toCsv()
    {
        this.loadVertexToCsv();
        this.loadEdgesToCsv();
    }

    private void loadVertexToCsv()
    {
        String vertexDir = baseDir + "\\Vertex.csv";
        BufferedWriter fileOutputStream = null;
        try
        {
            File vertexFile = new File(vertexDir);
            if (vertexFile.exists())
            {
                vertexFile.delete();
            }
            vertexFile.createNewFile();
            fileOutputStream = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(vertexFile), "GBK"),
                    1024);
            Iterator<Long> iterator = vertexIds.iterator();

            fileOutputStream.write("vertex");
            while (iterator.hasNext())
            {
                fileOutputStream.write(iterator.next() + "\n");
                // fileOutputStream.newLine();
            }
            fileOutputStream.flush();

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                fileOutputStream.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        logger.info("vertexId写入完毕");
    }

    private void loadEdgesToCsv()
    {
        String vertexDir = baseDir + "\\Edge.csv";
        BufferedWriter fileOutputStream = null;
        try
        {
            File vertexFile = new File(vertexDir);
            if (vertexFile.exists())
            {
                vertexFile.delete();
            }
            vertexFile.createNewFile();
            fileOutputStream = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(vertexFile), "GBK"),
                    1024);
            fileOutputStream
                    .write("target,source,edgelabel-edge,sourcevertexlabel-vertex,targetvertexlabel-vertex" + "\n");
            for (Long startId : edges.keySet())
            {

                Iterator<Long> iterator = edges.get(startId).iterator();
                while (iterator.hasNext())
                {
                    fileOutputStream.write(startId + "," + iterator.next() + "," + "\n");
                    // fileOutputStream.newLine();
                }

            }
            fileOutputStream.flush();

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                fileOutputStream.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        logger.info("边写入完毕");
    }

    public static void main(String[] args)
    {
        double startTime = System.currentTimeMillis();
        TxtToCsv loader = new TxtToCsv();
        loader.init("C:\\Users\\Administrator\\Desktop\\com-lj.ungraph-34m.txt", 4000000, 35000000);
        loader.execute();
        System.out.println("time" + (System.currentTimeMillis() - startTime));
    }
}
