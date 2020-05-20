
package com.kevin.designmode.TemplateMethodPatternExtend;

/**
 * 模板方法模式的拓展，增加钩子方法
 * 
 * @author chengyongchao
 * @version 1.0, 2019年12月17日
 */
public abstract class Template
{
    // 基本方法1
    protected void method1()
    {}

    // 基本方法2
    protected void method2()
    {}

    // 基本方法3
    protected void method3()
    {}

    protected boolean isBiBiBi()
    {
        return true;
    }

    // 模板方法
    public final void templateMethod()
    {
        this.method1();
        //增加判断
        if (isBiBiBi())
        {
            this.method2();
        }
        this.method3();
    }
}
