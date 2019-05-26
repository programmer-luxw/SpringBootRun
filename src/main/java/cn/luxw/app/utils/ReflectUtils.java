package cn.luxw.app.utils;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

import com.google.common.base.Throwables;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

public final class ReflectUtils {
	
	
	/**
	 * 获取字段名
	 * @param genericClass
	 * @return
	 */
	public static <T> Set<String> getFieldNames(Class<T> genericClass) {
		Set<String> fieldNames = Sets.newLinkedHashSet();
		Set<Field> fields = getFields(genericClass);
		for (Field field : fields) {
			String name = field.getName();
			if (!"serialVersionUID".equals(name))
				fieldNames.add(name);
		}
		return fieldNames;
	}

	public static <T> Set<Field> getFields(Class<T> genericClass) {
		return __GetFields__(genericClass);
	}

	private static <T> Set<Field> __GetFields__(Class<T> genericClass) {
		Set<Field> allGenericFields = Sets.newLinkedHashSet();
		if (null == genericClass) {
			return allGenericFields;
		}
		return __GetFields__(allGenericFields, genericClass);
	}

	@SuppressWarnings("unchecked")
	private static <T> Set<Field> __GetFields__(Set<Field> allGenericFields, Class<T> genericClass) {
		Object parent = genericClass.getGenericSuperclass();
		if (null != parent) {
			__GetFields__(allGenericFields, (Class<T>) parent);
		}
		Field[] fields = genericClass.getDeclaredFields();
		if (null != fields) {
			for (Field field : fields) {
				allGenericFields.add(field);
			}
		}
		return allGenericFields;
	}
	
	
	/**	//Map<String,String> map = BeanUtils.describe(new AffCampCap());
	 * bean 2 map
	 * @param obj
	 * @return
	 */
	public static Map<String,Object> describe(Object obj){
		Map<String, Object> map = Maps.newHashMap();
		try {
			Field[] fields = obj.getClass().getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(Boolean.TRUE);
//				String signString = field.getName();
				map.put(field.getName(),field.get(obj));
//				if(field.get(obj)!=null){//为NULL的不参与计算签名
//				}
			}
		} catch (Exception ex) {
			Throwables.propagate(ex);
		}
		return map;
	}
//	
//	public static Map<String, String> bean2map(Object obj){
//		try {
//			if(obj == null) {
//				return null;
//			}
//			Map<String,String> map = BeanUtils.describe(obj);
//			map.remove("class");
//			return map;
//		} catch (IllegalAccessException e) {
//			e.printStackTrace();
//		} catch (InvocationTargetException e) {
//			e.printStackTrace();
//		} catch (NoSuchMethodException e) {
//			e.printStackTrace();
//		}
//		return Collections.emptyMap();
//	}
	
	 public static <T> T map2Bean(Map<String, Map<String,Object>> map, Class<T> class1) {  
	        T bean = null;  
	        try {  
	            bean = class1.newInstance();  
	           // BeanUtils.populate(bean, map);  
	        } catch (InstantiationException e) {  
	            e.printStackTrace();  
	        } catch (IllegalAccessException e) {  
	            e.printStackTrace();  
	        }
//	        } catch (InvocationTargetException e) {  
//	            e.printStackTrace();  
//	        }  
	        return bean;  
	    }
	
}