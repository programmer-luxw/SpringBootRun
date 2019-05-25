package cn.luxw.app.utils;



import com.google.common.base.Charsets;
import com.google.common.base.Throwables;
import com.google.common.io.ByteStreams;
import com.google.common.io.Closeables;
import com.google.common.io.Files;
import com.google.common.io.Resources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;


/**
 * @description 文件帮助类
 * @author luxiaowen
 * @date 2019-5-17
 */
public class FileUtils {
	private static Logger LOG = LoggerFactory.getLogger(FileUtils.class);

	/**
	 *
	 * @param str 要写的字符串
	 * @param filePath 类似:D:/keys/json.txt
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
			Throwables.throwIfUnchecked(e);
		}
	}

	/**
	 * 读取文件
	 * 
	 * @param classpath
	 * @return
	 */
	
	public static String readFileToString(File file, boolean classpath) {
		
		String str = null;
		try {
			 str = Files.asCharSource(file, Charsets.UTF_8).read();
			//str = com.google.common.io.Files.toString(file, Charsets.UTF_8);
		} catch (IOException e) {
			Throwables.throwIfUnchecked(e);
		}
		return str;
	}


	/**
	 *
	 * @param filePath 类似:D:/keys/json.txt
	 * @param classpath 如果是本地d盘之类的，就false
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
			Throwables.throwIfUnchecked(e);
		}
		return str;
	}
	
	/**
	 * 读取流成字符串
	 * @return
	 */
	public static String streamToString(InputStream in) {
		String result = null;
		try {
			byte[] bytes = ByteStreams.toByteArray(in);
			result = new String(bytes, Charsets.UTF_8);
		} catch (IOException e) {
			Throwables.throwIfUnchecked(e);
		} finally {
			Closeables.closeQuietly(in);
		}
		return result;
	}
	
	
	


	

}
