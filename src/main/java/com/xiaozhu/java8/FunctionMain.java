package com.xiaozhu.java8;

import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FunctionMain {

    public static void testConsumer(){
        StringBuilder sb = new StringBuilder("hello ");
        Consumer<StringBuilder> consumer = (str) -> str.append("Jack!");
        consumer.accept(sb);

        System.out.println(sb.toString());
    }

    // 多个Consumer规则
    static void testMultiConsumer(){
        StringBuilder sb = new StringBuilder("hello ");
        Consumer<StringBuilder> consumer = (str) -> str.append("Jack!");
        Consumer<StringBuilder> consumer1 = (str) -> str.append(" Bob...");
        consumer.andThen(consumer1).accept(sb);

        System.out.println(sb.toString());
    }

    // BiConsumer 提供两个参数，不返回执行结果
    static void testBiConsumer(){
        StringBuilder sb = new StringBuilder();
        BiConsumer<String , String> biConsumer = (a, b) -> {
            sb.append(a);
            sb.append(b);
        };

        biConsumer.accept("Hello ", "Jack!");
        System.out.println(sb.toString());	// Hello Jack!
    }

    // 供给型，其实就是一个容器，用来存储数据，然后可以供其他方法使用的这么一个接口
    static void testSupplier(){
        Supplier<Integer> supplier = new Supplier<Integer>() {
            @Override
            public Integer get() {
                return new Random().nextInt();
            }
        };

        System.out.println(supplier.get());

        // 使用 lambda
        Supplier<Integer> supplier1 = () -> new Random().nextInt();
        System.out.println(supplier.get());
        System.out.println("********************");

        // 使用方法引用
        Supplier<Double> supplier2 = Math::random;
        System.out.println(supplier2.get());
    }

    static void testOptional() {
        System.out.println("===================================================");
        Stream<Integer> stream = Stream.of(1, 2, 90 ,3, 5);
        Optional<Integer> f = stream.filter(i -> i > 4).findFirst();

        System.out.println(f.orElse(0));
        // 由 Supplier 提供随机数
        Supplier<Integer> supplier = new Supplier<Integer>() {
            @Override
            public Integer get() {
                //返回一个随机值
                return new Random().nextInt();
            }
        };

        //orElseGet，如果first中存在数，就返回这个数，如果不存在，就返回supplier返回的值
        System.out.println(f.orElseGet(supplier));//输出结果5
    }

    static void testPredicate() {
        Predicate<Integer> predicate = number -> number != 0;
        System.out.println(predicate.test(10));    //true

        //承接上面一段代码
        predicate = predicate.and(number -> number >= 10);
        System.out.println(predicate.test(10));    //true

        // 承接上面代码
        predicate = predicate.or(number -> number != 10);
        System.out.println(predicate.test(10));    //true

        // 承接上面代码
        predicate = predicate.negate();
        System.out.println(predicate.test(10));    //false
    }

    static void testFunction(){
        // 例如像定义的泛型一样，输入的参数为String类型，返回的参数为Integer类型
        Function<String,Integer> test= i->Integer.parseInt(i)+1;
        Integer result = test.apply("5");// 6
        System.out.println(result);

        Function<Integer, Integer> name = e -> e * 2;
        Function<Integer, Integer> square = e -> e * e;
        int value = name.andThen(square).apply(3);// 36
        System.out.println(value);

        //承接上面代码 先执行square在执行name
        int value2 = name.compose(square).apply(3);// 18
        System.out.println(value2);
    }

    static void testFunctionIdentity(){
        Stream<String> stream = Stream.of("I", "Love", "You", "too");
        Map<String, Integer> map = stream.collect(Collectors.toMap(Function.identity(), String::length));
        System.out.println(map);
    }

    public static void main(String[] args) {
        // 测试Consumer接口，其实就是定义一个函数
        testConsumer();

        // 多个Consumer串联
        testMultiConsumer();

        testBiConsumer();

        testSupplier();

        testOptional();

        testFunction();

        // {love=4, too=3, I=1, you=3}
        testFunctionIdentity();
    }
}
