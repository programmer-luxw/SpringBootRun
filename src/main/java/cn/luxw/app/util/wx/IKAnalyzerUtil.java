package cn.luxw.app.util.wx;


import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.wltea.analyzer.lucene.IKAnalyzer;

public class IKAnalyzerUtil {

	static org.wltea.analyzer.lucene.IKAnalyzer analyzer = null;
	
	static{
		analyzer = new IKAnalyzer();
	}
	
	/**
	 * 根据关键词 分词
	 * @param keyword (eg:肯德基宅急送)
	 * @param minLenEq2  分词结果 最小长度为2
	 * @return eg：[肯德基,宅急送,急送]
	 * @throws IOException
	 */
	public static List<String> getIKAnalyzerList(String keyword,boolean minLenEq2) throws IOException{
		List<String> list = new ArrayList<String>();
		TokenStream ts = analyzer.tokenStream("field",new StringReader(keyword));
		CharTermAttribute term = ts.addAttribute(CharTermAttribute.class);
		ts.reset();
		while (ts.incrementToken()) {
			if(minLenEq2 && term.toString().length() >= 2){
				list.add(term.toString());
			}else if(!minLenEq2){
				list.add(term.toString());
			}
		}
		if(keyword.length() == 4){
			Matcher matcher = Pattern.compile("^[\u4e00-\u9fa5]+$").matcher(keyword);
			if (matcher.find()){
				if(!list.contains(keyword.substring(0, 2)))
					list.add(keyword.substring(0, 2));
				if(!list.contains(keyword.substring(2, 4)))
					list.add(keyword.substring(2, 4));
			}
		}
		ts.end();
		ts.close();
		return list;
    }
}
