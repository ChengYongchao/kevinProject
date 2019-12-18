/*
 * @(#)Client.java, 2019年12月17日 下午3:19:19
 *
 * Copyright (c) 2018-2019, 四川蜀天梦图数据科技有限公司.
 * All rights reserved.
 */
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
