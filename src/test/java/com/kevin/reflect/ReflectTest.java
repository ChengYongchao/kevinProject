
package com.kevin.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;

/**
 * 在这里加入功能说明
 *
 * @author chengyongchao
 * @version 1.0, 2020年10月27日
 */
public class ReflectTest
{
    @Test
    public void testReflect()
    {
        BaseClass bs = new BaseClass("1");
        Class cl1 = bs.getClass();
        System.out.println(cl1.getName());

        Class cl2 = BaseClass.class;
        System.out.println(cl1 == cl2);
        Class cl3 = null;
        try
        {
            cl3 = Class.forName("com.kevin.reflect.BaseClass");
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        System.out.println(cl3 == cl1);
        
        //Method
        
       
        try
        {
            Method f = cl1.getDeclaredMethod("setVar1", String.class);
            f.setAccessible(true);
            f.invoke(bs, "20");
            
            Method getF = cl1.getMethod("getVar1", null);
            String res = (String)getF.invoke(bs, null);
            System.out.println(res);
        }
        catch (NoSuchMethodException | SecurityException e1)
        {
            e1.printStackTrace();
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
        catch (IllegalArgumentException e)
        {
            e.printStackTrace();
        }
        catch (InvocationTargetException e)
        {
            e.printStackTrace();
        }
        
        //Field
        
        try
        {
            Field field = cl1.getField("var2");
            System.out.println(field);
            
            Field field2 = cl1.getDeclaredField("var1");
            System.out.println(field2);
            field2.setAccessible(true);
            String res2 = (String)field2.get(bs);
            System.out.println(res2);
        }
        catch (NoSuchFieldException | SecurityException e1)
        {
            e1.printStackTrace();
        }
        catch (IllegalArgumentException e)
        {
            e.printStackTrace();
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
        
        //Constructor
        Constructor ct1 = null;
        try
        {
             ct1 = BaseClass.class.getConstructor();
        }
        catch (NoSuchMethodException e)
        {
            e.printStackTrace();
        }
        catch (SecurityException e)
        {
            e.printStackTrace();
        }
        BaseClass bs2;
        try
        {
            bs2 = (BaseClass)ct1.newInstance();
            System.out.println(bs2.toString());
        }
        catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
        {
            e.printStackTrace();
        }
        
    }
}
