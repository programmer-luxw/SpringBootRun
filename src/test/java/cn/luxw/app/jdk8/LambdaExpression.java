package cn.luxw.app.jdk8;


import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Created by wangwenjun on 2016/10/12.
 * 
 * lambda有两种写法,可以用来构造匿名内部类对象(只有一个方法的接口)
 */
public class LambdaExpression {

    public static void main(String[] args) {

        Comparator<Apple> byColor = new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getColor().compareTo(o2.getColor());
            }
        };

        List<Apple> list = Collections.emptyList();

        list.sort(byColor);

        //1.匿名内部类只有一个方法的可以推导出来这个类.
        //箭头后面没有大括号的时候,返回值可以推导出来
        Comparator<Apple> byColor2 = (o1, o2) -> o1.getColor().compareTo(o2.getColor());
        
        //传入string,传出Integer
        Function<String, Integer> flambda = s -> s.length();
        Function<String, Integer> flambda2 = String::length;
        
        
        Function<Integer,String> b = (Integer i) -> i.toString();
        
        //传入apple,返回boolean
        Predicate<Apple> p = (Apple a) -> a.getColor().equals("green");

        Runnable r = ()->{};


        Function<Apple,Boolean> f = (a)->a.getColor().equals("green");
        //
        
        Supplier<Apple> s = Apple::new;
        Consumer<String> c = (str)->{};
      //格式
       // (param)->expression 返回值可以推导出来
        //(param) ->{expression;} 如果有return 关键字,必须有{}和;
        
        
        
    }
}
