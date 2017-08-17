package com.myworld.reservation.common;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.MethodUtils;

/**
 * 核心控制器 
 * 1. init()调用控制器扫描器扫描 package.controller.XXXController
 * Map<String, ControllerMetaData> metaDataMap; //属性
 * 2. doGet | doPost 
 * a) 从请求中解析出uri: /user/login 
 * b)通过uri从metaDataMap中取到一个ControllerMetaData 
 * c) 再从ControllerMetaData得到控制器类和方法对象
 * d) 解析方法中有哪些参数类型，并创建相应的对象，以便于随后调用方法时传递这些参数值
 * e) 创建一个与参数类型相同大小的数组来存参数值
 * f) 调用控制器方法： Object targetPath = MethodUtils.invokeMethod(controller,
 * requestMethod.getName(), parameter);
 * 
 * @author gg
 *
 */
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map<String, ControllerMetaData> metadataMap;
	public DispatcherServlet() {
		super();
	}

	@Override
	public void init() throws ServletException {
		metadataMap = ControllerScanner.scan();
	}

	public void destroy() {
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		//得到用户请求的URL: http://localhost:8080/ReservationSystem/user/login.do?loginName=admin&loginPass=admin
		//拦截所有/*.do的请求 /ReservationSystem/user/login.do
		//request.getContextPath()/ReservationSystem
		String uri = request.getRequestURI();
		uri = StringUtils.substringBetween(uri, request.getContextPath(), ".do");
		System.out.println("path:"+uri);
		if(metadataMap.containsKey(uri)){
			ControllerMetaData controllerMetaData = metadataMap.get(uri);
			//得到控制器类实例
			Class<?> controllerClass = controllerMetaData.getControllerClass();
			//处理该请求的方法
			Method requestMethod = controllerMetaData.getRequestMethod();
			try {
				//分析方法上每个参数的类型
				Class<?>[] parameterTypes = requestMethod.getParameterTypes();
				//创建一个与参数类型相同大小的数组来存参数值
				Object[] parameters = new Object[parameterTypes.length];
				for (int i = 0; i < parameterTypes.length; i++) {
					//System.out.println(parameterTypes[i]);
					if(ClassUtils.isAssignable(parameterTypes[i], ServletRequest.class)){
						//javax.servlet.http.HttpSession = 
						parameters[i] = request;
					}else if(ClassUtils.isAssignable(parameterTypes[i], ServletResponse.class)){
						parameters[i] = response;
					}else if(ClassUtils.isAssignable(parameterTypes[i], HttpSession.class)){
						parameters[i] = request.getSession();
					}else{
						//通过类实例反射出对象
						Object bean = parameterTypes[i].newInstance();
						//将请求中的数据组装到obj对象中
						// ......?userId=99&username=James
						//<input name="username">
						//保证请求参数名与目标bean的属性名一致即可
						BeanUtils.populate(bean, request.getParameterMap());
						parameters[i] = bean;
					}
				}
				
				//创建出控制器对象
				Object controller = controllerClass.newInstance();
				//Object targetPath = requestMethod.invoke(controller, parameters);
				//调用控制器的方法
				Object targetPath = MethodUtils.invokeMethod(controller, requestMethod.getName(), parameters);
				//根据返回的字符串跳转
				if (targetPath!=null) {
					String target = targetPath.toString();
					if (StringUtils.startsWith(target, "redirect:")) { //重定向
						response.sendRedirect(request.getContextPath()+StringUtils.substringAfter(target, "redirect:"));
					} else {//请求转发
						request.getRequestDispatcher(target);
					}
					return ;
				}else{
					//意味着是AJAX请求，无需处理
				}
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
		}
	}

}
