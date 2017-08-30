import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import aop.handler.MyHandler;
import aop.service.ServiceClass;
import aop.service.ServiceClassImpl;

public class Main {

	//定义一个类似于session对象的数据
	static Map<String, List<String>> session=
			new HashMap<String, List<String>>();
	//给session里存入数据
	static{
		String user="张三";
		List<String> permissions=new ArrayList<String>();
		permissions.add("excutionService1");
		permissions.add("excutionService2");
		session.put(user, permissions);
				
	}
	public static void main(String[] args) {
		
		ServiceClassImpl sci=new ServiceClassImpl();
		//通过工具类来创建代理对象
		ServiceClass proxy=
				(ServiceClass) Proxy.newProxyInstance(sci.getClass().getClassLoader(),
						sci.getClass().getInterfaces(),new MyHandler(sci, session) );
		proxy.excutionService1();
		proxy.excutionService2();
	}
}
