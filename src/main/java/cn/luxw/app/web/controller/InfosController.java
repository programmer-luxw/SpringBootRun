package cn.luxw.app.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.luxw.app.util.JsonUtil;


/**
 * 提供给运维调用检查API是否正常运行
 * @Description: TODO
 * @author JULY
 * @date  2018年3月29日 
 * @version V1.0
 */
//@Slf4j
@RestController
@RequestMapping("/v/infos")
public class InfosController {
	
	@Value("${spring.profiles.active}") 
	private String environment;
	@Autowired
	private EhCacheCacheManager ehCacheCacheManager;
	/**
	 *该接口提供给运营监控,勿修改
	 * @return
	 */
	 @GetMapping("/env")
	 public String version() {
		 test();
		return "net-"+environment;
	 }
	
	@GetMapping("/ehcache")
	 public Object getCahce(String key) {
	 ehCacheCacheManager.getCache("longtimecache").evict(key);
		//CacheManager c = ehCacheCacheManager.getCacheManager();
//		 Element e = ehCacheCacheManager.getCacheManager().getCache("longtimecache").get(key);
//		 if(e== null) {
//			 System.out.println("null--==--");
//		 }else {
//			return JsonUtil.toString(e);
//		 }
		 return null;
	 }
	
	private void  test() {
		
			Thread t = new Thread(()-> {
				while(true) {
					System.out.println("xxx777");
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("==============");
				}
				} );
			t.setDaemon(true);
			t.start();
		
	}
    
   
   
}
