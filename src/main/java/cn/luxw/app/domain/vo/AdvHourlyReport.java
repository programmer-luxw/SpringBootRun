package cn.luxw.app.domain.vo;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class AdvHourlyReport {
	private Integer advertiser_id;
	private Integer manager_id;
	private Integer offer_id;
	
	private Integer clicks;
	private Integer unique_clicks;
	private Integer conversions;
	private Integer pending_conversions;
	
	private BigDecimal pay_out;
	private BigDecimal revenue;
	private BigDecimal amount;
	
	private String currency;
	private Integer create_time;
	
	//https://blog.csdn.net/sdyy321/article/details/40298081
		//https://gitee.com/eagleFlySky/codes/gnwof0bxi1v3sr9qujpzt16
	// @JsonSerialize(using = MyBigDecimalSerializer.class)  
	/*private static class MyBigDecimalSerializer extends JsonSerializer<BigDecimal>{  
	    @Override  
	    public void serialize(BigDecimal value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {  
	        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	        String dateStr = dateFormat.format(value);  
	        jgen.writeString(dateStr);  
	    }  
	} */
}
