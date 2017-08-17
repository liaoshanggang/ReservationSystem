package com.myworld.reservation.common;

import java.lang.reflect.Method;

public class ControllerMetaData {
	//业务控制器类实例
	private Class<?> controllerClass;
	
	// 业务控制器类实例的目标方法，即标注了@RequestMapping的方法实例
	private Method requestMethod;

	public ControllerMetaData(Class<?> controllerClass, Method requestMethod) {
		super();
		this.controllerClass = controllerClass;
		this.requestMethod = requestMethod;
	}

	public Class<?> getControllerClass() {
		return controllerClass;
	}

	public void setControllerClass(Class<?> controllerClass) {
		this.controllerClass = controllerClass;
	}

	public Method getRequestMethod() {
		return requestMethod;
	}

	public void setRequestMethod(Method requestMethod) {
		this.requestMethod = requestMethod;
	}
	
}
