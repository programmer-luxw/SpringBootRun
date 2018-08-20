package cn.luxw.app.util;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wltea.analyzer.cfg.Configuration;
import org.wltea.analyzer.cfg.DefaultConfig;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

public class IKanalyzerUtil {
	private static final Logger logger = LoggerFactory.getLogger(IKanalyzerUtil.class);

	public static List<String> analyzer(String input, boolean useSmart) {
		Configuration cfg = DefaultConfig.getInstance();
		cfg.setUseSmart(useSmart);
		return analyzer(input, cfg);
	}

	private static List<String> analyzer(String input, Configuration cfg) {
		List<String> list = Lists.newArrayList();
		if ((Strings.isNullOrEmpty(input)) || (cfg == null)) {
			return list;
		}

		Reader reader = null;
		try {
			reader = new StringReader(input);
			IKSegmenter ik = new IKSegmenter(reader, cfg);
			Lexeme lexeme = null;
			while ((lexeme = ik.next()) != null) {
				list.add(lexeme.getLexemeText());
			}
			return list;
		} catch (Exception ex) {
			logger.error("分词错误 = {}", new Object[] { input }, ex);

			if (null != reader)
				try {
					reader.close();
				} catch (IOException ioe) {
					logger.error("分词reader关闭异常 = {} , Ex = {}", new Object[] { input, ioe }, ioe);
				}
		} finally {
			if (null != reader) {
				try {
					reader.close();
				} catch (IOException ioe) {
					logger.error("分词reader关闭异常 = {} , Ex = {}", new Object[] { input, ioe },ioe);
				}
			}
		}
		return list;
	}

}