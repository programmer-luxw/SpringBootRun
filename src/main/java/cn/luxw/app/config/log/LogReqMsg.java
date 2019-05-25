package cn.luxw.app.config.log;

import lombok.AllArgsConstructor;
import lombok.Data;


/**
 * 封装请求log信息
 * @author JULY
 *
 */
@AllArgsConstructor
@Data
public class LogReqMsg{
	private Integer pid;
	private Integer aff;
	private String path;
	private String ip;
	private long timestamp;
	private long times;
}
