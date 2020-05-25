package com.kevin.functionalInterface;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.DoublePredicate;
import java.util.function.Function;
import java.util.function.IntSupplier;
import java.util.function.ObjIntConsumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.junit.Test;

public class Java8FunctionalInterfaceAPI
{

    @Test
    public void FunctionalInterfaceTest()
    {
        // 调用自定义函数式接口
        MyFunctionalInterface functionalInterface = () -> System.out.println("123");
        functionalInterface.get();
        // ==================================消费型函数式接口=============================================/

        // 接收一个参数消费
        Consumer<String> consumer = System.out::println;
        consumer.accept("Consumer FunctionalInterface");

        // 接收两个参数消费
        BiConsumer<String, String> biConsumer = (a, b) -> System.out.println(a + b);
        biConsumer.accept("bi", "Consumer");

        // ObjxxxConsumer 其中xxx为int、long、double，代表accept函数第二个参数类型
        ObjIntConsumer<Integer> objIntConsumer = (a, b) -> System.out.println(a + b);
        objIntConsumer.accept(1, 2);

        // ==================================供给型函数式接口=============================================/
        // 返回一个Integer类型整数
        Supplier<Integer> supplier = () -> 1;
        supplier.get();
        // BooleanSupplier、DoubleSupplier、IntSupplier、LongSupplier 顾名思义不废话
        BooleanSupplier booleanSupplier = () -> true;
        booleanSupplier.getAsBoolean();

        // ==================================断言型函数式接口=============================================/
        // 接收一个参数断言
        Predicate<String> predicate = (a) -> "".equals(a);
        predicate.negate();
        // 接收两个参数断言
        BiPredicate<String, String> biPredicate = (a, b) -> a.equals(b);
        biPredicate.negate();
        // 限定接收一个为double的参数：XXXPredicate 其中xxx为：Double、Int、Long
        DoublePredicate doublePredivate = (a) -> Double.valueOf(0.0d).equals(a);
        doublePredivate.negate();

    }

    public static void main(String[] args)
    {
        // 自定义函数式接口
        // 通过类实现接口

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
}
