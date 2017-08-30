package aop.service;

import aop.annotation.Permission;

//测试业务的实现类
public class ServiceClassImpl implements ServiceClass {

	@Permission("excutionService1")
	public void excutionService1() {
		System.out.println("执行excutionService1");
	}

	@Permission("excutionService2")
	public void excutionService2() {
		System.out.println("执行excutionService2");
	}

}
