package aop.service;

import aop.annotation.Permission;
//测试的业务类接口
public interface ServiceClass {
	
	//@Permission("excutionService1")
	void excutionService1();
	
	//@Permission("excutionService2")
	void excutionService2();

}
