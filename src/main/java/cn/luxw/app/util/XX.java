package cn.luxw.app.util;

import java.io.IOException;
import java.nio.CharBuffer;

import kong.unirest.Callback;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;



public class XX<T> implements Callback<T> {

	@Override
	public void completed(HttpResponse<T> response) {
		System.out.println(")))=" + response.getBody());
		try {
			Unirest.shutDown();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void failed(UnirestException e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void cancelled() {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		String str = "看怎么用";
		CharBuffer cha = CharBuffer.allocate(16);
		// 输出缓冲区的 position and limit
		//
		System.out.println("刚创建缓冲区 position is " + cha.position() + "  limit is " + cha.limit());
		cha.put(str); // 存入数据。
		// 再看这两个参数。
		System.out.println("存入数组后   position is " + cha.position() + "  limit is " + cha.limit());
		cha.flip(); // “倒带”后再看
		System.out.println("flip() 以后  position is " + cha.position() + "  limit is " + cha.limit());
		String str1 = cha.toString();
		System.out.println(str1);
	}

}
