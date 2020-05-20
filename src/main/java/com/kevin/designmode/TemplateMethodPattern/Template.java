
package com.kevin.designmode.TemplateMethodPattern;

/**
 * 模板方法模式：定义一个操作中的算法的框架，而将一些步骤延迟到子类中。使得子类可以不改变一个算法的结构即可重定义该算法的某些特定步骤。
 * 一般将模板方法定义为final的 防止子类恶意重写
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

    // 模板方法
    public final void templateMethod()
    {
        this.method1();
        this.method2();
        this.method3();
    }
}
