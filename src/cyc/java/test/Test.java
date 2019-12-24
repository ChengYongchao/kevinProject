package cyc.java.test;

/**
 * 在这里加入功能说明
 *
 * @author chengyongchao
 * @version 1.0, 2019年12月24日
 */
public class Test
{
    public enum Status
    {
        NOJOB, INITIALIZED, COMPUTING;
    }
    
    public static void main(String[] args)
    {
        System.out.println(Status.NOJOB.toString());
    }
}
