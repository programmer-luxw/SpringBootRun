package cn.luxw.app.apply;

import java.math.BigDecimal;
import java.util.Map;
import java.util.stream.Collectors;

//BCryptPasswordEncoder
public class Jdk8Apply {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static void test1() {
//		List<Long> productIds = appProductList.stream().map(QmAppProduct::getProductId).collect(Collectors.toList());
//	     CompletableFuture[] cfs = productIds.stream()
//	             .map(productId -> CompletableFuture.runAsync(() -> distributeService.batchDoUserFilter(productId), distributeThreadPool))
//	             .toArray(CompletableFuture[]::new);
//	     try {
//	         CompletableFuture.allOf(cfs).join();
//	     } catch (Exception e) { // just for testing
//	         log.error("产品分发定时任务异常信息:",e);
//	     }

	}
	public static void test2() {
		
		//Map<Long, Integer> productRemainMap = qmAppProductList.stream().collect(Collectors.toMap(QmAppProduct::getProductId, QmAppProduct::getRemainLimit),(oldValue,newValue) -> oldValue);
	}
	
	 private static Map<Long, BigDecimal> sortByValue(Map<Long, BigDecimal> map) {
//	        Map<Long, BigDecimal> sortedMap = new LinkedHashMap<>();
//	        // DESC
//	        map.entrySet().stream()
//	                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
//	                .forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));
//	        return sortedMap;
		 return null;
	    }
	
	 
//	 authItemsList.stream().allMatch
	 
}
