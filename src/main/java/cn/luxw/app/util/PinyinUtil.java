package cn.luxw.app.util;

import java.util.List;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.CaseFormat;
import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;

public class PinyinUtil {
	private static final Logger logger = LoggerFactory.getLogger(PinyinUtil.class);
	private static HanyuPinyinOutputFormat Output_Format = new HanyuPinyinOutputFormat();

	public static boolean isChinese(char c) {
		return Character.toString(c).matches("[\\u4E00-\\u9FA5]+"); 
	}

	public static String convert(String src) {
		return convert(src, null, false);
	}
	
	 public static void main(String[] args) {
		 String str = "77天他";
		boolean cc= str.matches("[\\d]+[年月天]");
		System.out.println(cc);
		
	}
	public static String convert(String src, CaseFormat format) {
		return convert(src, format, false);
	}

	public static String convert(String src, CaseFormat format, boolean first) {
		if (Strings.isNullOrEmpty(src)){
			return "";
		}
		List<Object> pinyins = Lists.newLinkedList();
		try {
			char[] input = src.trim().toCharArray();
			for (char c : input){
				if (isChinese(c)) {
					String[] temp = PinyinHelper.toHanyuPinyinStringArray(c, Output_Format);
					if (!ArrayUtils.isEmpty(temp)){
						pinyins.add((first ? Character.valueOf(temp[0].charAt(0)) : temp[0]));
					}
				} else {
					pinyins.add(Character.toString(c));
				}
			}
		} catch (Exception ex) {
			logger.error("汉字转拼音错误 = {}，定界字符 = {}", new Object[] { src, format },ex);
		}
		return null == format ? Joiner.on(" ").join(pinyins) : CaseFormat.LOWER_UNDERSCORE.to(format, Joiner.on("_").join(pinyins));
	}

	static {
		Output_Format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		Output_Format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		Output_Format.setVCharType(HanyuPinyinVCharType.WITH_V);
	}

	
}
