package cn.luxw.app.util.wx;


import java.math.BigDecimal;

import org.joda.time.DateTime;

import com.google.common.collect.Range;

public class ValidUtil {
	
	
	
	/**
	 * 检查当前时间是否为微信发送时间 (0:00-8：00不触发红包赠送)
	 * @return
	 */
	public static boolean checkNowtime(){
		boolean result = Boolean.FALSE;
		Range<Integer> range = Range.closed(9, 23);
		boolean isValid = range.contains(new DateTime().getHourOfDay());
		if(isValid){
			result = Boolean.TRUE;
		}
		return result;
	}
	
	
	/**
	 * 验证金额是否是有效的,(0,200]
	 * @param hb
	 * @return
	 */
	public static boolean checkAmount(BigDecimal bd){
		boolean result = Boolean.FALSE;
		if(bd != null){
			if(bd.compareTo(new BigDecimal(200)) <= 0 && bd.compareTo(BigDecimal.ZERO)>0){
				result = Boolean.TRUE;
			}
		}
		return result;
	}

}
