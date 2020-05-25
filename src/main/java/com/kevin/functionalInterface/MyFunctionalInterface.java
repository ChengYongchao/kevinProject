package com.kevin.functionalInterface;

@FunctionalInterface
public interface MyFunctionalInterface
{
    void get();

    // public void set();
    default String getName1()
    {
        return "kevin";
    }

    default String getName2()
    {
        return "kevin";
    }

    static String getName3()
    {
        return "kevin";
    }

    static String getName4()
    {
        return "kevin";
    }
}
