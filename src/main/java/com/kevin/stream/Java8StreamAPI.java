package com.kevin.stream;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.Vector;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

public class Java8StreamAPI
{

    // ==================================获取流=============================================/

    @Test
    public void getStreamTest()
    {
        /**
         * {@code Collection}在JDK1.8版本实现了Stream()方法和parallelStream()方法，
         * 所有实现Collection的容器类通过调用stream()方法或者 parallelStream()
         */
        // list
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.stream();
        assertEquals(list.get(0), "a");
        // Set
        Set<Integer> set = new HashSet<Integer>();
        set.add(1);
        set.add(2);
        set.add(3);
        set.stream();
        set.parallelStream();
        // Vector
        Vector<Integer> vector = new Vector<>();
        vector.add(1);
        vector.add(2);
        vector.stream();

        // Queue
        Queue<Integer> queue = new ArrayBlockingQueue<>(12);
        queue.add(1);
        queue.add(2);
        queue.stream();

        // Stack
        Stack<Integer> stack = new Stack<>();
        stack.add(1);
        stack.add(2);
        stack.stream();

        // Map 通过调用map的entrySet()方法，获取Set再调用stream()
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "a");
        map.put(2, "b");
        map.entrySet().parallelStream();

        /**
         * {@code Arrays}在JDK1.8中加入了stream系列方法， 数组可以调用Arrays.stream(T []
         * array)实现流，同时还有参数不同的stream函数用来截取数组
         */
        // 数组
        String[] arr = {"a", "b", "c"};
        Arrays.stream(arr);
        
        // 值，直接将值转变成流对象
        Stream.of("a", "b", "c");

        // 从IO获取流
        try (
            Stream<String> lines = Files.lines(Paths.get("E:\\csv\\Vertex.csv"), Charset.defaultCharset())
        )
        {
            // 可对lines做一些操作
            lines.limit(10).forEach(System.out::println);
        }
        catch (IOException e)
        {}

        // 拓展：无限流的创建

