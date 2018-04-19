package cn.luxw.app.jdk8;


import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import cn.luxw.app.jdk8.domain.Test;

/*
 * 一、方法引用：若 Lambda 体中的功能，已经有方法提供了实现，可以使用方法引用
 * 			  （可以将方法引用理解为 Lambda 表达式的另外一种表现形式）
 * 
 * 1. 对象的引用 :: 实例方法名
 * 
 * 2. 类名 :: 静态方法名
 * 
 * 3. 类名 :: 实例方法名
 * 
 * 注意：
 * 	 ①方法引用所引用的方法的参数列表与返回值类型，需要与函数式接口中抽象方法的参数列表和返回值类型保持一致！
 * 	 ②若Lambda 的参数列表的第一个参数，是实例方法的调用者，第二个参数(或无参)是实例方法的参数时，格式： ClassName::MethodName
 * 	如:非静态方法
 *      BiFunction<String, Integer, Character> ff= (x,y)->x.charAt(y);
        BiFunction<String, Integer, Character> f2 = String::charAt;
        
        Function<String, Integer> ffff = (x)->x.length();
    	Function<String, Integer> fff = String::length;
 * 二、构造器引用 :构造器的参数列表，需要与函数式接口中参数列表保持一致！
 * 
 * 1. 类名 :: new
 * 
 * 三、数组引用
 * 
 * 	类型[] :: new;
 * 
 * 
 */
public class MethodReference {

    public static void main(String[] args) {
    	  
    	
    	BiPredicate<String, String> bp = (x, y) -> x.equals(y);
		System.out.println(bp.test("abcde", "abcde"));
		
		System.out.println("-----------------------------------------");
		
		//两个x,y参数,调用顺序也是x调用y,
		BiPredicate<String, String> bp2 = String::equals;
		System.out.println(bp2.test("abc", "abc"));
		
		System.out.println("-----------------------------------------");
    	

        /*Consumer<String> consumer = (s) -> System.out.println(s);
        useConsumer(consumer, "Hello Alex");*/

        useConsumer(s -> System.out.println(s), "Hello Alex");

        useConsumer(System.out::println, "Hello Wangwenjun");

        List<Apple> list = Arrays.asList(new Apple("abc", 123), new Apple("Green", 110), new Apple("red", 123));

        System.out.println(list);

        list.sort((a1, a2) -> a1.getColor().compareTo(a2.getColor()));

        System.out.println(list); 

        list.stream().forEach(a -> System.out.println(a));

        System.out.println("==========================");
        list.stream().forEach(System.out::println);

        int value = Integer.parseInt("123");
      
        //静态方法推导1,
        Function<String, Integer> f = Integer::parseInt;

        Integer result = f.apply("123");
        System.out.println(result);

        // BiFunction<String, Integer, Character> ff= (x,y)->x.charAt(y);
        BiFunction<String, Integer, Character> f2 = String::charAt;
        Character c = f2.apply("hello", 2);
        System.out.println(c);
        
        BiFunction<String, Integer, Character> ff= (x,y)->x.charAt(y);

        
        //方法推导3,具体类的实例推导
        String string = new String("Hello");
        Function<Integer, Character> f3 = string::charAt;
        Character c2 = f3.apply(4);
        System.out.println(c2);


        //构造函数推导
        Supplier<String> supplier = String::new;
        Supplier<String> ss = ()-> new String();

        String s = supplier.get();
        System.out.println(s);

        //两个参数构造函数
        BiFunction<String, Long, Apple> appleFuntion = Apple::new;

        Apple apple = appleFuntion.apply("red", 100L);
        System.out.println(apple);


        ThreeFunction<String, Long, String, ComplexApple> threeFunction = ComplexApple::new;
        ComplexApple complexApple = threeFunction.apply("Green", 123L, "FuShi");
        System.out.println(complexApple);


        List<Apple> list2 = Arrays.asList(new Apple("abc", 123), new Apple("Green", 110), new Apple("red", 123));

        System.out.println(list2);
        
        Function<Apple,String> GGG = Apple::getColor;
        list2.sort(Comparator.comparing(Apple::getColor));
        list2.sort((a1, a2) -> a1.getColor().compareTo(a2.getColor()));
        System.out.println(list2);
    }

    private static <T> void useConsumer(Consumer<T> consumer, T t) {
        consumer.accept(t);
        consumer.accept(t);
    }
}
