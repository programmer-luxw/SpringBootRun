package cn.luxw.app.guava.base;
//package cn.luxw.app.test.guava;
//
//import java.util.List;
//
//import org.apache.commons.lang.StringUtils;
//import org.junit.Test;
//
//public class GGstrings {
//
//	/**
//	 * 1.appendTo(*,*) : appendTo方法则提供了向可追加对象后增加数据的做法, 诸如可以向已有的StringBuilder对象后追加循环数据等;
//	 */
//	@Test
//	public void testJoiner() {// 合并
//		String[] str1 = { "test", "bb", "cc", "yyy", null };
//		ImmutableList<String> list = ImmutableList.of("a", "b", "c", "d");
//		ImmutableMap<String, String> map = ImmutableMap.of("key1", "value1", "key2", "value2");
//	
//		String result1 = Joiner.on("/").useForNull("isEmpty").join(str1);// 拆分数组
//		
//		String result2 = Joiner.on("||").skipNulls().join(list);// 拆分list集合
//		String result3 = Joiner.on("&").withKeyValueSeparator("=").join(map);// 拆分map集合
//		System.out.println(result1);
//		System.out.println(result2);
//		System.out.println(result3);
//
//	}
//
//	/**
//	 * 1.fixedLength(5) 每隔5个字符进行一次分割;<br>
//	 * 2.omitEmptyStrings() 用于排除分割后的空字符串数据; <br>
//	 * 3.trimResults() 用于清除分割后结果中，的字符串中的空格, 同样还可以使用trimResults(CharMatcher), 制定清除符合CharMatcher的字符;<br>
//	 */
//	@Test
//	public void testSplitter() {// 拆分
//		String str = "https://google.com/index.jsp/nex/   /   3x x //last";
//
//		List<String> list = Splitter.on("/").omitEmptyStrings().trimResults().splitToList(str);
//		System.out.println(list);
//
//		List<String> list2 = Splitter.on('|').limit(3).splitToList("agggg|b|c|ii|pp");
//		System.out.println(list2.size() + "" + list2);
//
//		List<String> list3 = Splitter.on(',').trimResults(CharMatcher.is('_')).splitToList("_a ,_b_ ,c__");
//		System.out.println(list3);
//
//	}
//
//	/**
//	 * 1.字符串对象的null和”"的相关处理方法 <br>
//	 * 2.字符串对象的null和”"的相关处理方法 <br>
//	 * 3.找到两个字符串中前缀相同或后缀相同的最长子串
//	 */
//	@Test
//	public void testStrings() {
//		
//		String str1 = "";
//		boolean b = Strings.isNullOrEmpty(str1);
//		String str2 = Strings.emptyToNull(str1);
//		String str3 = Strings.padEnd("123", 6, '0');
//		String s4 = Strings.repeat("abc", 2);// 重复兩次
//		String s5 = Strings.commonPrefix("abcEf", "abcedd");// 找到两个字符串中前缀相同或后缀相同的最长子串
//
//		String test = "This is a test of the abbreviation.";
//
//		System.out.println(b);
//		System.out.println(str2);
//		System.out.println(str3);
//		System.out.println(s4);
//		System.out.println(s5);
//		System.out.println(StringUtils.abbreviate(test, 8));
//	}
//
//	/**
//	 * Format Example LOWER_CAMEL lowerCamel <br>
//	 * LOWER_HYPHEN lower-hyphen<br>
//	 * LOWER_UNDERSCORE lower_underscore <br>
//	 * UPPER_CAMEL UpperCamel <br>
//	 * UPPER_UNDERSCORE UPPER_UNDERSCORE<br>
//	 **/
//	@Test
//	public void testCaseFormat() {
//		CaseFormat lower_underscore = CaseFormat.LOWER_UNDERSCORE;// lower_underscore
//		CaseFormat lower_camel = CaseFormat.LOWER_CAMEL;// lowerCamel
//
//		String s = "lowerCamel";
//		System.out.println(lower_camel.to(lower_underscore, s));
//
//		// 将UPPER_UNDERSCORE格式的字符串转变为LOWER_CAMEL格式。
//		System.out.println(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "YUAN_RQ,hi,CSDN_China"));
//		// 输出结果：yuanRq,hi,csdnChina
//	}
//
//	//http://my.oschina.net/leejun2005/blog/178569
//	@Test
//	public void testCharMatcher() {
//		String name = "NETE_LXW";
//		CharMatcher matcher = CharMatcher.JAVA_UPPER_CASE.or(CharMatcher.is('_'));
//		boolean b = matcher.matchesAllOf(name);
//		System.out.println(b);
//		// two 
//		String target = "xjjf554.215";
//		target = CharMatcher.JAVA_DIGIT.or(CharMatcher.is('.')).retainFrom(target).concat(".0");
//		System.out.println(target);
//
//		// removerFrom
//		String removeFromResult = CharMatcher.isNot('a').removeFrom("abacd");
//		System.out.println("removeForm:" + removeFromResult);
//		
//		CharMatcher.inRange('a','z').or(CharMatcher.inRange('A','Z'));
//		
//		CharMatcher.JAVA_ISO_CONTROL.removeFrom(target);
//		
//		CharMatcher.WHITESPACE.collapseFrom(target, ' ');
//		CharMatcher.WHITESPACE.trimAndCollapseFrom(target, ' ');
//		CharMatcher.JAVA_DIGIT.or(CharMatcher.JAVA_LOWER_CASE).retainFrom(target);
//	}
//	public static void main(String[] args) {
//		TypeToken<String> stringTok = TypeToken.of(String.class);
//		TypeToken<Integer> intTok = TypeToken.of(Integer.class);
//		
//	}
//	
//}
