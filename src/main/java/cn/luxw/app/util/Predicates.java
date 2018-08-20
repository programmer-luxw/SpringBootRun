//package cn.luxw.app.util;
//
//import com.google.common.base.Predicates;
//
//public class Predicates {
//
//	public static boolean isNull(Object obj) {
//		Predicates<Object> predicate = com.google.common.base.Predicates.isNull();
//		return predicate.apply(obj);
//	}
//
//	public static boolean isNotNull(Object obj) {
//		Predicate<Object> predicate = com.google.common.base.Predicates.notNull();
//		return predicate.apply(obj);
//	}
//
//	public static boolean alwaysTrue() {
//		Predicate<Object> predicate = com.google.common.base.Predicates.alwaysTrue();
//		return predicate.apply(returnNull());
//	}
//
//	public static boolean alwaysFalse() {
//		Predicate<Object> predicate = com.google.common.base.Predicates.alwaysFalse();
//		return predicate.apply(returnNull());
//	}
//
//	public static Object returnNull() {
//		return null;
//	}
//
//	public static void main(String[] args) {
//		System.out.println(alwaysFalse());
//	}
//}
