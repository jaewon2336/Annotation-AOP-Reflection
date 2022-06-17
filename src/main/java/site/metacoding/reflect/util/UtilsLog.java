package site.metacoding.reflect.util;

// 싱글톤패턴
public class UtilsLog {
	
	private static UtilsLog instance = new UtilsLog();

	private UtilsLog() {} // private이라서 외부에서 new 불가능
	
	public static UtilsLog getInstance() {
		return instance;
	}
	
	public void info(String TAG, String msg) {
		System.out.println("====================INFO "+ TAG + msg);
	}
}
