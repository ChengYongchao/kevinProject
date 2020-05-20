package com.kevin.functionalInterface;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntSupplier;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Java8FunctionalInterfaceAPI implements FunTest
{
    public static void main(String[] args)
    {
        // 自定义函数式接口
        // 通过类实现接口
        Java8FunctionalInterfaceAPI fi = new Java8FunctionalInterfaceAPI();
        fi.get();
        System.out.println(fi.getName1() +" "+ FunTest.getName4());
        // 消费型接口 Consumer<T>
        // 通过lambda表达式实现接口
        Consumer<Integer> ft1 = System.out::println;
        ft1.accept(100);

        Consumer<String> ft2 = t ->
        {
            System.out.println(t);
        };
        ft2.accept("start");
        BiConsumer<Integer, Integer> bc1 = (a, b) ->
        {
            System.out.println(a + b);
        };
        bc1.accept(44, 55);

        // 供给型接口 Supplier<T>
        Supplier<Integer> sp1 = () ->
        {
            return 1;
        };
        int num1 = sp1.get();
        IntSupplier is1 = () ->
        {
            return 33;
        };
        int num2 = is1.getAsInt();

        // 函数型接口 Function<T,R>
        Function<Integer, String> fun = a ->
        {
            return a.toString();
        };
        System.out.println(fun.apply(99999));
        BiFunction<String, String, String> bfun = (a, b) ->
        {
            return a + b;
        };
        System.out.println(bfun.apply("hello", " java"));

        // 断言型接口 Predicate<T>

        Predicate<Integer> pd1 = a ->
        {
            return a == 9;
        };
        Predicate<Integer> pd2 = a ->
        {
            return a != 8;
        };
        System.out.println(pd1.test(9) + " " + pd1.negate().test(9));
        System.out.println(pd1.and(pd2).test(8));
        BiPredicate<Integer, String> bpd = (a, b) ->
        {
            return a == 8 && "8".equals(b);
        };
        System.out.println(bpd.test(8, "8"));

    }

    public void get()
    {
        System.out.println("test functionalInterface");
    }
}
