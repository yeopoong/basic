package kyun.network.emul;

public class Logger {
	
	static int LOG_LEVEL_DEBUG = 1;
	static int LOG_LEVEL_INFO = 2;
	static int LOG_LEVEL_ERROR = 3;
	static int LOG_LEVEL_OFF = 4;

	static int LOG_LEVEL = LOG_LEVEL_DEBUG;

	
	public static void debug(Object obj){
		if (LOG_LEVEL <= LOG_LEVEL_DEBUG)
			System.out.println(obj);
	}
	
	public static void info(Object obj){
		if (LOG_LEVEL <= LOG_LEVEL_INFO)
			System.out.println(obj);
	}
	
	public static void error(Object obj){
		System.out.println(obj);
	}
	
	public static void error(Throwable t){
		t.printStackTrace();
	}
	
	public static void error(Object obj, Throwable t){
		System.out.println(obj);
		t.printStackTrace();
	}
	
	public static void setLogLevel(String level){
		if ("DEBUG".equalsIgnoreCase(level)) {
			LOG_LEVEL = LOG_LEVEL_DEBUG;
		}
		else if ("INFO".equalsIgnoreCase(level)) {
			LOG_LEVEL = LOG_LEVEL_INFO;
		}
		else if ("ERROR".equalsIgnoreCase(level)) {
			LOG_LEVEL = LOG_LEVEL_ERROR;
		}
		else if ("OFF".equalsIgnoreCase(level)) {
			LOG_LEVEL = LOG_LEVEL_OFF;
		}
	}
	
	public static boolean isDebugMode(){
		return LOG_LEVEL == LOG_LEVEL_DEBUG;
	}
}
