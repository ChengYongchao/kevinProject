package cyc.java.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;

public class Java8StreamAPI
{
    public static void main(String[] args)
    {
        /****************** 获取流 ******************/

        // list
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.stream();
        // 数组
        String[] arr = {"a", "b", "c"};
        String[] array2 = new String[] {};
        String[] array3 = new String[6];
        // 使用Arrays静态方法stream转换成流
        Arrays.stream(arr);

        // 值，直接将值转变成流对象
        Stream<String> stream = Stream.of("a", "b", "c");

        // Map，获取entrySet调用stream方法
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "a");
        map.put(2, "b");
        map.put(3, "c");
        map.put(4, "d");
        map.entrySet().stream();
        // 创建并行流
        map.entrySet().parallelStream();
        // 文件
        /*
         * try(Stream lines =
         * Files.lines(Paths.get(“文件路径名”),Charset.defaultCharset())){
         * //可对lines做一些操作 }catch(IOException e){ }
         */

        /****************** stream方法 ******************/

        /**
         * @decription 过滤器filter
         * @param 一个返回true或者false的lambda表达式
         * @return 筛选出返回结果为true的元素
         */

        int cout1 = (int)Arrays.stream(arr).filter(a ->
        {
            return a.equals("c");
        }).count();

        /**
         * @description 去重
         * @return 去除重复的元素后的流
         */
        Arrays.stream(arr).distinct();

        /**
         * @decription 截取前n个元素
         * @return 流
         */
        Arrays.stream(arr).limit(2);

        /**
         * @decription 跳过前n个元素
         */
        Arrays.stream(arr).skip(1l);

        /**
         * @decription 映射
         */

        int cout2 = (int)Arrays.stream(arr).map(a ->
        {
            List<String> li = new ArrayList<>();
            li.add(a);
            return li.toArray();
        }).flatMap(Arrays::stream).map(ar ->
        {
            System.out.println(ar);
            return ar;
        }).count();
        System.out.println(cout2);

        /**
         * @description 是否匹配任意元素
         */

        Arrays.stream(arr).anyMatch(a ->
        {
            return a.equals("b");
        });

        /**
         * @description 是否匹配所有元素
         */

        Arrays.stream(arr).allMatch(a ->
        {
            return a.equals("b");
        });

        /**
         * @description 是否所有元素都不匹配
         */

        Arrays.stream(arr).noneMatch(a ->
        {
            return a.equals("b");
        });

        /**
         * @description 从流中返回任意元素，返回结果放在Optional对象中(实测怎么感觉就是findFirst())
         */
        Optional<String> op1 = Arrays.stream(arr).findAny();

        /**
         * @description 返回第一个元素，返回结果放在Optional对象中
         */

        Optional<String> op2 = Arrays.stream(arr).findFirst();
        op2.ifPresent((a) ->
        {
            System.out.println(a);
        });
        op2.isPresent();
        op2.orElse("dd");

        /**
         * @description reduce 自定义lambda表达式求和 以
         *              identity为初始值，对流中的所有元素执行lambda表达式求和
         */

        String result = Arrays.stream(arr).reduce("begin:", (a, b) ->
        {
            return a + b;
        });

        /****************** stream数值流 ******************/
        // 将普通流转换成数值流

        Arrays.stream(arr).mapToInt(a ->
        {
            return Integer.valueOf(a);
        });
        Arrays.stream(arr).mapToDouble(a ->
        {
            return Double.valueOf(a);
        });
        Arrays.stream(arr).mapToLong(a ->
        {
            return Long.valueOf(a);
        });

        /**
         * @description 数值流计算方法 max min sum
         */

        /****************** stream收集器 ******************/
        // 规约： 求最值、求和、求平均值
        /**
         * @description:求数量
         */

        long num1 = Arrays.stream(arr).count();
        // 使用收集器
        long num2 = stream.collect(Collectors.counting());

        /**
         * @description:求最值,最大值最小值都类似，比较方法有comparingDouble、comparingInt、comparingLong、甚至自定义
         */
        Optional<String> res = Arrays.stream(arr).collect(Collectors.maxBy(Comparator.comparingDouble(a ->
        {
            String a1 = a;
            char[] ch = a1.toCharArray();
            return Double.valueOf((int)ch[0]);
        })));
        System.out.println(res.get());
        /**
         * @description:求和 方法也很多，类似求最值
         */

        int sum = Arrays.stream(arr).collect(Collectors.summingInt(a ->
        {
            String a1 = a;
            char[] ch = a1.toCharArray();
            return Integer.valueOf((int)ch[0]);
        }));

        /**
         * @description:求平均值,方法也很多，类似求最值
         */

        double avg = Arrays.stream(arr).collect(Collectors.averagingInt(a ->
        {
            String a1 = a;
            char[] ch = a1.toCharArray();
            return Integer.valueOf((int)ch[0]);
        }));

        /**
         * Collectors.summarizingInt函数能一次性将最值、均值、总和、元素个数全部计算出来，并存储在对象IntSummaryStatisics中。
         * 可以通过该对象的getXXX()函数获取这些值。
         */

        /****************** stream连接字符串 ******************/
        /**
         * @description:连接字符串，默认中间无连接符，可以在joining方法中加入连接符 joining("_")
         */
        String str = Arrays.stream(arr).collect(Collectors.joining());

        /**
         * @description:自定义规约操作
         * @param:1.初始值
         * @param:2.对流中的数据进行处理
         * @param:3.对处理后的元素进行自定义规约操作
         */
        Arrays.stream(arr).collect(Collectors.reducing("begin:", a ->
        {
            return a;
        }, (a, b) ->
        {
            return a + b;
        }));

        /****************** stream分组 ******************/
        /**
         * @description:将元素按指定规则分组
         */
        Map<String, List<String>> res1 = Arrays.stream(arr).collect(Collectors.groupingBy(a ->
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
        Map<String, Long> res2 = Arrays.stream(arr).collect(Collectors.groupingBy((a) ->
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

    private Map<String, String> map2;

    private Integer[] arr;

    @Before
    public void before()
    {
        map2 = new HashMap<>();
        map2.put("1", "1");
        map2.put("2", "2");
        map2.put("3", "3");
        map2.put("4", "4");
        map2.put("5", "5");

        arr = new Integer[6];
        IntStream.range(0, 5).forEach((i) ->
        {
            arr[i] = i;
        });
    }

    @Test
    public void returnMap()
    {
        // stream流以map格式返回
        Map<String, String> map3 = new HashMap<>();
        map2 = map2.entrySet().stream().collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
        System.out.println(map3);
    }

    @Test
    public void ArrayAndMapToSet()
    {
        // stream流以set格式返回
        Set<Integer> set2 = new HashSet<>();
        set2 = Arrays.stream(arr).collect(Collectors.toSet());
        set2 = map2.entrySet().stream().map(m ->
        {
            return Integer.parseInt(m.getValue());
        }).collect(Collectors.toSet());
        System.out.println(set2);
    }

    @Test
    public void sortedMap()
    {
        // 方法一
        map2 = map2.entrySet().stream().sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
        // 方法二（其实就是对方法一的封装）
        map2 = map2.entrySet().stream().sorted(Map.Entry.<String, String> comparingByValue().reversed())
                .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));

    }
}
