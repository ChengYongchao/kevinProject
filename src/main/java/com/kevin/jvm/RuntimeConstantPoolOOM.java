package com.kevin.jvm;

/**
 * @author chengyongchao
 * @version 1.0, 2020年9月10日
 */
public class RuntimeConstantPoolOOM
{
    public static void main(String[] args)
    {
        String str1 = new StringBuffer("计算机").append("软件").toString();
        System.out.println(str1.intern() == str1);

        String str2 = new StringBuffer("ja").append("va").toString();
        System.out.println(str2.intern() == str2);
        // result: true false reason: java 在sun.misc.Version init的时候已经被创建了
        // ==比较引用，两个对象不是同一个对象，所以可证：若想str2.intern() == str2 必须保证
        // str2的字符串是第一次加载到字符串常量区
        System.out.println(str2.intern() == str2.intern());

        String str3 = new StringBuffer("计算机").append("软件").toString().intern();
        System.out.println(str1.intern() == str3.intern());
        System.out.println(str1 == str3);
        System.out.println(str1.equals(str3));
    }
}
