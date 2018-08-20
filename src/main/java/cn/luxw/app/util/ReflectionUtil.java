package cn.luxw.app.util;


import java.lang.reflect.Field;
import java.util.Set;

import com.google.common.base.CharMatcher;
import com.google.common.collect.Sets;

public final class ReflectionUtil {
	private static CharMatcher matcher = CharMatcher.JAVA_UPPER_CASE.or(CharMatcher.is('_'));
	
	public static <T> Set<String> getFieldNames(Class<T> genericClass) {
		Set<String> fieldNames = Sets.newLinkedHashSet();
		Set<Field> fields = getFields(genericClass);
		for (Field field : fields) {
			String name = field.getName();
			fieldNames.add(name);
		}
		return fieldNames;
	}
public static void main(String[] args) {
}
	
	public static <T> Set<Field> getFields(Class<T> genericClass) {
		return new ReflectionUtil().__GetFields__(genericClass);
	}

	private <T> Set<Field> __GetFields__(Class<T> genericClass) {
		Set<Field> allGenericFields = Sets.newLinkedHashSet();
		if (null == genericClass)
			return allGenericFields;
		return __GetFields__(allGenericFields, genericClass);
	}

	@SuppressWarnings("unchecked")
	private <T> Set<Field> __GetFields__(Set<Field> allGenericFields, Class<T> genericClass) {
		//返回表示此 Class 所表示的实体（类、接口、基本类型或 void）的直接超类的 Type
		Object parent = genericClass.getGenericSuperclass();
		if (null != parent) {
			__GetFields__(allGenericFields, (Class<T>) parent);//递归获取父类的
		}
		// 返回一个 Field 对象，该对象反映此 Class 对象所表示的类或接口的指定已声明字段。
		Field[] fields = genericClass.getDeclaredFields();
		if (null != fields) {
			for (Field field : fields){
				//MysqlIgnore fieldName = field.getAnnotation(MysqlIgnore.class);
				String name = field.getName();
				if(!"serialVersionUID".equals(name)&&!matcher.matchesAllOf(name)/*&&null==fieldName*/){
					allGenericFields.add(field);
				}
			}
		}
		return allGenericFields;
	}
}