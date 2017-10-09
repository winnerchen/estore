package chen.yiheng.estore.utils;

import java.util.ResourceBundle;

public class MyFactory {

	@SuppressWarnings("unchecked")
	public static <T> T getInstance(Class<T> c) {
		String interfaceName = c.getSimpleName();

		String instanceClasspath = ResourceBundle.getBundle("ServletConfig")
				.getString(interfaceName);
		if (instanceClasspath != null) {
			try {
				Object obj = Class.forName(instanceClasspath).newInstance();
				return (T) obj;
			} catch (Exception e) {
				throw new RuntimeException();
			}

		} else {
			throw new RuntimeException("配置文件错误,请配置正确");
		}
	}
	public static void main(String[] args) {
		int i=15;
		System.out.println(i%13);
	}

}
