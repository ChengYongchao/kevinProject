
package com.kevin.reflect;

/**
 * 在这里加入功能说明
 *
 * @author chengyongchao
 * @version 1.0, 2020年10月27日
 */
public class BaseClass
{
    private String var1;

    public String var2;

    public BaseClass()
    {}

    public BaseClass(String var1)
    {
        this.var1 = var1;
    }

    public String getVar1()
    {
        return var1;
    }

    private void setVar1(String var1)
    {
        this.var1 = var1;
    }

    public String getVar2()
    {
        return var2;
    }

    public void setVar2(String var2)
    {
        this.var2 = var2;
    }

}
