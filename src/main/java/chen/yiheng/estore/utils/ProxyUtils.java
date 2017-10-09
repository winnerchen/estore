package chen.yiheng.estore.utils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import chen.yiheng.estore.domain.User;
import org.apache.log4j.Logger;

public class ProxyUtils<T> {
	T target;

	public ProxyUtils(T t) {
		this.target = t;
	}

	@SuppressWarnings("all")
	public T getProxy() {
		return (T) Proxy.newProxyInstance(ProxyUtils.class.getClassLoader(),
				target.getClass().getInterfaces(), new InvocationHandler() {
					public Logger logger = Logger.getLogger(target.getClass());

					@Override
					public Object invoke(Object proxy, Method method,
							Object[] args) throws Throwable {
						// 哪些方法需要添加事务管理 定义规范 目标类业务方法以: save开头 或者 delete 方法开头
						// 添加事务管理
						if (method.getName().startsWith("save")) {
							try {
								TransactionManager.begin();
								return method.invoke(target, args);
							} catch (Exception e) {
								TransactionManager.rollback();
							} finally {
								TransactionManager.commit();
							}
						} else if (method.getName().startsWith("login")) {
							User user = (User) args[0];
							logger.info("用户" + user.getEmail() + "登录");
							User existUser = (User) method.invoke(target, args);
							if (existUser == null) {
								logger.info("登录失败");
							} else {
								logger.info("登录成功");
							}
							return method.invoke(target, args);
						}
						return method.invoke(target, args);// 其他方法不用添加事务管理
					}
				});
	}

}
