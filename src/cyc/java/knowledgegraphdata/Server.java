package cyc.java.knowledgegraphdata;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

/**
 * 在这里加入功能说明
 *
 * @author chengyongchao
 * @version 1.0, 2019年12月6日
 */
public class Server
{
    public static final String URL = "https://api.ownthink.com/bot?spoken=";

    private static volatile Server instance;

    private CloseableHttpClient client;

    private Server()
    {
        client = HttpClientBuilder.create().build();
    }

    public static Server instance()
    {
        if (instance == null)
        {
            synchronized (Server.class)
            {
                if (instance == null)
                {
                    instance = new Server();
                }
            }
        }
        return instance;
    }

    public String search(String context)
    {
        HttpGet get = new HttpGet(URL + context);

        CloseableHttpResponse response = null;
        try
        {
            response = client.execute(get);
            HttpEntity entity = response.getEntity();
            if (entity != null)
            {
                String result = EntityUtils.toString(entity);
                return result;
            }
        }
        catch (ClientProtocolException e)
        {
            e.printStackTrace();
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                // 释放资源
                if (response != null)
                {
                    response.close();
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            } 
        }
        return null;
    }

    public static void main(String[] args)
    {
        Server server = Server.instance();
        String result = server.search("教师有哪些类别");
        System.out.println(result);
    }
}
