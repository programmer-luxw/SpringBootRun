package cn.luxw.app.guava.concurrent;

import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Test {

	public static void main(String[] args) {
		//Stream.generate(Math::random).limit(2).forEach(System.out::println);
		//IntStream.range(1, 5).forEach(System.out::println);
		test2(v->System.out.println(v));
	}
	
	public static void test2(Consumer<Integer> consumer) {
		consumer.accept(7);
	}

}
