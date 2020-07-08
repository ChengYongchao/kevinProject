/*
 * @(#)GenericClass.java, 2020年6月3日 上午10:36:27
 *
 * Copyright (c) 2018-2020, 四川蜀天梦图数据科技有限公司.
 * All rights reserved.
 */
package com.kevin.genericity;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 在这里加入功能说明
 *
 * @author chengyongchao
 * @version 1.0, 2020年6月3日
 */
public class GenericClass<T>
{
    public T add(T t)
    {
        return t;
    }

    /**
     * 泛型方法，单独的泛型声明擦除为Object
     */
    public static <V> V add(V v1, V v2)
    {
        return v1;
    }

    /**
     * 有继承上限说明的擦除为父类类型
     */
    public <A extends Number> void get(A a)
    {}

    /**
     * 类泛型声明不能用在静态 变量和静态函数中，因为泛型类中的泛型参数的实例化是在定义对象的时候指定的，
     * 而静态变量和静态方法不需要使用对象来调用。对象都没有创建，如何确定这个泛型参数是何种类型，所以当然是错误的。
     */
    /*
     * public static T set() { }
     */

    public int get()
    {
        return 0;
    }

    public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
    {
        // 这两个参数都是Integer，所以V为Integer
        int res = GenericClass.add(1, 2);
        // 一个参数为Integer，一个参数为Double 返回类型取同一父类Number
        Number res2 = GenericClass.add(1, 2.0d);
        // 这两个参数一个是Integer，一个是String，所以取同一父类，为Object
        Object res3 = GenericClass.add(1, "2");

        List<String> list1 = new ArrayList<String>();
        list1.add("1"); // 编译通过
        // list1.add(1); //编译错误
        String str1 = list1.get(0); // 返回类型为String

        List list2 = new ArrayList<String>();
        list2.add("1"); // 编译通过
        list2.add(1); // 编译通过
        Object object = list2.get(0); // 返回类型为Object

        
        
        
        //Java 泛型为何是伪泛型
        List<Integer> list = new ArrayList<>();
        list.add(1);

        //list.add("a"); // 这样直接添加肯定是不允许的

        //下面通过java的反射，绕过泛型 来给添加字符串

        Method add = list.getClass().getMethod("add", Object.class);

        add.invoke(list,"a");

        System.out.println(list); //[1, a] 输出没有没问题

        System.out.println(list.get(1)); //a

    }
}
