package com.kevin.functionalInterface;

import static org.junit.Assert.assertEquals;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.DoublePredicate;
import java.util.function.Function;
import java.util.function.ObjIntConsumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

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
        assertEquals(1, (int)supplier.get());
        // BooleanSupplier、DoubleSupplier、IntSupplier、LongSupplier 顾名思义不废话
        BooleanSupplier booleanSupplier = () -> true;
        assertEquals(true, booleanSupplier.getAsBoolean());

        // ==================================断言型函数式接口=============================================/
        // 接收一个参数断言,Predicate有四个方法：test、and、or、 negate 分别对应 判断、与、或、非
        Predicate<String> predicate = (a) -> "".equals(a);
        assertEquals(false, predicate.test("s"));
        assertEquals(true, predicate.negate().test("s"));
        // 接收两个参数断言
        BiPredicate<String, String> biPredicate = (a, b) -> a.equals(b);
        assertEquals(false, biPredicate.test("s", "b"));
        // 限定接收一个为double的参数：XXXPredicate 其中xxx为：Double、Int、Long
        DoublePredicate doublePredivate = (a) -> Double.valueOf(0.0d).equals(a);
        doublePredivate.test(1);
        assertEquals(false, doublePredivate.test(1));

        // ===========================Function<T,R>函数式接口=============================================/
        // 接收一个T类型参数返回一个Integer类型的结果
        Function<String, Integer> function = Integer::parseInt;
        assertEquals(12, (int)function.apply("12"));
        // 下面列举的是对Function的细化，没什么好说的，顾名思义
        // DoubleFunction<R>(DoubleToIntFunction、DoubleTOLongFunction)、
        // LongFunction<R>(LongToDoubleFunction、LongToIntFunction)、
        // IntFunction<R>(IntToLongFunction、IntToDoubleFunction)、
        // ToxxxFunction(ToDoubleFunction<T>、ToIntFunction<T>、ToLongFunction<T>)

        // BiFunction<T, U, R> 接收两个参数 返回一个R类型的结果
        BiFunction<Integer, Integer, String> biFunction = (a, b) -> String.valueOf(a + b);
        assertEquals("3", biFunction.apply(1, 2));

        // 下面列举的是对BiFunction的细化
        // ToDoubleBiFunction<T, U>、ToLongBiFunction<T, U>、ToIntBiFunction<T, U>

        // ===========================Operator函数式接口=============================================/
        // 接收一个T类型的参数返回一个相同类型的结果，UnaryOperator有一个静态方法identity，返回操作符UnaryOperator对象本身
        UnaryOperator<Integer> unaryoperator = x -> x + 1;
        assertEquals(11, (int)unaryoperator.apply(10));
        // 下面列举的是对UnaryOperator的细化
        // DoubleUnaryOperator、LongUnaryOperator、IntUnaryOperator

        // 接收两个相同类型的参数，转换成相同类型的结果输出，BinaryOperator和BiFunction不同的地方在于，提供了两个静态函数：minBy、maxBy用来返回带有比较器的BinaryOperator
        BinaryOperator<Integer> binaryOperator = (a, b) -> a + b;
        assertEquals(3, (int)binaryOperator.apply(1, 2));
        BinaryOperator<Integer> maxBinaryOperator = BinaryOperator.maxBy((a, b) -> Integer.compare(a, b));
        assertEquals(2, (int)maxBinaryOperator.apply(1, 2));
    }
}
