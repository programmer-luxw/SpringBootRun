package cn.luxw.app.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Maps;

public class BeanUtils {

	public static Map<String, Object> transBean2Map(Object obj) {
		Map<String, Object> map = Maps.newHashMap();
		try {
			if (obj == null) {
				return map;
			}
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();
				if (!StringUtils.equals("class", key)) {// 过滤class属性和 sign属性
					Method getter = property.getReadMethod();// 得到property对应的getter方法
					Object value = getter.invoke(obj);
					map.put(key, value);
				}
			}
		} catch (Exception e) {
			
			throw new RuntimeException(e);
		}
		return map;
	}
}
