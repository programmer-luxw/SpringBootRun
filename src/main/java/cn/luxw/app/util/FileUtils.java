package cn.luxw.app.util;



import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.base.Charsets;
import com.google.common.base.Throwables;
import com.google.common.io.ByteStreams;
import com.google.common.io.Closeables;
import com.google.common.io.Files;
import com.google.common.io.Resources;



/**
 * @description 文件帮助类
 * @author July
 * @date 2016-1-6
 */
public class FileUtils {
	private static Logger LOG = LoggerFactory.getLogger(FileUtils.class);
	/**
	 * 写出字符串到文件
	 * @param path
	 * @param str
	 */
	public static void writeStringToFile(String str, String filePath) {
		try {
			File file = new File(filePath);
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			Files.asCharSink(file, Charsets.UTF_8).write(str);
			//com.google.common.io.Files.write(str, file, Charsets.UTF_8);
		} catch (IOException e) {
			Throwables.propagate(e);
		}
	}

	/**
	 * 读取文件
	 * 
	 * @param filePath
	 * @param classpath
	 * @return
	 */
	public static String readFileToString(String filePath, boolean classpath) {
		File file = new File(filePath);
		if (classpath) {
			file = new File(Resources.getResource(filePath).getFile());
		}
		String str = null;
		try {
			 str = Files.asCharSource(file, Charsets.UTF_8).read();
			//str = com.google.common.io.Files.toString(file, Charsets.UTF_8);
		} catch (IOException e) {
			Throwables.propagate(e);
		}
		return str;
	}
	
	/**
	 * 读取流成字符串
	 * @param input
	 * @return
	 */
	public static String streamToString(InputStream in) {
		String result = null;
		try {
			byte[] bytes = ByteStreams.toByteArray(in);
			result = new String(bytes, Charsets.UTF_8);
		} catch (IOException e) {
			Throwables.propagate(e);
		} finally {
			Closeables.closeQuietly(in);
		}
		return result;
	}
	
	
	
	
	
	
	
}
