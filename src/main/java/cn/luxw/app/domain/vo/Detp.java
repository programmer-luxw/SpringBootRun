package cn.luxw.app.domain.vo;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;

import lombok.Data;

@Data
public class Detp {
	private Integer deptId;
	private String type;
	private Integer num;
	
	public static void main(String[] args) {
		
	}
	
	
	  private static Detp combine(Detp e1, Detp e2){
	  Detp e = new Detp();
	  e.setDeptId(e1.getDeptId());
	  e.setType(e1.getType());
	  e.setNum(e1.getNum() + e2.getNum());
	  return e;
	  }
}

