package aop.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import aop.annotation.Permission;

public class MyHandler implements InvocationHandler {

	private Object target;
	
	private Map<String,List<String>> session;
	//接收要执行的业务所属对象
	public MyHandler(Object target, Map<String, List<String>> session){
		
		this.target=target;
		this.session=session;
	}
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		//验证session里有没有这个用户
		boolean flag=session.keySet().contains("张三");
		String methodName=method.getName();
		method=target.getClass().getMethod(methodName);
		//判断有无permission注解
		if(method.isAnnotationPresent(Permission.class))
			if(!flag){
				throw new RuntimeException("请先登录");
			}
			//获取张三所有权限注解
			List<String> permissions=session.get("张三");
			//拿出当前要执行方法的权限
			Permission permission=method.getAnnotation(Permission.class);
			String privilege=permission.value();
			//能否在用户权限中找到
			if(!permissions.contains(privilege)){
				throw new RuntimeException("您没有权限访问");
			}
			//达到该要求执行
			method.invoke(target);
		return null;
	}

}
