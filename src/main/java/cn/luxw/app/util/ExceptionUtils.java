package cn.luxw.app.util;

import com.google.common.base.Throwables;

public class ExceptionUtils {

	public static void rethrow(Throwable e) {
		Throwables.propagate(e);
	}
}
