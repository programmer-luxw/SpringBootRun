//package com.zhidian3g.util;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//import org.apache.solr.client.solrj.SolrQuery;
//import org.apache.solr.client.solrj.SolrQuery.ORDER;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//public class SolrUtil {
//
//	private static final Logger logger = LoggerFactory.getLogger(SolrUtil.class);
//	
//	public static void main(String[] args) {
//		long beg = System.currentTimeMillis();
//		System.out.println(wrapEnglishChineseKeyword("tao金"));
//		long end = System.currentTimeMillis();
//		System.out.println(beg-end);
//	}
//	
//	public static String wrapEnglishChineseKeyword(String keyword) {
//		Pattern patEn = Pattern.compile("^([a-zA-Z]+|[0-9]+)([\u4e00-\u9fa5]+)");
//		Pattern patCh = Pattern.compile("^([\u4e00-\u9fa5]+)([a-zA-Z]+|[0-9]+)");
//		Matcher matcherEn_Ch = patEn.matcher(keyword);
//		Matcher matcherCh_En = patCh.matcher(keyword);
//		if (matcherEn_Ch.find()){
//			int ch_index = keyword.indexOf(matcherEn_Ch.group(2));
//			return keyword.substring(0,ch_index)+" "+ keyword.substring(ch_index);
//		}else if(matcherCh_En.find()){
//			int en_index = keyword.indexOf(matcherCh_En.group(2));
//			return keyword.substring(0,en_index)+" "+ keyword.substring(en_index);
//		}
//		return keyword;
//	}
//	
//	/**
//	 * is all English
//	 * @param str
//	 * @return
//	 */
//	public static boolean isAllEnglish(String str) {
//		Pattern pat_ch = Pattern.compile("^[a-zA-Z]+$");
//		Matcher matcher = pat_ch.matcher(str);
//		if (matcher.find())
//			return true;
//		return false;
//	}
//	
//	/**
//	 * is all Chinese
//	 * @param str
//	 * @return
//	 */
//	public static boolean isAllChinese(String str) {
//		Pattern pat_ch = Pattern.compile("^[\u4e00-\u9fa5]+$");
//		Matcher matcher = pat_ch.matcher(str);
//		if (matcher.find())
//			return true;
//		return false;
//	}
//	
//	/**
//	 * 是否为  中英文组合
//	 * @param str
//	 * @return
//	 */
//	public static boolean isCN_EN_Composition(String str) {
//		Pattern before_EN = Pattern.compile("^([a-zA-Z]+)([\u4e00-\u9fa5]+)");
//		Pattern before_CN = Pattern.compile("^([\u4e00-\u9fa5]+)([a-zA-Z]+)");
//		if(before_EN.matcher(str).find())
//			return true;
//		else if(before_CN.matcher(str).find())
//			return true;
//		return false;
//	}
//	
//	/**
//	 * is all Number
//	 * @param str
//	 * @return
//	 */
//	public static boolean isAllNumber(String str) {
//		Pattern pat_num = Pattern.compile("^[0-9]+$");
//		Matcher matcher = pat_num.matcher(str);
//		if (matcher.find())
//			return true;
//		return false;
//	}
//	
//	/**
//	 * 
//	 * @param solrQuery
//	 * @param keyword
//	 */
//	public static void wrapAppQuerySearch(SolrQuery solrQuery,String keyword, String kw_pinyin, boolean isPulldown){
//		StringBuilder query = new StringBuilder();
//		boolean isFuzzySearch = false;
//		if(keyword.length() == 1){
//			solrQuery.addSort("downloads",ORDER.desc);
//			if(isAllEnglish(keyword)){
//				query.append("nameKW:"+keyword+"*");
//				query.append(" OR pinyinIK:"+keyword+"*");
//				if(!isPulldown)
//					query.append(" OR text:"+keyword+"*");
//				else
//					query.append(" OR name:"+keyword+"*");
//				isFuzzySearch = true;
//			}
//		}
//		if(!isFuzzySearch){
//			keyword = wrapEnglishChineseKeyword(keyword);
//			query.append("nameKW:"+keyword);
//			if(!isPulldown)
//				query.append(" OR text:"+keyword.replace(" ", ""));
//			else
//				query.append(" OR name:"+keyword);
//		}
//		if(keyword.length() == 2){
//			if(isAllChinese(keyword)){//中文 + 拼音 + 消歧义
//				String pinyin = PinyinUtil.chineseToPinyin(keyword,false);
//				query.append(" OR pinyinIK:"+pinyin);
//			}else if(isAllEnglish(keyword)){
//				query.append(" OR pinyinIK:"+keyword+"*");
//				solrQuery.addSort("downloads",ORDER.desc);
//			}else if(isCN_EN_Composition(keyword)){
//				String pinyin = PinyinUtil.chineseToPinyin(keyword,true);
//				query.append(" OR pinyinIK:"+pinyin);
//			}
//		}else if(keyword.length() >= 3){
//			if(isAllEnglish(keyword)){
//				query.append(" OR pinyinIK:"+keyword+"*");
//				solrQuery.addSort("downloads",ORDER.desc);
//	    	}else if(!isAllNumber(keyword)){
//	    		try {
//	    			List<String> list = IKAnalyzerUtil.getIKAnalyzerList(keyword,true);
//	    			if(list.size() > 0){
//	    				boolean isEndCN = false;
//	    				for (String ciyu : list) {
//	    					if(!keyword.equals(ciyu) && kw_pinyin == null){
//	    						query.append(" OR name:"+ciyu);
//								isEndCN = true;
//	    					}
//						}
//	    				
//	    				if(isEndCN)
//	    					query.append("^2");
//	    			}
//	    			keyword = keyword.replace(" ", "");
//	    			if(isCN_EN_Composition(keyword.replace(" ", ""))){
//						String pinyin = PinyinUtil.chineseToPinyin(keyword,true);
//						query.append(" OR pinyinIK:"+pinyin);
//					}
//				} catch (IOException e) {
//					logger.error(" IKAnalyzer participle fail,e:{}",e.getMessage());
//				}
//	    	}
//		}
//		solrQuery.setQuery(query.toString());
//	}
//
//}
