
package cyc.java.designmode.TemplateMethodPatternExtend;

/**
 * 在这里加入功能说明
 *
 * @author chengyongchao
 * @version 1.0, 2019年12月17日
 */
public class Client
{
    public static void main(String[] args)
    {
        Car1 car = new Car1();
        car.setBiBiBi(false);
        car.templateMethod();
    }
}
