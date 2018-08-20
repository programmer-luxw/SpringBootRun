package cn.luxw.app.util.refle;


import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Set;

import com.google.common.base.Throwables;
import com.google.common.collect.Sets;
import com.google.common.collect.Sets.SetView;

/**
 * 通过反射获取实体字段或者属性
 * 
 * @author luxw
 * @version 1.0,2014-12-20
 */
public class Reflections {

	/**
	 * 获取实体字段名
	 * @param entityClass
	 * @param annotationClass
	 * @return
	 */
	public static <T, annotationClass extends Annotation> Set<String> getFieldNames(Class<T> entityClass, Class<annotationClass> annotationClass) {
		Set<String> fieldNames = Sets.newLinkedHashSet();
		Set<Field> fields = getFields(entityClass, annotationClass); 
		for (Field field : fields) {
			String name = field.getName();
			fieldNames.add(name);
		}
		return fieldNames;
	}

	public static <T, annotationClass extends Annotation> Set<Field> getFields(Class<T> entityClass, Class<annotationClass> annotationClass) {
		return __GetFields__(entityClass, annotationClass); 
	}

	private static <T, annotationClass extends Annotation> Set<Field> __GetFields__(Class<T> entityClass, Class<annotationClass> annotationClass) {
		Set<Field> allGenericFields = Sets.newLinkedHashSet();
		if (null == entityClass)
			return allGenericFields;
		return __GetFields__(allGenericFields, entityClass, annotationClass);
	}

	@SuppressWarnings("unchecked") 
	private static <T, annotationClass extends Annotation> Set<Field> __GetFields__(Set<Field> allGenericFields, Class<T> genericClass, Class<annotationClass> annotationClass) {
		// 返回表示此 Class 所表示的实体（类、接口、基本类型或 void）的直接超类的 Type
		Object parent = genericClass.getGenericSuperclass();
		if (null != parent) {
			__GetFields__(allGenericFields, (Class<T>) parent, annotationClass);// 递归获取父类的
		}
		// 返回一个 Field 对象，该对象反映此 Class 对象所表示的类或接口的指定已声明字段。
		Field[] fields = genericClass.getDeclaredFields();
		if (null != fields) {
			for (Field field : fields) {
//				aladdinIgnoreAnnotation ignoreUpdateField = field.getAnnotation(aladdinIgnoreAnnotationClass);
				String fieldName = field.getName();
				if (!"serialVersionUID".equalsIgnoreCase(fieldName) && !field.isAnnotationPresent(annotationClass)) {
					allGenericFields.add(field);
				}
			}
		}
		return allGenericFields;
	}

	/**
	 * 获取实体属性名
	 * @param genericClass
	 * @param annotationClass 
	 * @return
	 */
	public static <T, annotationClass extends Annotation> Set<String> getPropertyNames(Class<T> entityClass, Class<annotationClass> annotationClass ) {
		Set<String> results = Sets.newLinkedHashSet();
		try {
			// 内省操作
			Set<String> propertyNames = Sets.newLinkedHashSet();
			BeanInfo beanInfo = Introspector.getBeanInfo(entityClass);
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor properdesc : propertyDescriptors) {
				String propertyName = properdesc.getName();
				if (!"class".equalsIgnoreCase(propertyName)) {
					propertyNames.add(propertyName);
				}
			}
			Set<String> fieldNames = getFieldNames(entityClass, annotationClass );//实现注解功能
			SetView<String> setViews = Sets.intersection(propertyNames, fieldNames);// 交集
			results.addAll(setViews);
		} catch (Exception e) {
			Throwables.propagate(e);
		}
		return results;
	}

	
	
	
/*	public static void main(String[] args) {
		 Set<String> propertyNames = getPropertyNames(null);
		 System.out.println(propertyNames);
		 Object object = Class.forName(genericClass.getName()).newInstance();
		 Method getter = properdesc.getReadMethod();// 得到property对应的getter方法
		 Object propertyValue = getter.invoke(user);
		 System.out.println(propertyName +"=="+ propertyValue);
		 Class<?> clazz = properdesc.getPropertyType();
		 System.out.println(MysqlStatus.class.equals(clazz));
		 Set<String> name = getFieldNames(User.class,AladdinIgnoreUpdate.class);
		 System.out.println(name);

		Set<String> results = getPropertyNames(User.class,AladdinIgnoreUpdate.class);
		System.out.println(results);
	}*/
}
