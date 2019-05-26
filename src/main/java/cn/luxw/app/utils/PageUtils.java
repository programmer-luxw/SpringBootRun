package cn.luxw.app.utils;





/**
 * @description	
 * @author July
 * @date 2016-1-6
 */
public class PageUtils {
	
	/**
	 * 根据当前页和每页记录数获取起点位置
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public static int getStartIndex(int currentPage,int pageSize){
		if(pageSize<1 || pageSize>Integer.MAX_VALUE) {
			throw new RuntimeException("每页显示数目超出范围");
		}
		if(currentPage<0) {
			throw new RuntimeException("页码超出范围");
		}
		if(((currentPage - 1)*pageSize) > Integer.MAX_VALUE) {
			throw new RuntimeException("非法的分页参数值");
		}
		return ((currentPage - 1)<0 ? 0 : (currentPage - 1))*pageSize;
	}
}
