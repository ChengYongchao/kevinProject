
package com.kevin.jni;

public class JavaCallC
{
    
    static 
    {
        System.out.println(System.getProperty("java.library.path")); 
        System.loadLibrary("javaCallC");
    }


    public native int callC(String arg);

    public static void main(String[] args)
    {
        JavaCallC call = new JavaCallC();
        int res = call.callC("1");
        System.out.println(res);
    }
}
