package com.starry.mytools;

import android.util.Log;

public class KLog {
	public static final boolean isDebug = true;

	private KLog() {
		throw new UnsupportedOperationException("cannot be instantiated");
	}

	public static void i(String tag, String msg) {
		if (isDebug)
			Log.i(tag, msg);
	}

	public static void d(String tag, String msg) {
		if (isDebug)
			Log.d(tag, msg);
	}

	public static void e(String tag, String msg) {
		if (isDebug)
			Log.e(tag, msg);
	}

	public static void v(String tag, String msg) {
		if (isDebug)
			Log.v(tag, msg);
	}
}
