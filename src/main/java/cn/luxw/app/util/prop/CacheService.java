package cn.luxw.app.util.prop;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

/**
 * 缓存
 * @author Luxh
 *
 */
@Service
public class CacheService {
	
	private static Cache<String, Object> cache = CacheBuilder.newBuilder().build();

	public Object get(final String key){
		Object value = null;
		try {
			value = cache.get(key, new Callable<Object>() {
				@Override
				public Object call() throws Exception {
					return "";
				}
			});
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return value;
	}
	
	public void put(String key, Object value) {
		cache.put(key, value);
	}
	
	public void delete(String key) {
		cache.invalidate(key);
	}
	
	public void clear() {
		cache.invalidateAll();
	}
	
	
}
