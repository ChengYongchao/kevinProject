
package com.kevin.designmode.TemplateMethodPatternExtend;

/**
 * 在这里加入功能说明
 *
 * @author chengyongchao
 * @version 1.0, 2019年12月17日
 */
public class Car1 extends Template
{
    private boolean flag = true;

    protected void method1()
    {
        System.out.println("I`m car1");
    }

    protected void method2()
    {
        System.out.println("I can bibibi");
    }

    protected void method3()
    {
        System.out.println("car1 byebye~");
    }

    protected boolean isBiBiBi()
    {
        return this.flag;
    }

    public void setBiBiBi(boolean flag)
    {
        this.flag = flag;
    }
}