        // 迭代
        Stream.iterate(0, x -> x + 1).limit(10).forEach(System.out::println);
        // 随机生成
        Stream.generate(() -> Math.random());

    }

    // =============================stream方法===============================//

    /**
     * stream常用方法
     */
    @Test
    public void streamMethodTest()
    {
        /**
         * @decription 过滤器filter
         * @param 一个返回true或者false的lambda表达式
         * @return 筛选出返回结果为true的元素
         */
        Stream.of(1, 2, 3).filter(a ->
        {
            return a.equals(1);
        }).count();

        /**
         * @description 去重
         * @return the new stream
         */
        Stream.of(1, 2, 2, 3).distinct();

        /**
         * @decription 截取前n个元素
         * @return the new stream
         */
        Stream.of(1, 2, 3).limit(2);

        /**
         * @decription 跳过前n个元素
         * @return the new stream
         */

        Stream.of(1, 2, 3).skip(2);

        /**
         * @decription map() 一对一映射 输入一个元素，按照给定的规则处理该元素
         */
        Stream.of("a", "b", "c").map(a ->
        {
            return a.toUpperCase();
        }).map(String::toLowerCase);

        /**
         * @description flatMap() 将流扁平化，说人话就是将流中最基层的元素抽出来为单独的流，eg:
         *              List<List<Integer>>
         *              类型的，会把最底层的Integer抽取出来，和map()不同的是map()将List<Integer>视为一个元素进行处理
         */
        // 写法1
        Stream.of(Arrays.asList(1, 2), Arrays.asList(3, 4), Arrays.asList(5, 6, 7)).flatMap(Collection::stream).map(a ->
        {
            // 此时 a就只是Integer了
            return a.byteValue();
        });
        // 写法2
        Stream.of(Arrays.asList(1, 2), Arrays.asList(3, 4), Arrays.asList(5, 6, 7)).flatMap(l ->
        {
            return l.stream();
        }).map(a ->
        {
            return a.byteValue();
        });

        /**
         * @description 是否匹配任意元素,有任意一个符合为true
         */
        Stream.of(1, 2, 3).anyMatch(a ->
        {
            return a.equals(1);
        });

        /**
         * @description 是否匹配所有元素
         */
        Stream.of(1, 2, 3).allMatch(a ->
        {
            return a.equals(1);
        });

        /**
         * @description 是否所有元素都不匹配
         */
        Stream.of(1, 2, 3).noneMatch(a ->
        {
            return a.equals(1);
        });

        /**
         * @description 从流中返回任意元素，返回结果放在Optional对象中(实测怎么感觉就是findFirst())
         */
        Stream.of(1, 2, 3).findAny();

        /**
         * @description 返回第一个元素，返回结果放在Optional对象中
         */
        Optional<Integer> res = Stream.of(Arrays.asList(1, 2), Arrays.asList(3, 4)).flatMap(Collection::stream)
                .findFirst();
        res.ifPresent(System.out::print);

        /**
         * @description reduce 自定义lambda表达式求和 以
         *              identity为初始值，对流中的所有元素执行lambda表达式求和
         */
        Stream.of(1, 2, 3).reduce(0, (a, b) ->
        {
            return a + b;
        });

    }

    // =============================stream数值流 ===============================//
    /**
     * @description 将Stream流转换成数值流 eg： IntStream、LongStream
     */
    @Test
    public void test3()
    {
        Stream.of(1, 2, 3).mapToDouble(Double::valueOf);
        Stream.of(1, 2, 3).mapToLong(Long::valueOf);
    }

    /**
     * 对Map进行排序
     */
    @Test
    public void sortedMap()
    {
        Map<String, String> map = new LinkedHashMap<>();
        // 方法一
        map = map.entrySet().stream().sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
        // 方法二（其实就是对方法一的封装）
        map = map.entrySet().stream().sorted(Map.Entry.<String, String> comparingByValue().reversed())
                .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
        map.entrySet().stream().collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
    }

    // =============================优雅的结束Stream流===============================//

    /**
     * 优雅的结束Stream流
     */
    @SuppressWarnings ("unused")
    @Test
    public void endStreamTest()
    {
        // 返回Map
        Stream.of("a", "b", "c").collect(Collectors.toMap(s -> s, s -> s));
        // 返回 List
        Stream.of("a", "b", "c").collect(Collectors.toList());
        // 返回Set
        Stream.of("a", "b", "c").collect(Collectors.toSet());
        //返回数组
        Stream.of("a", "b", "c").toArray();
        

        // ========================stream收集器 =============================//
        /**
         * @description:求数量
         */
        // 写法1
        Stream.of(1, 2, 3).count();
        // 写法2
        Stream.of(1, 2, 3).collect(Collectors.counting());

        /**
         * @description:求最值,最大值最小值都类似，比较方法有comparingDouble、comparingInt、comparingLong、甚至自定义
         */
        // 写法1
        Optional<Integer> res1 = Stream.of(1, 2, 3, 3).max((x, y) ->
        {
            return (x < y) ? -1 : ((x == y) ? 0 : 1);
        });
        assertEquals((int)res1.get(), 3);
        // 写法2 依据数值流比较大小不需要参数
        OptionalInt res2 = Stream.of(1, 2, 3, 3).mapToInt(a ->
        {
            return a;
        }).max();
        assertEquals((int)res2.getAsInt(), 3);
        // 写法3 借助已有的Collectors.maxBy方法，没啥用，知道能这样写就好
        Optional<Integer> res3 = Stream.of(1, 2, 3, 3).collect(Collectors.maxBy(Comparator.comparingInt(a ->
        {
            return a;
        })));
        assertEquals((int)res3.get(), 3);

        /**
         * @description:求和、求平均值 方法也很多，类似求最值
         */

        // ========================stream连接字符串 =============================//
        /**
         * @description:连接字符串，默认中间无连接符，可以在joining方法中加入连接符 joining("_")
         */
        Stream.of("a", "b").collect(Collectors.joining());
        Stream.of("a", "b").reduce("", (a, b) ->
        {
            return a + b;
        });

        /**
         * @description:自定义规约操作
         * @param:1.初始值
         * @param:2.对流中的数据进行处理
         * @param:3.对处理后的元素进行自定义规约操作
         */
        Stream.of("a", "b", "c").collect(Collectors.reducing("begin:", a ->
        {
            return a;
        }, (a, b) ->
        {
            return a + b;
        }));

        // ========================stream连接字符串 =============================//
        /**
         * @description:将元素按指定规则分组
         */
        Map<String, List<String>> group1 = Stream.of("a", "b", "c").collect(Collectors.groupingBy(a ->
        {
            if ("a".equals(a))
            {
                return "isA";
            }
            else if ("b".equals(a))
            {
                return "isB";

            }
            else
            {
                return "isOthers";
            }
        }));

        // 可以在collect方法中添加参数，如再次分组或者统计每个分组的数量
        Map<String, Long> group2 = Stream.of("a", "b", "c").collect(Collectors.groupingBy((a) ->
        {
            if ("a".equals(a))
            {
                return "isA";
            }
            else if ("b".equals(a))
            {
                return "isB";

            }
            else
            {
                return "isOthers";
            }
        }, Collectors.counting()));
    }
}
